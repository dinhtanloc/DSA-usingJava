package homework.bai1;
import java.util.*;

public class Main {

    // Generic method to find the largest and smallest numbers from a list of integers
    public static <T extends Number> void findLargestAndSmallest(List<T> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        T largest = numbers.get(0);
        T smallest = numbers.get(0);

        // Iterate through the list to find the largest and smallest values
        for (T num : numbers) {
            if (num.doubleValue() > largest.doubleValue()) {
                largest = num;
            }
            if (num.doubleValue() < smallest.doubleValue()) {
                smallest = num;
            }
        }

        System.out.println("Largest number: " + largest);
        System.out.println("Smallest number: " + smallest);
    }

    // Method to simulate the timing functionality to calculate execution time
    public static void measureTime(Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Execution time: " + duration + " nanoseconds");
    }

    public static void main(String[] args) {
        // Create a random list of numbers for the example (with 1000 random integers)
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            numbers.add(random.nextInt(1000)); // Adding random integers between 0 and 999
        }

        // Measure execution time for finding largest and smallest
        measureTime(() -> findLargestAndSmallest(numbers));
    }
}
