package com.restaurant.structure;

import java.util.ArrayList;
import java.util.List;

public class MenuComposite extends MenuComponent {
    private final String name;
    private final String description;
    private final List<MenuComponent> children = new ArrayList<>();

    public MenuComposite(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(MenuComponent c) {
        children.add(c);
    }

    @Override
    public void remove(MenuComponent c) {
        children.remove(c);
    }

    @Override
    public MenuComponent getChild(int i) {
        return children.get(i);
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public void print(String indent) {
        System.out.printf("%s+ %s : %s%n", indent, name, description);
        for (MenuComponent c : children) {
            c.print(indent + "  ");
        }
    }

    public List<MenuComponent> getChildren() {
        return new ArrayList<>(children);
    }
}
