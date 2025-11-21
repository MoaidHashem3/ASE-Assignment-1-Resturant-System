package com.restaurant.structure;

public abstract class MenuComponent {
    public void add(MenuComponent c) {
        throw new UnsupportedOperationException();
    }
    public void remove(MenuComponent c) {
        throw new UnsupportedOperationException();
    }
    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }
    public String getDescription() {
        throw new UnsupportedOperationException();
    }
    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    public void print(String indent) {
        throw new UnsupportedOperationException();
    }
}
