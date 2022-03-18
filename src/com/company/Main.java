package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + count);

        List<String> conscripts = persons.stream()
                .filter(p -> p.getAge() >= 18 && p.getAge() < 27 && p.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("\nCписок фамилий призывников(список ограничен 10 фамилиями))\n" + conscripts);

        List<String> ableBodiedPopulation = persons.stream()
                .filter(p -> p.getEducation().equals(Education.HIGHER) && p.getAge() >= 18 &&
                        (p.getAge() < 65 && p.getSex().equals(Sex.MAN) ||
                                p.getAge() < 60 && p.getSex().equals(Sex.WOMAN)))
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("\nCписок потенциально работоспособных людей(список ограничен 10 фамилиями))\n" + ableBodiedPopulation);
    }
}