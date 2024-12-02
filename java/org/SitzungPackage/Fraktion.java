package org.SitzungPackage;

public class Fraktion {
    private String name;

    public Fraktion(String name) {
        this.name = name;
    }

    public Fraktion() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fraktion: " + getName();
    }
}

