package lw03.unguided;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("===== Enrollment Checks =====");

        Map<String, Integer> enrollment = new LinkedHashMap<>();
        int failedEnroll = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(" ", 3);
            String operation = parts[0];
            String course = parts[1];

            if (operation.equals("REGISTER")) {
                int jumlah = Integer.parseInt(parts[2]);
                if (jumlah <= 0) {
                    failedEnroll++;
                } else {
                    enrollment.put(course, enrollment.getOrDefault(course, 0) + jumlah);
                }
            } else if (operation.equals("WITHDRAW")) {
                int jumlah = Integer.parseInt(parts[2]);
                Integer jumlahMahasiswa = enrollment.get(course);
                if (jumlah <= 0 || jumlahMahasiswa == null || jumlahMahasiswa < jumlah) {
                    failedEnroll++;
                } else {
                    enrollment.put(course, jumlahMahasiswa - jumlah);
                }
            } else if (operation.equals("CHECK")) {
                if (enrollment.containsKey(course)) {
                    System.out.println(course + ": " + enrollment.get(course) + " students");
                } else {
                    System.out.println(course + ": Not found");
                }
            }
        }

        System.out.println();
        System.out.println("===== Final Enrollment =====");
        for (Map.Entry<String, Integer> entry : enrollment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " students");
        }
        System.out.println();
        System.out.println("Rejected operations: " + failedEnroll);
    }
}