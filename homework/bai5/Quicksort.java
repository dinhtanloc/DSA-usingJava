package homework.bai5;

public class Quicksort {
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
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

    static void swap(int[] arr, int v1, int v2) {
        int temp = arr[v1];
        arr[v1] = arr[v2];
        arr[v2] = temp;
    }
    
}
