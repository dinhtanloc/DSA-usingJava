package homework.bai5;
public class Main {
    public static void main(String[] args) {
        int[] arr = {12, 34, 54, 2, 3, 7, 8, 9, 1, 5, 6, 11, 13, 14, 10};

        // Bubble Sort
        int[] bubbleSortedArray = arr.clone();
        Bubblesort.bubbleSort(bubbleSortedArray);
        System.out.println("Bubble Sort: ");
        printArray(bubbleSortedArray);

        // Selection Sort
        int[] selectionSortedArray = arr.clone();
        Selectionsort.selectionSort(selectionSortedArray);
        System.out.println("Selection Sort: ");
        printArray(selectionSortedArray);

        // Insertion Sort
        int[] insertionSortedArray = arr.clone();
        Insertionsort.insertionSort(insertionSortedArray);
        System.out.println("Insertion Sort: ");
        printArray(insertionSortedArray);

        // Merge Sort
        int[] mergeSortedArray = arr.clone();
        Mergesort.mergeSort(mergeSortedArray, 0, mergeSortedArray.length - 1);
        System.out.println("Merge Sort: ");
        printArray(mergeSortedArray);

        // Quick Sort
        int[] quickSortedArray = arr.clone();
        Quicksort.quickSort(quickSortedArray, 0, quickSortedArray.length - 1);
        System.out.println("Quick Sort: ");
        printArray(quickSortedArray);

        // Shell Sort
        int[] shellSortedArray = arr.clone();
        Shellsort.shellSort(shellSortedArray);
        System.out.println("Shell Sort: ");
        printArray(shellSortedArray);

        // Heap Sort
        int[] heapSortedArray = arr.clone();
        Heapsort.heapSort(heapSortedArray);
        System.out.println("Heap Sort: ");
        printArray(heapSortedArray);

        // Exchange Sort
        int[] exchangeSortedArray = arr.clone();
        Exchangesort.exchangeSort(exchangeSortedArray);
        System.out.println("Exchange Sort: ");
        printArray(exchangeSortedArray);
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
