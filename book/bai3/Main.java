package book.bai3;

import java.io.*;
import java.util.*;
// import java.util.concurrent.TimeUnit;

// Bài 1: Sắp xếp các chuỗi từ file
class SortingAlgorithms {

    // Phương thức sắp xếp bằng thuật toán Bubble Sort
    public static void bubbleSort(List<String> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }

    // Phương thức sắp xếp bằng thuật toán Selection Sort
    public static void selectionSort(List<String> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).compareTo(list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            Collections.swap(list, i, minIndex);
        }
    }

    // Phương thức sắp xếp bằng thuật toán Insertion Sort
    public static void insertionSort(List<String> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            String key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    // Phương thức sắp xếp bằng thuật toán Merge Sort
    public static void mergeSort(List<String> list) {
        if (list.size() <= 1) {
            return;
        }
        List<String> left = new ArrayList<>();
        List<String> right = new ArrayList<>();
        int middle = list.size() / 2;

        for (int i = 0; i < middle; i++) {
            left.add(list.get(i));
        }
        for (int i = middle; i < list.size(); i++) {
            right.add(list.get(i));
        }

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    // Hợp nhất hai danh sách đã được sắp xếp
    private static void merge(List<String> list, List<String> left, List<String> right) {
        int leftIndex = 0, rightIndex = 0, listIndex = 0;
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) <= 0) {
                list.set(listIndex++, left.get(leftIndex++));
            } else {
                list.set(listIndex++, right.get(rightIndex++));
            }
        }

        while (leftIndex < left.size()) {
            list.set(listIndex++, left.get(leftIndex++));
        }
        while (rightIndex < right.size()) {
            list.set(listIndex++, right.get(rightIndex++));
        }
    }

    // Phương thức sắp xếp bằng thuật toán Quick Sort
    public static void quickSort(List<String> list) {
        quickSortHelper(list, 0, list.size() - 1);
    }

    private static void quickSortHelper(List<String> list, int low, int high) {
        if (low < high) {
            int pivot = partition(list, low, high);
            quickSortHelper(list, low, pivot - 1);
            quickSortHelper(list, pivot + 1, high);
        }
    }

    private static int partition(List<String> list, int low, int high) {
        String pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }
}

// Bài 2 & 3: So sánh thời gian của các thuật toán sắp xếp với mảng
public class Main {
    public static void main(String[] args) throws IOException {
        // Bài 1: Tạo và đọc file chuỗi
        List<String> stringsFromFile = generateRandomStrings();
        System.out.println("Sorting algorithms performance on 100 random strings:");
        sortAndTime(stringsFromFile);

        // Bài 2: So sánh thời gian sắp xếp mảng có sẵn
        System.out.println("\nSorting algorithms performance on 1000 sorted integers:");
        List<Integer> sortedIntegers = generateSortedIntegers();
        sortAndTimeIntegers(sortedIntegers);

        // Bài 3: So sánh thời gian sắp xếp mảng đảo ngược
        System.out.println("\nSorting algorithms performance on 1000 reverse-sorted integers:");
        List<Integer> reverseSortedIntegers = generateReverseSortedIntegers();
        sortAndTimeIntegers(reverseSortedIntegers);
    }

    // Tạo một danh sách chuỗi ngẫu nhiên
    private static List<String> generateRandomStrings() throws IOException {
        List<String> strings = new ArrayList<>();
        FileWriter writer = new FileWriter("strings.txt");
        for (int i = 0; i < 100; i++) {
            String randomString = generateRandomString();
            strings.add(randomString);
            writer.write(randomString + "\n");
        }
        writer.close();
        return strings;
    }

