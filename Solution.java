import java.util.*;

public class ExtremeCornersAlternate {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static void optimal(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();

                // even level → print first, odd level → print last
                if ((level % 2 == 0 && i == 0) || (level % 2 == 1 && i == size - 1)) {
                    System.out.print(curr.val + " ");
                }

                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            level++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /*
                1
               / \
              2   3
             / \ / \
            4  5 6  7
           / \
          8   9
        */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);

        System.out.print("Brute Force: ");
        bruteForce(root);

        System.out.print("Optimal:     ");
        optimal(root);
    }
}
