package lw04.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("cards.txt"));

        int n = scanner.nextInt();
        int[] cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] requests = new int[m];
        for (int i = 0; i < m; i++) {
            requests[i] = scanner.nextInt();
        }

        sortCards(cards, 2);

        for (int i = 0; i < cards.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(cards[i]);
        }
        System.out.println();

        for (int request : requests) {
            int index = findCard(cards, request);
            if (index != -1) {
                System.out.println(request + ": FOUND at index " + index);
            } else {
                System.out.println(request + ": NOT FOUND");
            }
        }
    }

    private static void sortCards(int[] arr, int passes) {
        for (int i = 0; i < passes; i++) {
            int idx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[idx]) {
                    idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }
    }

    private static int findCard(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}