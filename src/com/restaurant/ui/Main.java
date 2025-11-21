package com.restaurant.ui;

import com.restaurant.facade.RestaurantFacade;
import com.restaurant.notification.*;
import com.restaurant.domain.Order;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RestaurantFacade facade = new RestaurantFacade();
        facade.registerObserver(new KitchenDisplay());
        facade.registerObserver(new WaiterScreen());

        Order currentOrder = null;

        System.out.println("=== Welcome to our Restaurant ===");

        while (true) {
            System.out.println("\n=============================");
            System.out.println("1) Create New Order");
            System.out.println("2) Show Menu");
            System.out.println("3) Add Item to Order");
            System.out.println("4) Add Addon to Last Item");
            System.out.println("5) View Current Order Items");
            System.out.println("6) Checkout Order");
            System.out.println("7) List All Orders");
            System.out.println("0) Exit");
            System.out.println("=============================");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {

                case "1":
                    currentOrder = facade.createOrder();
                    System.out.println("New order created. ID: " + currentOrder.getId());
                    System.out.println("\nYou must add at least one item to the order.");
                    while (currentOrder.getItems().isEmpty()) {
                        System.out.print("Enter item ID to add: ");
                        String itemId = sc.nextLine().trim();

                        System.out.print("Quantity: ");
                        int qty = Integer.parseInt(sc.nextLine().trim());

                        if (facade.addItemToOrderAnyMenu(currentOrder, itemId, qty)) {
                            System.out.println("Item added!");
                        } else {
                            System.out.println("Invalid item ID. Try again.");
                        }
                    }

                    break;

                case "2":
                    System.out.print("Enter menu name (main/vegetarian / nonvegetarian / kids): ");
                    String menuName = sc.nextLine().trim().toLowerCase();
                    facade.showMenu(menuName);
                    break;

                case "3":
                    if (currentOrder == null) {
                        System.out.println("Create an order first");
                        break;
                    }

                    System.out.print("Enter item ID: ");
                    String itemId = sc.nextLine().trim();

                    System.out.print("Quantity: ");
                    int qty = Integer.parseInt(sc.nextLine().trim());

                    if (facade.addItemToOrderAnyMenu(currentOrder, itemId, qty)) {
                        System.out.println("Item added successfully");
                    } else {
                        System.out.println("Could not find item with that ID.");
                    }
                    break;

                case "4":
                    if (currentOrder == null) {
                        System.out.println("Create an order first!");
                        break;
                    }

                    System.out.print("Addon (CHEESE, SPICY, BACON, MUSHROOM): ");
                    String addon = sc.nextLine().trim();

                    if (facade.addAddonToLastItem(currentOrder, addon)) {
                        System.out.println("Addon applied!");
                    } else {
                        System.out.println("Failed to apply addon.");
                    }
                    break;

                case "5":
                    if (currentOrder == null) {
                        System.out.println("Create an order first");
                        break;
                    }
                    facade.showOrderItems(currentOrder);
                    break;

                case "6":
                    if (currentOrder == null) {
                        System.out.println("Create an order first!");
                        break;
                    }

                    System.out.print("Payment method (CASH / CARD / WALLET): ");
                    String pay = sc.nextLine().trim();

                    String data = "";
                    if (pay.equalsIgnoreCase("CARD") || pay.equalsIgnoreCase("WALLET")) {
                        System.out.print("Enter payment info (card number / wallet ID): ");
                        data = sc.nextLine();
                    }

                    boolean success = facade.checkout(currentOrder, pay, data);

                    if (success) {
                        System.out.println("Order completed!");
                        currentOrder = null;
                    } else {
                        System.out.println("Payment failed.");
                    }
                    break;

                case "7":
                    facade.listAllOrders();
                    break;

                case "0":
                    System.out.println("Goodbye, see you again");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
