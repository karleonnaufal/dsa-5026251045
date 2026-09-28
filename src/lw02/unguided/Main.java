package lw02.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static final int maxBorrow = 2;

    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        Scanner scanner = new Scanner(new File("borrowing.txt"));
        while (scanner.hasNext()) {
            String name = scanner.next();
            String bookTitle = scanner.next();

            requests.add(new String[]{name, bookTitle});

            if (findRecord(members, name) == null) {
                members.add(new String[]{name, "0"});
            }
        }

        Queue<String[]> queue = new LinkedList<>(requests);

        System.out.println("=== Successfully Processed Requests ===");
        while (!queue.isEmpty()) {
            String[] request = queue.poll();
            String[] member = findRecord(members, request[0]);
            String[] book = findRecord(books, request[1]);

            if (canBorrow(book, member)) {
                book[1] = String.valueOf(Integer.parseInt(book[1]) - 1);
                member[1] = String.valueOf(Integer.parseInt(member[1]) + 1);
                System.out.println(request[0] + " " + request[1]);
            } else {
                failed.push(request);
            }
        }

        System.out.println("=== Remaining Book Stock ===");
        for (String[] book : books) {
            System.out.println(book[0] + " : " + book[1]);
        }

        System.out.println("=== Failed Requests ===");
        while (!failed.isEmpty()) {
            String[] request = failed.pop();
            System.out.println(request[0] + " " + request[1]);
        }
    }

    private static String[] findRecord(LinkedList<String[]> list, String key) {
        for (String[] record : list) {
            if (record[0].equals(key)) {
                return record;
            }
        }
        return null;
    }

    private static boolean canBorrow(String[] book, String[] member) {
        return book != null
                && Integer.parseInt(book[1]) > 0
                && Integer.parseInt(member[1]) < maxBorrow;
    }
}