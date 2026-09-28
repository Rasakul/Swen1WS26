package at.technikum.birds;

import at.technikum.birds.interfaces.Flyable;
import at.technikum.birds.models.Bird;
import at.technikum.birds.models.Colibri;
import at.technikum.birds.models.Duck;
import at.technikum.birds.models.Goose;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Duck duck = new Duck("Donald");

        duck.setName("Donald");
        System.out.println(duck.getName());

        duck.toString();

        // TODO Colibri is a Bird,
        // but not Singable

        Bird goose = new Goose(false);
        Bird colibri = new Colibri(true);
        List<Flyable> birds = Arrays.asList(duck, goose, colibri);
        fly(birds);
    }

    public static void fly(List<Flyable> flyables){
        for (Flyable flyable : flyables) {
            flyable.fly();
        }
    }

}
