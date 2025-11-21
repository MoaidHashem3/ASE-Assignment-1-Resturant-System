package com.restaurant.structure;


public class MenuLeaf extends MenuComponent {
    private final String id;
    private final String name;
    private final String description;
    private final double price;

    public MenuLeaf(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public double getPrice() { return price; }

    @Override
    public void print(String indent) {
        System.out.printf("%s- [%s] %s (%.2f)%n",
                indent,
                id,
                description,
                price);
    }
}
