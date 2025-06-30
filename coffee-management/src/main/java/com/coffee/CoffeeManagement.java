package com.coffee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class CoffeeManagement {

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(CoffeeManagement.class);

        System.out.println("Вас приветствует система контроля кофе-машины!");
        Scanner scanner = new Scanner(System.in);

        Set<String> availableVolumes = Set.of("0,2", "0,3", "0,4");
        List<String> availableSorts = List.of("Бразилия", "Эфиопия");
        List<String> availableTypes = List.of("Эспрессо", "Американо", "Капучино");

        Random random = new Random();
        while (true) {
            System.out.println("Чтобы продолжить, введите имя пользователя, запустившего кофемашину:");
            String username = scanner.nextLine();

            String sortOfCoffee = availableSorts.get(random.nextInt(availableSorts.size()));
            String typeOfCoffee = availableTypes.get(random.nextInt(availableTypes.size()));

            System.out.println("Введите объем кофе:");
            String volume = scanner.nextLine();
            if (!availableVolumes.contains(volume)) {
                System.out.println("Выбран недопустимый объем");
                log.error("{} {}, {}: {}", username, sortOfCoffee, typeOfCoffee, volume);
            } else {
                System.out.println("Выбран сорт " + sortOfCoffee + " для пользователя " + username + ", объем " + volume);
                log.info("{} {}, {}: {}", username, sortOfCoffee, typeOfCoffee, volume);
            }
        }
    }
}
