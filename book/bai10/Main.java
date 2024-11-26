package book.bai10;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Test với mảng 1,000 phần tử
        int size1 = 1000;
        int[] arr1 = generateRandomArray(size1);

        // Bài tập 1: So sánh các thuật toán sắp xếp
        System.out.println("Comparing sorting algorithms for array size: " + size1);
        compareSortingAlgorithms(arr1);

        // Test với mảng 10,000 phần tử
        int size2 = 10000;
        int[] arr2 = generateRandomArray(size2);
        System.out.println("\nComparing sorting algorithms for array size: " + size2);
        compareSortingAlgorithms(arr2);

        // Test với mảng 100,000 phần tử
        int size3 = 100000;
        int[] arr3 = generateRandomArray(size3);
        System.out.println("\nComparing sorting algorithms for array size: " + size3);
        compareSortingAlgorithms(arr3);

        // Bài tập 2: So sánh thuật toán Insertion Sort và QuickSort với mảng nhỏ (dưới 20 phần tử)
        System.out.println("\nComparing Insertion Sort and QuickSort for small array (size < 20):");
        int[] smallArr = generateRandomArray(15);  // Mảng nhỏ hơn 20 phần tử
        compareInsertionSortAndQuickSort(smallArr);
    }

    // Hàm sinh mảng ngẫu nhiên
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(1000);  // Giá trị trong phạm vi từ 0 đến 999
        }
        return array;
    }

    // Hàm so sánh các thuật toán sắp xếp
    public static void compareSortingAlgorithms(int[] arr) {
        // Copy mảng gốc để tránh sửa đổi mảng gốc
        int[] arrForMergeSort = arr.clone();
        int[] arrForQuickSort = arr.clone();
        int[] arrForHeapSort = arr.clone();
        int[] arrForBubbleSort = arr.clone();

        // Merge Sort
        long startTime = System.nanoTime();
        mergeSort(arrForMergeSort);
        long endTime = System.nanoTime();
        System.out.println("Merge Sort: " + (endTime - startTime) + " ns");

        // QuickSort
        startTime = System.nanoTime();
        quickSort(arrForQuickSort, 0, arrForQuickSort.length - 1);
        endTime = System.nanoTime();
        System.out.println("QuickSort: " + (endTime - startTime) + " ns");

        // Heap Sort
        startTime = System.nanoTime();
        heapSort(arrForHeapSort);
        endTime = System.nanoTime();
        System.out.println("Heap Sort: " + (endTime - startTime) + " ns");

        // Bubble Sort
        startTime = System.nanoTime();
        bubbleSort(arrForBubbleSort);
        endTime = System.nanoTime();
        System.out.println("Bubble Sort: " + (endTime - startTime) + " ns");
    }

    // Thuật toán Merge Sort
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // Thuật toán QuickSort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Thuật toán Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Xây dựng max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Lấy phần tử lớn nhất và đặt vào cuối mảng
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    // Thuật toán Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // Bài tập 2: So sánh thuật toán Insertion Sort và QuickSort với mảng nhỏ
    public static void compareInsertionSortAndQuickSort(int[] arr) {
        // Insertion Sort
        long startTime = System.nanoTime();
        insertionSort(arr.clone());
        long endTime = System.nanoTime();
        System.out.println("Insertion Sort: " + (endTime - startTime) + " ns");

        // QuickSort
        startTime = System.nanoTime();
        quickSort(arr.clone(), 0, arr.length - 1);
        endTime = System.nanoTime();
        System.out.println("QuickSort: " + (endTime - startTime) + " ns");
    }

    // Thuật toán Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}
