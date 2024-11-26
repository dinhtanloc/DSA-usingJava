package book.bai8;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Bài tập 1: Chuyển đổi ứng dụng Console sử dụng LinkedList với Iterator thành Windows Application
        System.out.println("Exercise 1: Linked List with Iterator");
        LinkedListExample linkedListExample = new LinkedListExample();
        linkedListExample.addElements();
        linkedListExample.displayList();

        // Bài tập 2: Flavius Josephus Problem với Circularly Linked List
        System.out.println("\nExercise 2: Flavius Josephus Problem");
        JosephusProblem josephusProblem = new JosephusProblem();
        int lastPerson = josephusProblem.solveJosephusProblem(40, 3); // 40 người, giết mỗi người thứ 3
        System.out.println("Last person remaining is at position: " + lastPerson);

        // Bài tập 3: Đọc VB.NET code và phân loại từ khóa, định danh, và literals
        System.out.println("\nExercise 3: Parse VB.NET Code");
        VbNetParser vbNetParser = new VbNetParser();
        vbNetParser.parseCode("Dim x As Integer = 10\nDim str As String = \"Hello\"\nIf x > 5 Then");
        vbNetParser.displayLists();

        // Bài tập 4: Implement ToArray for LinkedList class
        System.out.println("\nExercise 4: ToArray for LinkedList");
        LinkedList<Integer> intList = new LinkedList<>();
        intList.add(10);
        intList.add(20);
        intList.add(30);
        Integer[] array = toArray(intList);
        System.out.println("Converted LinkedList to Array: " + Arrays.toString(array));
    }

    // Bài tập 1: Sử dụng Iterator để duyệt qua danh sách liên kết
    static class LinkedListExample {
        private LinkedList<String> list;

        public LinkedListExample() {
            list = new LinkedList<>();
        }

        // Thêm phần tử vào danh sách
        public void addElements() {
            list.add("Apple");
            list.add("Banana");
            list.add("Cherry");
            list.add("Date");
        }

        // Duyệt qua danh sách sử dụng Iterator
        public void displayList() {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }

    // Bài tập 2: Giải quyết bài toán Flavius Josephus sử dụng Circularly Linked List
    static class JosephusProblem {
        static class Node {
            int position;
            Node next;
            Node(int position) {
                this.position = position;
                this.next = null;
            }
        }

        public int solveJosephusProblem(int n, int m) {
            // Tạo danh sách liên kết tròn
            Node head = new Node(1);
            Node temp = head;

            for (int i = 2; i <= n; i++) {
                temp.next = new Node(i);
                temp = temp.next;
            }
            temp.next = head; // Tạo vòng tròn

            // Bắt đầu quá trình giết người
            Node current = head;
            Node prev = null;
            while (current.next != current) { // Lặp cho đến khi chỉ còn 1 người
                for (int i = 1; i < m; i++) {
                    prev = current;
                    current = current.next;
                }
                prev.next = current.next; // Loại bỏ người thứ m
                current = current.next;
            }

            return current.position; // Người cuối cùng còn lại
        }
    }

    // Bài tập 3: Phân loại từ khóa, định danh, literals trong VB.NET code
    static class VbNetParser {
        private LinkedList<String> reservedWords;
        private LinkedList<String> identifiers;
        private LinkedList<String> literals;

        public VbNetParser() {
            reservedWords = new LinkedList<>();
            identifiers = new LinkedList<>();
            literals = new LinkedList<>();
            loadReservedWords();
        }

        // Tải từ khóa VB.NET
        private void loadReservedWords() {
            reservedWords.add("Dim");
            reservedWords.add("As");
            reservedWords.add("If");
            reservedWords.add("Then");
        }

        // Phân tích mã VB.NET
        public void parseCode(String code) {
            String[] lines = code.split("\n");
            for (String line : lines) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (reservedWords.contains(word)) {
                        reservedWords.add(word);
                    } else if (word.matches("[A-Za-z_][A-Za-z0-9_]*")) {
                        identifiers.add(word);
                    } else if (word.matches("\"[^\"]*\"")) {
                        literals.add(word);
                    }
                }
            }
        }

        // Hiển thị các danh sách từ khóa, định danh và literals
        public void displayLists() {
            System.out.println("Reserved Words: " + reservedWords);
            System.out.println("Identifiers: " + identifiers);
            System.out.println("Literals: " + literals);
        }
    }

    // Bài tập 4: Chuyển đổi LinkedList thành Array
    public static <T> T[] toArray(LinkedList<T> list) {
        T[] array = (T[]) new Object[list.size()];
        return list.toArray(array);
    }
}
