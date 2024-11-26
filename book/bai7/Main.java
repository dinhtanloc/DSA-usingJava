package book.bai7;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // Bài tập 1: Hash class với các chiến lược giải quyết va chạm và hàm băm tùy chỉnh
        System.out.println("Exercise 1: Computer Terms Glossary using Custom Hash Class");
        Glossary glossary = new Glossary();
        glossary.addTerm("Algorithm", "A step-by-step procedure for solving a problem.");
        glossary.addTerm("Data Structure", "A way of organizing and storing data.");
        glossary.addTerm("Hashing", "The process of mapping data to fixed-size values.");
        glossary.addTerm("Queue", "A linear data structure where elements are added to the rear and removed from the front.");
        glossary.addTerm("Stack", "A linear data structure where elements are added and removed from the same end.");
        
        glossary.displayTerms();

        // Bài tập 2: Spelling Checker sử dụng Hashtable
        System.out.println("\nExercise 2: Spelling Checker using Hashtable");
        SpellingChecker checker = new SpellingChecker();
        checker.checkSpelling("Hello, this is a simple spelling checker. Ths is a test.");
        
        // Bài tập 3: Hash class sử dụng ArrayList thay vì mảng
        System.out.println("\nExercise 3: New Hash Class using ArrayList");
        GlossaryWithArrayList glossaryWithList = new GlossaryWithArrayList();
        glossaryWithList.addTerm("Computer", "A device for processing and storing data.");
        glossaryWithList.addTerm("Network", "A group of computers that are connected.");
        glossaryWithList.addTerm("Database", "An organized collection of data.");
        glossaryWithList.displayTerms();
    }

    // Bài tập 1 - Hash Class với chiến lược giải quyết va chạm và hàm băm tùy chỉnh
    static class Glossary {
        private final Hash customHashTable;

        public Glossary() {
            customHashTable = new Hash(10); // Kích thước bảng băm 10
        }

        public void addTerm(String term, String definition) {
            customHashTable.put(term, definition);
        }

        public void displayTerms() {
            customHashTable.display();
        }
    }

    // Hàm băm tùy chỉnh và giải quyết va chạm (sử dụng phương thức chaining)
    static class Hash {
        private final LinkedList<Entry>[] table;

        public Hash(int size) {
            table = new LinkedList[size];
            for (int i = 0; i < size; i++) {
                table[i] = new LinkedList<>();
            }
        }

        // Hàm băm đơn giản: lấy mã băm của từ và chia cho kích thước bảng
        private int hash(String key) {
            return key.hashCode() % table.length;
        }

        public void put(String key, String value) {
            int index = hash(key);
            table[index].add(new Entry(key, value)); // Thêm phần tử vào danh sách liên kết
        }

        public void display() {
            for (int i = 0; i < table.length; i++) {
                if (!table[i].isEmpty()) {
                    for (Entry entry : table[i]) {
                        System.out.println(entry.key + ": " + entry.value);
                    }
                }
            }
        }

        private static class Entry {
            String key;
            String value;

            public Entry(String key, String value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    // Bài tập 2 - Spelling Checker sử dụng Hashtable
    static class SpellingChecker {
        private final Hashtable<String, String> dictionary;

        public SpellingChecker() {
            dictionary = new Hashtable<>();
            loadDictionary();
        }

        // Tạo một từ điển với các từ thông dụng
        private void loadDictionary() {
            dictionary.put("hello", "A greeting");
            dictionary.put("this", "A demonstrative pronoun");
            dictionary.put("is", "A verb");
            dictionary.put("a", "An indefinite article");
            dictionary.put("simple", "Easily understood");
            dictionary.put("spelling", "The action of writing or naming words");
            dictionary.put("checker", "A tool for checking");
            dictionary.put("test", "A procedure for assessing something");
        }

        // Kiểm tra chính tả các từ trong một câu
        public void checkSpelling(String text) {
            String[] words = text.toLowerCase().split("\\s+");
            for (String word : words) {
                word = word.replaceAll("[^a-zA-Z]", ""); // Loại bỏ dấu câu
                if (!dictionary.containsKey(word)) {
                    System.out.println("Spelling error: " + word);
                }
            }
        }
    }

    // Bài tập 3 - Hash Class sử dụng ArrayList thay vì mảng
    static class GlossaryWithArrayList {
        private final HashWithArrayList customHashTable;

        public GlossaryWithArrayList() {
            customHashTable = new HashWithArrayList(10); // Kích thước bảng băm 10
        }

        public void addTerm(String term, String definition) {
            customHashTable.put(term, definition);
        }

        public void displayTerms() {
            customHashTable.display();
        }
    }

    // Hàm băm tùy chỉnh và giải quyết va chạm (sử dụng ArrayList thay vì mảng)
    static class HashWithArrayList {
        private final ArrayList<Entry> table[];

        public HashWithArrayList(int size) {
            table = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                table[i] = new ArrayList<>();
            }
        }

        private int hash(String key) {
            return key.hashCode() % table.length;
        }

        public void put(String key, String value) {
            int index = hash(key);
            table[index].add(new Entry(key, value));
        }

        public void display() {
            for (int i = 0; i < table.length; i++) {
                if (!table[i].isEmpty()) {
                    for (Entry entry : table[i]) {
                        System.out.println(entry.key + ": " + entry.value);
                    }
                }
            }
        }

        private static class Entry {
            String key;
            String value;

            public Entry(String key, String value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
