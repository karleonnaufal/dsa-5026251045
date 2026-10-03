package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        runProblem1();
        runProblem2();
        runProblem3();
    }

    private static void runProblem1() throws FileNotFoundException {
        List<String> playlist = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("playlist.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" ", 3);
                String operation = parts[0];

                if (operation.equals("ADD")) {
                    playlist.add(parts[1]);
                } else if (operation.equals("INSERT")) {
                    int index = Integer.parseInt(parts[1]);
                    playlist.add(index, parts[2]);
                } else if (operation.equals("REMOVE")) {
                    playlist.remove(parts[1]);
                }
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
        System.out.println(" ");
    }

    private static void runProblem2() throws FileNotFoundException {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;

        try (Scanner scanner = new Scanner(new File("participants.txt"))) {
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    continue;
                }

                if (!participants.add(name)) {
                    duplicateCount++;
                }
            }
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int index = 1;
        for (String name : participants) {
            System.out.println(index + ". " + name);
            index++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);
        System.out.println(" ");
    }

    private static void runProblem3() throws FileNotFoundException {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        try (Scanner scanner = new Scanner(new File("inventory.txt"))) {
            while (scanner.hasNext()) {
                String type = scanner.next();
                String product = scanner.next();
                int quantity = scanner.nextInt();

                if (type.equals("ADD")) {
                    inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
                } else if (type.equals("SELL")) {
                    Integer stock = inventory.get(product);
                    if (stock == null || stock < quantity) {
                        failedSales++;
                    } else {
                        inventory.put(product, stock - quantity);
                    }
                }
            }
        }

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}