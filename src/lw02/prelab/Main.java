package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        try (Scanner scanner = new Scanner(new File("transactions.txt"))) {
            while (scanner.hasNext()) {
                String name = scanner.next();
                String type = scanner.next();
                String amount = scanner.next();

                transactions.add(new String[]{name, type, amount});

                if (!containsCustomer(customers, name)) {
                    customers.add(new String[]{name, "0"});
                }
            }
        }

        Queue<String[]> queue = new LinkedList<>(transactions);
        Stack<String[]> failed = new Stack<>();

        while (!queue.isEmpty()) {
            String[] transaction = queue.poll();
            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] customer = findCustomer(customers, name);
            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    failed.push(transaction);
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failed.isEmpty()) {
            String[] transaction = failed.pop();
            System.out.println(transaction[0] + " " + transaction[1] + " " + transaction[2]);
        }
    }

    private static boolean containsCustomer(LinkedList<String[]> customers, String name) {
        return findCustomer(customers, name) != null;
    }

    private static String[] findCustomer(LinkedList<String[]> customers, String name) {
        for (String[] customer : customers) {
            if (customer[0].equals(name)) {
                return customer;
            }
        }
        return null;
    }
}