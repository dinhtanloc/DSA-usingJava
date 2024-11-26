package book.bai9;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Bài tập 1: Sinh ngẫu nhiên 10,000 số nguyên trong phạm vi 0-9 và lưu trữ vào cây nhị phân tìm kiếm
        System.out.println("Exercise 1: Generate 10,000 random integers and store in a binary search tree.");
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 10000; i++) {
            bst.insert((int) (Math.random() * 10));  // Số ngẫu nhiên trong phạm vi 0-9
        }
        bst.displayFrequencies();

        // Bài tập 2: Đếm số lượng cạnh trong cây nhị phân tìm kiếm
        System.out.println("\nExercise 2: Count the number of edges in the tree.");
        int edgeCount = bst.countEdges();
        System.out.println("Number of edges in the tree: " + edgeCount);

        // Bài tập 3: Đọc các từ từ file và lưu vào cây nhị phân tìm kiếm
        System.out.println("\nExercise 3: Read words from file and store in binary search tree.");
        try {
            bst.clear();
            File file = new File("sample.txt"); // Đảm bảo rằng bạn có file "sample.txt"
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String word = scanner.next();
                bst.insert(word.toLowerCase());  // Chuyển đổi thành chữ thường để không phân biệt chữ hoa và chữ thường
            }
            bst.displayFrequencies();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        // Bài tập 4: Biểu thức toán học trong cây nhị phân tìm kiếm
        System.out.println("\nExercise 4: Evaluate arithmetic expression using binary search tree.");
        String expression = "2+3*4/5";
        BinarySearchTree<String> exprTree = new BinarySearchTree<>();
        exprTree.insert(expression);
        System.out.println("Expression result: " + evaluateExpression(expression));
    }

    // Hàm để đánh giá biểu thức toán học (Bài tập 4)
    public static double evaluateExpression(String expression) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = expression.split("(?=[-+*/()])|(?<=[-+*/()])");
        for (String token : tokens) {
            if (token.matches("[0-9]+")) {
                stack.push(Double.parseDouble(token));
            } else if (token.equals("+")) {
                double b = stack.pop();
                double a = stack.pop();
                stack.push(a + b);
            } else if (token.equals("-")) {
                double b = stack.pop();
                double a = stack.pop();
                stack.push(a - b);
            } else if (token.equals("*")) {
                double b = stack.pop();
                double a = stack.pop();
                stack.push(a * b);
            } else if (token.equals("/")) {
                double b = stack.pop();
                double a = stack.pop();
                stack.push(a / b);
            }
        }
        return stack.pop();
    }

    // Lớp BinarySearchTree
    static class BinarySearchTree<T extends Comparable<T>> {
        private Node<T> root;

        // Node của cây
        private static class Node<T> {
            T data;
            Node<T> left, right;

            Node(T data) {
                this.data = data;
                left = right = null;
            }
        }

        // Chèn một phần tử vào cây
        public void insert(T data) {
            root = insertRec(root, data);
        }

        private Node<T> insertRec(Node<T> root, T data) {
            if (root == null) {
                root = new Node<>(data);
                return root;
            }
            if (data.compareTo(root.data) < 0) {
                root.left = insertRec(root.left, data);
            } else if (data.compareTo(root.data) > 0) {
                root.right = insertRec(root.right, data);
            }
            return root;
        }

        // Hiển thị số lần xuất hiện của mỗi phần tử trong cây
        public void displayFrequencies() {
            Map<T, Integer> frequencies = new HashMap<>();
            countFrequencies(root, frequencies);
            frequencies.forEach((key, value) -> System.out.println(key + ": " + value));
        }

        private void countFrequencies(Node<T> node, Map<T, Integer> frequencies) {
            if (node == null) {
                return;
            }
            frequencies.put(node.data, frequencies.getOrDefault(node.data, 0) + 1);
            countFrequencies(node.left, frequencies);
            countFrequencies(node.right, frequencies);
        }

        // Đếm số lượng cạnh trong cây
        public int countEdges() {
            return countEdgesRec(root);
        }

        private int countEdgesRec(Node<T> node) {
            if (node == null) {
                return 0;
            }
            int leftEdges = countEdgesRec(node.left);
            int rightEdges = countEdgesRec(node.right);
            return leftEdges + rightEdges + (node.left != null || node.right != null ? 1 : 0);
        }

        // Xóa tất cả phần tử trong cây
        public void clear() {
            root = null;
        }
    }
}
