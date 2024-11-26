package homework.bai7;
import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

class PhoneBook {
    private Map<String, String> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    // Đọc file và lưu dữ liệu vào HashMap
    public void loadFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                phoneBook.put(parts[0].trim(), parts[1].trim());
            }
        }
        br.close();
    }

    // Tìm kiếm theo tên
    public String searchByName(String name) {
        return phoneBook.getOrDefault(name, "Không tìm thấy!");
    }

    // Tìm kiếm theo số điện thoại
    public String searchByPhone(String phone) {
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            if (entry.getValue().equals(phone)) {
                return entry.getKey();
            }
        }
        return "Không tìm thấy!";
    }
}

class SpellChecker {
    private Hashtable<String, Boolean> dictionary;

    public SpellChecker() {
        dictionary = new Hashtable<>();
    }

    // Tải từ điển vào Hashtable
    public void loadDictionary(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String word;
        while ((word = br.readLine()) != null) {
            dictionary.put(word.trim().toLowerCase(), true);
        }
        br.close();
    }

    // Kiểm tra lỗi chính tả
    public void spellCheck(String text) {
        String[] words = text.split("\\W+"); // Tách các từ (bỏ qua ký tự đặc biệt)
        System.out.println("Từ sai chính tả:");
        boolean hasError = false;
        for (String word : words) {
            if (!dictionary.containsKey(word.toLowerCase())) {
                System.out.println(word);
                hasError = true;
            }
        }
        if (!hasError) {
            System.out.println("Không có lỗi chính tả!");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // ---------------- Bài 1 ----------------
        System.out.println("Bài 1: PhoneBook");
        PhoneBook phoneBook = new PhoneBook();
        try {
            String phoneBookFile = "phonebook.txt"; // File chứa tên và số điện thoại (mỗi dòng: Tên,Số_Điện_Thoại)
            phoneBook.loadFromFile(phoneBookFile);

            // Tìm kiếm theo tên
            System.out.println("Tìm số điện thoại của 'Nguyen Van A': " + phoneBook.searchByName("Nguyen Van A"));

            // Tìm kiếm theo số điện thoại
            System.out.println("Tìm tên với số điện thoại '0123456789': " + phoneBook.searchByPhone("0123456789"));
        } catch (IOException e) {
            System.err.println("Lỗi đọc file phonebook: " + e.getMessage());
        }

        // ---------------- Bài 2 ----------------
        System.out.println("\nBài 2: SpellChecker");
        SpellChecker spellChecker = new SpellChecker();
        try {
            String dictionaryFile = "dictionary.txt"; // File từ điển (mỗi dòng một từ)
            spellChecker.loadDictionary(dictionaryFile);

            // Văn bản để kiểm tra lỗi chính tả
            String text = "This is a smple text with erors.";
            spellChecker.spellCheck(text);
        } catch (IOException e) {
            System.err.println("Lỗi đọc file từ điển: " + e.getMessage());
        }
    }
}
