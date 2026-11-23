package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        // Write your main code here
        String[] values = read.nextLine().trim().split(" ");
        int target = read.nextInt();

        BinarySearchTree tree = new BinarySearchTree();
        for (String value : values) {
            tree.insert(Integer.parseInt(value));
        }

        System.out.print("Preorder : ");
        tree.preOrder();
        System.out.print("Inorder : ");
        tree.inOrder();
        System.out.print("Postorder : ");
        tree.postOrder();
        System.out.println("Search " + target + " : " + tree.search(target));
        System.out.println("Minimum : " + tree.findMin());
        System.out.println("Maximum : " + tree.findMax());

        read.close();
    }
}
