package at.technikum.birds.models;

import at.technikum.birds.interfaces.Singable;

public class Duck extends Bird implements Singable {

    private String name;

    public Duck(String name) {
        super(true);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean canSing() {
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Duck duck = (Duck) obj;
        return name != null ? name.equals(duck.name) : duck.name == null;
    }
}
