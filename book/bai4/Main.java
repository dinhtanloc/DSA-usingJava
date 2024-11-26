package book.bai4;
import java.util.Random;

public class Main {
    private static int compCount = 0; // To track comparisons

    // Sequential search for the nth occurrence of an item
    public static int seqSearchNth(int[] arr, int target, int occurrence) {
        int count = 0;
        compCount = 0; // Reset comparison count
        for (int i = 0; i < arr.length; i++) {
            compCount++;
            if (arr[i] == target) {
                count++;
                if (count == occurrence) {
                    return i; // Return the index of nth occurrence
                }
            }
        }
        return -1; // Return -1 if occurrence not found
    }

    // Sequential search for the last occurrence of an item
    public static int seqSearchLast(int[] arr, int target) {
        int lastIndex = -1;
        compCount = 0; // Reset comparison count
        for (int i = 0; i < arr.length; i++) {
            compCount++;
            if (arr[i] == target) {
                lastIndex = i; // Update last found index
            }
        }
        return lastIndex;
    }

    // Binary search (will fail on unsorted data)
    public static int binSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        compCount = 0; // Reset comparison count
        while (low <= high) {
            compCount++;
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1; // Return -1 if target not found
    }

    // Main method for testing
    public static void main(String[] args) {
        // Generate an array of 1000 random integers
        Random random = new Random();
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000); // Random integers between 0-999
        }

        int target = 734; // Number to search for

        // Test sequential search for nth occurrence
        int nthOccurrence = 3;
        int indexNth = seqSearchNth(arr, target, nthOccurrence);
        System.out.println("Sequential search for " + nthOccurrence + "rd occurrence of " + target + ": " + indexNth);
        System.out.println("Comparisons for nth occurrence: " + compCount);

        // Test sequential search for last occurrence
        int indexLast = seqSearchLast(arr, target);
        System.out.println("Sequential search for last occurrence of " + target + ": " + indexLast);
        System.out.println("Comparisons for last occurrence: " + compCount);

        // Test binary search on unsorted data
        int indexBinary = binSearch(arr, target);
        System.out.println("Binary search result on unsorted data for " + target + ": " + indexBinary);
        System.out.println("Comparisons for binary search (unsorted data): " + compCount);

        // Sort the array and test binary search again
        java.util.Arrays.sort(arr); // Sorting the array
        indexBinary = binSearch(arr, target);
        System.out.println("Binary search result on sorted data for " + target + ": " + indexBinary);
        System.out.println("Comparisons for binary search (sorted data): " + compCount);
    }
}

