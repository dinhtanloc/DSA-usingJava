import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Random;

public class b5 {
    public static void main(String[] args) {
        StudentDictionary studs = new StudentDictionary();
        studs.put("SV001", new Student("SV001", 20, 7.5f));
        studs.put("SV002", new Student("SV002", 21, 8.3f));

        Enumeration<String> keys = studs.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            System.out.println(key + " => " + studs.get(key));
        }

        /*
        int total = 100;
        int[] arr = new int[total];
        randomize(arr);
        int time = 0;
        int[] cpy = new int[total];

        Timing timing = new Timing();
        System.arraycopy(arr, 0, cpy, 0, total);
        for (int i = 0; i < 100; i++) {
            timing.startTime();
            selectionSort(arr);
            timing.stopTime();
            time += timing.result();
        }
        System.out.println("Selection Sort: " + (time / 100f) + " ms");
        time = 0;
        System.arraycopy(cpy, 0, arr, 0, total);
        for (int i = 0; i < 100; i++) {
            timing.startTime();
            bubbleSort(arr);
            timing.stopTime();
            time += timing.result();
        }
        System.out.println("Bubble Sort: " + (time / 100f) + " ms");
        time = 0;
        System.arraycopy(cpy, 0, arr, 0, total);
        for (int i = 0; i < 100; i++) {
            timing.startTime();
            quickSort(arr, 0, arr.length - 1);
            timing.stopTime();
            time += timing.result();
        }
        System.out.println("Quick Sort: " + (time / 100f) + " ms");
        */
    }

    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            if (p > 1)
                quickSort(arr, low, p - 1);
            if (p + 1 < high)
                quickSort(arr, p + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int j = low;
        for (int i = low; i < high; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, j);
                j++;
            }
        }
        swap(arr, j, high);
        return j;
    }

    static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    static void randomize(int[] arr) {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(99);
        }
    }

    static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[smallest]) {
                    smallest = j;
                }
            }
            swap(array, i, smallest);
        }
    }

    static void swap(int[] arr, int v1, int v2) {
        int temp = arr[v1];
        arr[v1] = arr[v2];
        arr[v2] = temp;
    }
}

// Custom Hashtable for storing students
class StudentDictionary extends Hashtable<String, Student> { }

// Student class
class Student {
    private String id;
    private int age;
    private float gpa;

    public Student(String id, int age, float gpa) {
        this.id = id;
        this.age = age;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{id='" + id + "', age=" + age + ", gpa=" + gpa + "}";
    }
}