    private static String generateRandomString() {
        int length = (int) (Math.random() * 10) + 5;  // Tạo chuỗi ngẫu nhiên dài từ 5 đến 15 ký tự
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + Math.random() * 26));
        }
        return sb.toString();
    }

    // Đọc chuỗi từ file
    private static List<String> readStringsFromFile() throws IOException {
        List<String> strings = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("strings.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            strings.add(line);
        }
        reader.close();
        return strings;
    }

    // Sắp xếp và đo thời gian cho các thuật toán
    private static void sortAndTime(List<String> strings) {
        List<String> list = new ArrayList<>(strings);

        // Bubble Sort
        List<String> bubbleSortList = new ArrayList<>(strings);
        long start = System.nanoTime();
        SortingAlgorithms.bubbleSort(bubbleSortList);
        long end = System.nanoTime();
        System.out.println("BubbleSort: " + (end - start) + " ns");

        // Selection Sort
        List<String> selectionSortList = new ArrayList<>(strings);
        start = System.nanoTime();
        SortingAlgorithms.selectionSort(selectionSortList);
        end = System.nanoTime();
        System.out.println("SelectionSort: " + (end - start) + " ns");

        // Insertion Sort
        List<String> insertionSortList = new ArrayList<>(strings);
        start = System.nanoTime();
        SortingAlgorithms.insertionSort(insertionSortList);
        end = System.nanoTime();
        System.out.println("InsertionSort: " + (end - start) + " ns");

        // Merge Sort
        List<String> mergeSortList = new ArrayList<>(strings);
        start = System.nanoTime();
        SortingAlgorithms.mergeSort(mergeSortList);
        end = System.nanoTime();
        System.out.println("MergeSort: " + (end - start) + " ns");

        // Quick Sort
        List<String> quickSortList = new ArrayList<>(strings);
        start = System.nanoTime();
        SortingAlgorithms.quickSort(quickSortList);
        end = System.nanoTime();
        System.out.println("QuickSort: " + (end - start) + " ns");
    }

    // Tạo mảng số nguyên đã được sắp xếp
    private static List<Integer> generateSortedIntegers() {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            integers.add(i);
        }
        return integers;
    }

    // Tạo mảng số nguyên đã được sắp xếp đảo ngược
    private static List<Integer> generateReverseSortedIntegers() {
        List<Integer> integers = new ArrayList<>();
        for (int i = 999; i >= 0; i--) {
            integers.add(i);
        }
        return integers;
    }

    // So sánh thời gian sắp xếp với mảng số nguyên
    private static void sortAndTimeIntegers(List<Integer> integers) {
        List<Integer> list = new ArrayList<>(integers);

        // Bubble Sort
        List<Integer> bubbleSortList = new ArrayList<>(integers);
        long start = System.nanoTime();
        bubbleSort(bubbleSortList);
        long end = System.nanoTime();
        System.out.println("BubbleSort: " + (end - start) + " ns");

        // Selection Sort
        List<Integer> selectionSortList = new ArrayList<>(integers);
        start = System.nanoTime();
        selectionSort(selectionSortList);
        end = System.nanoTime();
        System.out.println("SelectionSort: " + (end - start) + " ns");

        // Insertion Sort
        List<Integer> insertionSortList = new ArrayList<>(integers);
        start = System.nanoTime();
        insertionSort(insertionSortList);
        end = System.nanoTime();
        System.out.println("InsertionSort: " + (end - start) + " ns");

        // Merge Sort
        List<Integer> mergeSortList = new ArrayList<>(integers);
        start = System.nanoTime();
        mergeSort(mergeSortList);
        end = System.nanoTime();
        System.out.println("MergeSort: " + (end - start) + " ns");

        // Quick Sort
        List<Integer> quickSortList = new ArrayList<>(integers);
        start = System.nanoTime();
        quickSort(quickSortList);
        end = System.nanoTime();
        System.out.println("QuickSort: " + (end - start) + " ns");
    }

    // Sắp xếp bằng thuật toán Bubble Sort
    private static void bubbleSort(List<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }

    // Sắp xếp bằng thuật toán Selection Sort
    private static void selectionSort(List<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j) < list.get(minIndex)) {
                    minIndex = j;
                }
            }
            Collections.swap(list, i, minIndex);
        }
    }

    // Sắp xếp bằng thuật toán Insertion Sort
    private static void insertionSort(List<Integer> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            int key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    // Sắp xếp bằng thuật toán Merge Sort
    private static void mergeSort(List<Integer> list) {
        if (list.size() <= 1) {
            return;
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int middle = list.size() / 2;

        for (int i = 0; i < middle; i++) {
            left.add(list.get(i));
        }
        for (int i = middle; i < list.size(); i++) {
            right.add(list.get(i));
        }

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    // Hợp nhất hai danh sách đã được sắp xếp
    private static void merge(List<Integer> list, List<Integer> left, List<Integer> right) {
        int leftIndex = 0, rightIndex = 0, listIndex = 0;
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) <= right.get(rightIndex)) {
                list.set(listIndex++, left.get(leftIndex++));
            } else {
                list.set(listIndex++, right.get(rightIndex++));
            }
        }

        while (leftIndex < left.size()) {
            list.set(listIndex++, left.get(leftIndex++));
        }
        while (rightIndex < right.size()) {
            list.set(listIndex++, right.get(rightIndex++));
        }
    }

    // Sắp xếp bằng thuật toán Quick Sort
    private static void quickSort(List<Integer> list) {
        quickSortHelper(list, 0, list.size() - 1);
    }

    private static void quickSortHelper(List<Integer> list, int low, int high) {
        if (low < high) {
            int pivot = partition(list, low, high);
            quickSortHelper(list, low, pivot - 1);
            quickSortHelper(list, pivot + 1, high);
        }
    }

    private static int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }
}
