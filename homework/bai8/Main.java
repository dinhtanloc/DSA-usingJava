package homework.bai8;
import java.util.Random;
import java.util.Stack;

class BinarySearchTree {
    class Node {
        int data;
        Node left, right;

        Node(int value) {
            data = value;
            left = right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Thêm một nút vào cây
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value < root.data)
            root.left = insertRec(root.left, value);
        else if (value > root.data)
            root.right = insertRec(root.right, value);
        return root;
    }

    // Inorder Traversal
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    // Đếm số lần xuất hiện của các số từ 0-9
    public void countOccurrences() {
        int[] count = new int[10];
        countOccurrencesRec(root, count);
        for (int i = 0; i < 10; i++) {
            System.out.println("Số " + i + " xuất hiện " + count[i] + " lần.");
        }
    }

    private void countOccurrencesRec(Node root, int[] count) {
        if (root != null) {
            count[root.data]++;
            countOccurrencesRec(root.left, count);
            countOccurrencesRec(root.right, count);
        }
    }

    // Bài 2: Đếm số cạnh trên cây
    public int countEdges() {
        return countEdgesRec(root);
    }

    private int countEdgesRec(Node root) {
        if (root == null)
            return 0;
        int leftEdges = countEdgesRec(root.left);
        int rightEdges = countEdgesRec(root.right);
        return leftEdges + rightEdges + (root.left != null ? 1 : 0) + (root.right != null ? 1 : 0);
    }

    // Bài 3: Lưu biểu thức toán học và tính giá trị
    public int evaluateExpressionTree() {
        return evaluateExpressionTreeRec(root);
    }

    private int evaluateExpressionTreeRec(Node root) {
        if (root == null)
            return 0;

        // Nếu là lá, trả về giá trị
        if (root.left == null && root.right == null)
            return root.data;

        // Tính toán giá trị của cây con
        int leftValue = evaluateExpressionTreeRec(root.left);
        int rightValue = evaluateExpressionTreeRec(root.right);

        // Thực hiện phép tính
        switch (root.data) {
            case '+':
                return leftValue + rightValue;
            case '-':
                return leftValue - rightValue;
            case '*':
                return leftValue * rightValue;
            case '/':
                return leftValue / rightValue;
        }
        return 0;
    }

    // Thêm biểu thức vào cây nhị phân
    public void buildExpressionTree(String postfix) {
        Stack<Node> stack = new Stack<>();
        for (char c : postfix.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(new Node(c - '0'));
            } else {
                Node operator = new Node(c);
                operator.right = stack.pop();
                operator.left = stack.pop();
                stack.push(operator);
            }
        }
        root = stack.pop();
    }
}

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // ---------------- Bài 1 ----------------
        System.out.println("Bài 1: Khởi tạo và đếm số lần xuất hiện:");
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            bst.insert(rand.nextInt(10)); // Chèn các số từ 0 đến 9
        }
        System.out.println("Inorder traversal của cây:");
        bst.inorder();
        System.out.println("\nĐếm số lần xuất hiện:");
        bst.countOccurrences();

        // ---------------- Bài 2 ----------------
        System.out.println("\nBài 2: Đếm số cạnh trên cây:");
        System.out.println("Số cạnh trên cây: " + bst.countEdges());

        // ---------------- Bài 3 ----------------
        System.out.println("\nBài 3: Tính biểu thức toán học:");
        BinarySearchTree expressionTree = new BinarySearchTree();
        String postfix = "23+45*+"; // Biểu thức hậu tố: (2 + 3) + (4 * 5)
        expressionTree.buildExpressionTree(postfix);
        System.out.println("Giá trị của biểu thức: " + expressionTree.evaluateExpressionTree());
    }
}
