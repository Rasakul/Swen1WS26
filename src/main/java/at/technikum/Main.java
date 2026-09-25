package at.technikum;

import java.util.Arrays;
import java.util.Collections;
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
