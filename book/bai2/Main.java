package book.bai2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Bài 1: Lớp Course theo dõi điểm của một khóa học
class Course {
    private double[] grades;  // Mảng lưu trữ điểm số
    private int count;        // Số lượng điểm đã nhập

    public Course(int size) {
        grades = new double[size];  // Khởi tạo mảng với kích thước cho trước
        count = 0;                   // Ban đầu chưa có điểm nào
    }

    // Phương thức để thêm điểm vào mảng
    public void addGrade(double grade) {
        if (count < grades.length) {
            grades[count++] = grade;
        } else {
            System.out.println("Maximum grades reached!");
        }
    }

    // Tính điểm trung bình
    public double getAverage() {
        if (count == 0) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += grades[i];
        }
        return sum / count;
    }

    // Tìm điểm cao nhất
    public double getHighestGrade() {
        if (count == 0) {
            return 0;
        }
        double highest = grades[0];
        for (int i = 1; i < count; i++) {
            if (grades[i] > highest) {
                highest = grades[i];
            }
        }
        return highest;
    }

    // Tìm điểm thấp nhất
    public double getLowestGrade() {
        if (count == 0) {
            return 0;
        }
        double lowest = grades[0];
        for (int i = 1; i < count; i++) {
            if (grades[i] < lowest) {
                lowest = grades[i];
            }
        }
        return lowest;
    }
}

// Bài 2: Lớp School theo dõi nhiều khóa học
class School {
    private Map<String, Course> courses;

    public School() {
        courses = new HashMap<>();
    }

    // Thêm một khóa học
    public void addCourse(String courseName, int size) {
        courses.put(courseName, new Course(size));
    }

    // Thêm điểm cho một khóa học
    public void addGrade(String courseName, double grade) {
        Course course = courses.get(courseName);
        if (course != null) {
            course.addGrade(grade);
        } else {
            System.out.println("Course not found!");
        }
    }

    // Tính điểm trung bình của một khóa học
    public double getAverage(String courseName) {
        Course course = courses.get(courseName);
        return (course != null) ? course.getAverage() : 0;
    }

    // Tính điểm cao nhất của một khóa học
    public double getHighestGrade(String courseName) {
        Course course = courses.get(courseName);
        return (course != null) ? course.getHighestGrade() : 0;
    }

    // Tính điểm thấp nhất của một khóa học
    public double getLowestGrade(String courseName) {
        Course course = courses.get(courseName);
        return (course != null) ? course.getLowestGrade() : 0;
    }
}

// Bài 3: Sử dụng ArrayList thay cho mảng trong Course và kiểm tra hiệu suất
class CourseWithArrayList {
    private ArrayList<Double> grades;

    public CourseWithArrayList() {
        grades = new ArrayList<>();
    }

    // Thêm điểm vào ArrayList
    public void addGrade(double grade) {
        grades.add(grade);
    }

    // Tính điểm trung bình
    public double getAverage() {
        if (grades.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    // Tìm điểm cao nhất
    public double getHighestGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        double highest = grades.get(0);
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    // Tìm điểm thấp nhất
    public double getLowestGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        double lowest = grades.get(0);
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }
}

// Bài 4: Mô phỏng ArrayList bằng cách sử dụng mảng
class MyArrayList<T> {
    private Object[] items;
    private int size;

    public MyArrayList() {
        items = new Object[10];
        size = 0;
    }

    public void add(T item) {
        if (size == items.length) {
            items = Arrays.copyOf(items, size * 2);
        }
        items[size++] = item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) items[index];
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        size--;
    }

    public int size() {
        return size;
    }
}

// Chương trình kiểm thử
public class Main {
    public static void main(String[] args) {
        // Bài 1: Kiểm thử lớp Course
        System.out.println("==== Test Course ====");
        Course course = new Course(5);
        course.addGrade(85.5);
        course.addGrade(90.0);
        course.addGrade(78.0);
        course.addGrade(88.5);
        course.addGrade(92.0);

        System.out.println("Average grade: " + course.getAverage());
        System.out.println("Highest grade: " + course.getHighestGrade());
        System.out.println("Lowest grade: " + course.getLowestGrade());

        // Bài 2: Kiểm thử lớp School
        System.out.println("\n==== Test School ====");
        School school = new School();
        school.addCourse("Math", 5);
        school.addCourse("Science", 5);

        school.addGrade("Math", 85.5);
        school.addGrade("Math", 90.0);
        school.addGrade("Math", 78.0);
        school.addGrade("Science", 88.5);
        school.addGrade("Science", 92.0);

        System.out.println("Math - Average: " + school.getAverage("Math"));
        System.out.println("Math - Highest: " + school.getHighestGrade("Math"));
        System.out.println("Math - Lowest: " + school.getLowestGrade("Math"));
        System.out.println("Science - Average: " + school.getAverage("Science"));
        System.out.println("Science - Highest: " + school.getHighestGrade("Science"));
        System.out.println("Science - Lowest: " + school.getLowestGrade("Science"));

        // Bài 3: So sánh hiệu suất giữa Array và ArrayList
        System.out.println("\n==== Test Performance ====");
        int numGrades = 1000000;
        
        // Sử dụng mảng
        long start = System.nanoTime();
        Course arrayCourse = new Course(numGrades);
        for (int i = 0; i < numGrades; i++) {
            arrayCourse.addGrade(Math.random() * 100);
        }
        long end = System.nanoTime();
        System.out.println("Array implementation took: " + (end - start) + " nanoseconds");

        // Sử dụng ArrayList
        start = System.nanoTime();
        CourseWithArrayList arrayListCourse = new CourseWithArrayList();
        for (int i = 0; i < numGrades; i++) {
            arrayListCourse.addGrade(Math.random() * 100);
        }
        end = System.nanoTime();
        System.out.println("ArrayList implementation took: " + (end - start) + " nanoseconds");

        // Bài 4: Kiểm thử MyArrayList
        System.out.println("\n==== Test MyArrayList ====");
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            myArrayList.add(i);
        }
        
        System.out.println("Size: " + myArrayList.size());
        System.out.println("Item at index 5: " + myArrayList.get(5));

        myArrayList.remove(5);
        System.out.println("Size after removal: " + myArrayList.size());
    }
}
