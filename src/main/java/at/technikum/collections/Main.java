package at.technikum.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //Initialisiere Person
        Person p1 = new Person(UUID.randomUUID(), "Person", "1", "test@mail", 1);
        Person p2 = new Person(UUID.randomUUID(), "Person", "1", "test@mail", 18);
        Person p3 = new Person(UUID.randomUUID(), "Person", "1", "test@mail", 65);
        Person p4 = new Person(UUID.randomUUID(), "Person", "1", "test@mail", 5);
        Person p5 = new Person(UUID.randomUUID(), "Person", "1", "test@mail", 81);
        Person p6 = new Person(UUID.randomUUID(), "Person", "1", "test@mail", 19);
        Person p7 = new Person(UUID.randomUUID(), "Person", "1", "test@mail", 16);
        Person p8 = new Person(UUID.randomUUID(), "Person", "1", "test@mail", 37);
        Person p9 = new Person(UUID.randomUUID(), "Person", "1", "test@mail", 50);
        Person p10 = new Person(UUID.randomUUID(), "Person", "1", "test@mail", 41);

        //Lege alle Persons in Liste
        List<Person> persons = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);

        //Lege alle Persons in Map
        Map<UUID, Person> personMap = new HashMap<>();

        persons.forEach(person -> personMap.put(person.getId(), person));

        //alternativ
        Map<UUID, Person> personMap2 = persons.stream()
                .collect(Collectors.toMap(Person::getId, person -> person));

        //iteriere über Liste & Map
        for (Person person : persons) {
            System.out.println(person);
        }

        personMap.values().forEach(person -> System.out.println(person));

        //alternativ
        personMap2.values().forEach(System.out::println);

        //Forme die Liste auf einen Stream um
        //Filtere im Stream auf Alter > 18
        //Mappe im Stream alle Personen auf Name
        //Gib jeden Namen auf der Konsole aus
        persons.stream()
                .filter(person -> person.getAge() > 18)
                .map(Person::getLastName)
                .forEach(System.out::println);

    }

}
