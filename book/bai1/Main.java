package book.bai1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Test {
    private String studentName;
    private int testNumber;

    public Test(String studentName, int testNumber) {
        this.studentName = studentName;
        this.testNumber = testNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getTestNumber() {
        return testNumber;
    }

    @Override
    public String toString() {
        return "Test{Student Name='" + studentName + "', Test Number=" + testNumber + "}";
    }
}

// Lớp Collection để thực hiện các thao tác với danh sách các đối tượng
class Collection<T> {
    private List<T> items;

    public Collection() {
        items = new ArrayList<>();
    }

    // Thêm một phần tử vào collection
    public void insert(T item) {
        items.add(item);
    }

    // Kiểm tra xem một phần tử có trong collection hay không
    public boolean contains(T item) {
        return items.contains(item);
    }

    // Lấy chỉ số của phần tử trong collection
    public int indexOf(T item) {
        return items.indexOf(item);
    }

    // Xóa phần tử tại vị trí chỉ định
    public void removeAt(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    // Lấy phần tử tại vị trí chỉ định
    public T get(int index) {
        return items.get(index);
    }

    // Lấy kích thước của collection
    public int size() {
        return items.size();
    }

    // In tất cả các phần tử trong collection
    public void print() {
        for (T item : items) {
            System.out.println(item);
        }
    }

    // Xóa phần tử khỏi collection
    public boolean remove(T item) {
        return items.remove(item);
    }
}

// Lớp chính để mô phỏng ứng dụng quản lý bài kiểm tra
public class Main {
    private static Collection<Test> submittedTests = new Collection<>();
    private static Collection<Test> outForChecking = new Collection<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Turn in a test");
            System.out.println("2. Let student look at test");
            System.out.println("3. Return a test");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    // Turn in a test
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter test number: ");
                    int testNumber = scanner.nextInt();
                    scanner.nextLine();  // Consume newline character
                    Test test = new Test(name, testNumber);
                    submittedTests.insert(test);
                    System.out.println("Test submitted: " + test);
                    break;
                case 2:
                    // Let student look at test
                    System.out.print("Enter student name to check the test: ");
                    name = scanner.nextLine();
                    test = findTestByName(name, submittedTests);
                    if (test != null) {
                        submittedTests.remove(test);
                        outForChecking.insert(test);
                        System.out.println("Test is now out for checking: " + test);
                    } else {
                        System.out.println("Test not found.");
                    }
                    break;
                case 3:
                    // Return a test
                    System.out.print("Enter student name to return the test: ");
                    name = scanner.nextLine();
                    test = findTestByName(name, outForChecking);
                    if (test != null) {
                        outForChecking.remove(test);
                        submittedTests.insert(test);
                        System.out.println("Test returned to submitted: " + test);
                    } else {
                        System.out.println("Test not found.");
                    }
                    break;
                case 4:
                    // Exit
                    running = false;
                    System.out.println("Exiting... Returning all tests to submitted collection.");
                    while (outForChecking.size() > 0) {
                        Test returningTest = outForChecking.get(0);
                        outForChecking.removeAt(0);
                        submittedTests.insert(returningTest);
                    }
                    System.out.println("All tests returned. Final list of submitted tests:");
                    submittedTests.print();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static Test findTestByName(String name, Collection<Test> collection) {
        for (int i = 0; i < collection.size(); i++) {
            Test test = collection.get(i);
            if (test.getStudentName().equalsIgnoreCase(name)) {
                return test;
            }
        }
        return null;
    }
}
