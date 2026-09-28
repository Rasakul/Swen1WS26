package at.technikum.birds.models;

import at.technikum.birds.interfaces.Flyable;

public abstract class Bird implements Flyable {

    private boolean canFly;

    public Bird(boolean canFly) {
        this.canFly = canFly;
    }

    @Override
    public void fly() {
        if (canFly) {
            System.out.println("I fly!");
        } else {
            System.out.println("I cant fly");
        }
    }

}
