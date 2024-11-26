package book.bai6;

import java.io.*;
import java.util.*;

public class Main {

    

    // Bài tập 2 - Phương thức reverse lookup: tìm tên từ số điện thoại
    public static String reverseLookup(Map<String, String> phoneBook, String phoneNumber) {
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            if (entry.getValue().equals(phoneNumber)) {
                return entry.getKey();
            }
        }
        return "Not Found";
    }

    // Bài tập 3 - Đếm số lần xuất hiện của từ trong câu
    public static Map<String, Integer> wordCount(String sentence) {
        Map<String, Integer> countMap = new HashMap<>();
        String[] words = sentence.split(" ");
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }
        return countMap;
    }

    // Bài tập 4 - Đếm số lần xuất hiện của các chữ cái trong câu
    public static Map<Character, Integer> letterCount(String sentence) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char letter : sentence.replaceAll(" ", "").toCharArray()) {
            countMap.put(letter, countMap.getOrDefault(letter, 0) + 1);
        }
        return countMap;
    }

    // Lớp SortedList sử dụng 2 mảng để lưu trữ keys và values
    public static class SortedList<K extends Comparable<K>, V> {
        private List<K> keys = new ArrayList<>();
        private List<V> values = new ArrayList<>();

        // Thêm phần tử vào SortedList
        public void add(K key, V value) {
            int index = Collections.binarySearch(keys, key);
            if (index < 0) {
                index = -index - 1;
            }
            keys.add(index, key);
            values.add(index, value);
        }

        // Phương thức để lấy giá trị bằng key
        public V get(K key) {
            int index = Collections.binarySearch(keys, key);
            if (index >= 0) {
                return values.get(index);
            }
            return null;
        }
    
    }

    public static void main(String[] args) {
        // Bài tập 1: Sắp xếp các địa chỉ IP trong lớp IPAddresses
        System.out.println("Exercise 1: Displaying IP Addresses in Ascending Order");
        List<String> ipAddresses = Arrays.asList("192.168.1.1", "10.0.0.1", "172.16.0.1", "192.168.0.1");
        Collections.sort(ipAddresses);
        System.out.println(ipAddresses);

        // Bài tập 2: Tìm kiếm tên theo số điện thoại
        System.out.println("\nExercise 2: Reverse Lookup (Name from Phone Number)");
        Map<String, String> phoneBook = new HashMap<>();
        phoneBook.put("John", "123456789");
        phoneBook.put("Alice", "987654321");
        phoneBook.put("Bob", "456789123");
        String phoneNumber = "987654321";
        String name = reverseLookup(phoneBook, phoneNumber);
        System.out.println("Phone number " + phoneNumber + " belongs to: " + name);

        // Bài tập 3: Tính số lần xuất hiện của từ trong câu
        System.out.println("\nExercise 3: Word Occurrence Count");
        String sentence = "apple banana apple orange banana apple";
        Map<String, Integer> wordCount = wordCount(sentence);
        System.out.println("Word occurrences: " + wordCount);

        // Bài tập 4: Đếm số lần xuất hiện của các chữ cái
        System.out.println("\nExercise 4: Letter Occurrence Count");
        String sentenceLetters = "apple banana apple orange banana apple";
        Map<Character, Integer> letterCount = letterCount(sentenceLetters);
        System.out.println("Letter occurrences: " + letterCount);

        // Bài tập 5: Reverse Lookup với SortedList
        System.out.println("\nExercise 5: Reverse Lookup using SortedList");
        SortedList<String, String> sortedPhoneBook = new SortedList<>();
        sortedPhoneBook.add("John", "123456789");
        sortedPhoneBook.add("Alice", "987654321");
        sortedPhoneBook.add("Bob", "456789123");
        String sortedPhoneNumber = "987654321";
        String sortedName = reverseLookup(sortedPhoneBook, sortedPhoneNumber);
        System.out.println("Phone number " + sortedPhoneNumber + " belongs to: " + sortedName);

        // Bài tập 6: Sử dụng SortedList để giải quyết bài tập 2
        System.out.println("\nExercise 6: Using SortedList for PhoneBook");
        SortedList<String, String> sortedPhoneBook2 = new SortedList<>();
        sortedPhoneBook2.add("John", "123456789");
        sortedPhoneBook2.add("Alice", "987654321");
        sortedPhoneBook2.add("Bob", "456789123");
        String reverseSortedPhoneNumber = "987654321";
        String reverseSortedName = reverseLookup(sortedPhoneBook2, reverseSortedPhoneNumber);
        System.out.println("Phone number " + reverseSortedPhoneNumber + " belongs to: " + reverseSortedName);
    }
}
