package com.restaurant.ui;

import com.restaurant.decorators.*;
import com.restaurant.domain.*;
import com.restaurant.factory.*;
import com.restaurant.notification.KitchenDisplay;
import com.restaurant.notification.WaiterScreen;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Welcome to the Restaurant Ordering System ===");

        // Abstract Factory: pick menu families
        MenuFactory vegFactory = new VegetarianMenuFactory();
        MenuFactory nonVegFactory = new NonVegetarianMenuFactory();
        MenuFactory kidsFactory = new KidsMenuFactory();

        // Create base items
        MenuItem vegPizza = vegFactory.createPizza();
        MenuItem beefBurger = nonVegFactory.createBurger();
        MenuItem kidsPizza = kidsFactory.createPizza();

        // Decorators: add customizations
        MenuItem customVegPizza = new ExtraCheese(new SpicySauce(vegPizza));
        MenuItem customBeefBurger = new Bacon(new Mushroom(beefBurger));
        MenuItem customKidsPizza = new ExtraCheese(kidsPizza); // one add-on

        // Build order and attach observers
        Order order = new Order();
        order.addObserver(new KitchenDisplay());
        order.addObserver(new WaiterScreen());

        order.addItem(customVegPizza);
        order.addItem(customBeefBurger);
        order.addItem(customKidsPizza);

        // Print order details
        for (MenuItem item : order.getItems()) {
            System.out.println("Added: " + item.getDescription() + " -> " + item.getPrice());
        }
        System.out.println("Total items: " + order.getItemsCount());
        System.out.println("Total price: " + order.getTotalPrice());

        // Place order -> triggers observers
        order.placeOrder();

        System.out.println("\nThank you for visiting!");
    }
}
