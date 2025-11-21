package com.restaurant.facade;

import com.restaurant.domain.MenuItem;
import com.restaurant.domain.Order;
import com.restaurant.payment.*;
import com.restaurant.pricing.*;
import com.restaurant.factory.*;
import com.restaurant.billing.*;
import com.restaurant.storage.DataStorage;
import com.restaurant.structure.*;
import com.restaurant.notification.*;
import com.restaurant.decorators.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RestaurantFacade {

    private final DataStorage store = new DataStorage();
    private final OrderPublisher publisher = new OrderPublisher();

    private final java.util.List<DiscountStrategy> discountStrategies = new java.util.ArrayList<>();
    private final BillGenerator billGenerator;

    public RestaurantFacade() {
        buildMenusFromFactories();

        discountStrategies.add(new PizzaDiscount(0.10));
        discountStrategies.add(new ComboDiscount(5.0));
        discountStrategies.add(new NoDiscount());
        billGenerator = new BillGenerator(discountStrategies, 0.14);
    }


    public void registerMenu(String name, MenuComponent menu) {
        store.saveMenu(name, menu);
    }

    public MenuComponent getMenu(String name) {
        return store.getMenu(name);
    }

    public void showMenu(String name) {
        MenuComponent menu = store.getMenu(name);
        if (menu != null) {
            System.out.println("\n--- " + name.toUpperCase() + " MENU ---");
            menu.print("");
        } else {
            System.out.println("Menu not found: " + name);
        }
    }

    // menu building
    private void buildMenusFromFactories() {
        try {
            List<Object> factories = new ArrayList<>();
            try { factories.add(Class.forName("com.restaurant.factory.VegetarianMenuFactory").getDeclaredConstructor().newInstance()); } catch (Exception ignored) {}
            try { factories.add(Class.forName("com.restaurant.factory.NonVegetarianMenuFactory").getDeclaredConstructor().newInstance()); } catch (Exception ignored) {}
            try { factories.add(Class.forName("com.restaurant.factory.KidsMenuFactory").getDeclaredConstructor().newInstance()); } catch (Exception ignored) {}

            MenuComposite mainMenu = new MenuComposite("Main Menu", "All categories");

            for (Object factory : factories) {
                Class<?> facClass = factory.getClass();
                String factoryName = facClass.getSimpleName().replaceAll("MenuFactory$", "");
                MenuComposite category = new MenuComposite(factoryName + " Menu", "Items from " + factoryName);
                int counter = 1;

                for (Method m : facClass.getMethods()) {
                    if (m.getParameterCount() != 0) continue;
                    if (!MenuItem.class.isAssignableFrom(m.getReturnType())) continue;

                    try {
                        Object result = m.invoke(factory);
                        if (result instanceof MenuItem) {
                            MenuItem mi = (MenuItem) result;
                            String leafId = factoryName.substring(0, 1).toLowerCase() + counter;
                            counter++;
                            String description = mi.getDescription();
                            double price = mi.getPrice();
                            MenuLeaf leaf = new MenuLeaf(leafId,  "", description, price);
                            category.add(leaf);
                        }
                    } catch (Exception e) {
                    }
                }

                if (!category.getChildren().isEmpty()) {
                    mainMenu.add(category);
                    registerMenu(factoryName.toLowerCase(), category);
                }
            }

            registerMenu("main", mainMenu);
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
    }

    // Order creation
    public Order createOrder() {
        return new Order();
    }

    public boolean addItemToOrderAnyMenu(Order order, String leafId, int qty) {
        if (order == null || leafId == null) return false;

        // search all  menus
        for (MenuComponent menu : store.getAllMenus()) {
            MenuLeaf leaf = findLeafById(menu, leafId);
            if (leaf != null) {
                for (int i = 0; i < Math.max(1, qty); i++) {
                    order.addItem(wrapLeafAsMenuItem(leaf));
                }
                return true;
            }
        }
        return false;
    }

    // view  items before checkout
    public void showOrderItems(Order order) {
        if (order == null || order.getItems().isEmpty()) {
            System.out.println("Order is empty.");
            return;
        }

        System.out.println("\n--- Items in Order ---");
        int i = 1;
        for (MenuItem item : order.getItems()) {
            System.out.printf("%d) %s (%.2f)%n", i++, item.getDescription(), item.getPrice());
        }
        System.out.printf("Subtotal: %.2f%n", order.getSubtotal());
    }

    //Decorators
    public boolean addAddonToLastItem(Order order, String addonType) {
        if (order == null) return false;
        List<MenuItem> items = order.getItems();
        if (items.isEmpty()) return false;

        MenuItem last = items.get(items.size() - 1);
        MenuItem decorated;
        switch (addonType == null ? "" : addonType.toUpperCase()) {
            case "CHEESE": decorated = new ExtraCheese(last); break;
            case "SPICY": decorated = new SpicySauce(last); break;
            case "BACON": decorated = new Bacon(last); break;
            case "MUSHROOM": decorated = new Mushroom(last); break;
            default: return false;
        }

        boolean removed = order.removeItem(last);
        if (!removed) {
            List<MenuItem> copy = new ArrayList<>(items);
            order.clearItems();
            for (MenuItem mi : copy) {
                if (mi == last) order.addItem(decorated);
                else order.addItem(mi);
            }
        } else {
            order.addItem(decorated);
        }
        return true;
    }

  // apply discounts and payment
    public boolean checkout(Order order, String paymentChoice, String paymentData) {
        if (order == null) return false;

        PaymentStrategy payment;
        switch (paymentChoice == null ? "CASH" : paymentChoice.toUpperCase()) {
            case "CARD": payment = new CardPayment(paymentData); break;
            case "WALLET": payment = new MobileWalletPayment(paymentData); break;
            default: payment = new CashPayment(); break;
        }

        finalizeOrder(order, payment);
        return order.getStatus() == Order.OrderStatus.PAID;
    }

    // ----------------- Observers -----------------
    public void registerObserver(OrderObserver observer) {
        publisher.registerObserver(observer);
    }

// bill
    public void finalizeOrder(Order order, PaymentStrategy payment) {
        if (order == null) {
            System.out.println("Error: No order, please create order.");
            return;
        }

        System.out.println("\nProcessing you order: " + order.getId());

        Bill bill = billGenerator.generate(order);

        PaymentResult result = payment.pay(order, bill.getTotal());
        if (result.isSuccess()) {
            order.markPaid(result.getMethod());
            store.saveOrder(order);
            publisher.publishOrderPlaced(order);
        } else {
            System.out.println("Payment failed: " + result.getMessage());
            return;
        }

        System.out.println("\n===== FINAL INVOICE =====");
        System.out.println(bill);
        System.out.println(result);
        System.out.println("=========================\n");
    }


    private MenuLeaf findLeafById(MenuComponent comp, String id) {
        if (comp == null) return null;
        if (comp instanceof MenuLeaf) {
            MenuLeaf leaf = (MenuLeaf) comp;
            if (leaf.getId().equals(id)) return leaf;
            return null;
        }
        if (comp instanceof MenuComposite) {
            for (MenuComponent child : ((MenuComposite) comp).getChildren()) {
                MenuLeaf r = findLeafById(child, id);
                if (r != null) return r;
            }
        }
        return null;
    }

    private MenuItem wrapLeafAsMenuItem(MenuLeaf leaf) {
        return new MenuItem() {
            @Override public String getDescription() { return leaf.getDescription(); }
            @Override public double getPrice() { return leaf.getPrice(); }
        };
    }

    public void listAllOrders() {
        List<Order> orders = store.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders placed yet.");
        } else {
            System.out.println("\n--- All Orders ---");
            for (Order o : orders) System.out.println(o);
        }
    }
}
