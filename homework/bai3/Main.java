package homework.bai3;
import java.io.*;
import java.util.*;

public class Main {

    // ===================== Chuyển đổi từ Infix sang Postfix =====================
    private static final Map<Character, Integer> precedence = new HashMap<>();
    static {
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);
        precedence.put('^', 3);
    }

    public static String infixToPostfix(String expression) {
        Stack<Character> stack = new Stack<>();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch)) {
                output.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop());
                }
                stack.pop();
            } else if (isOperator(ch)) {
                while (!stack.isEmpty() && precedence.get(ch) <= precedence.get(stack.peek())) {
                    output.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }

        return output.toString();
    }

    private static boolean isOperator(char c) {
        return precedence.containsKey(c);
    }

    // ===================== Tính giá trị biểu thức hậu tố =====================
    public static int evaluatePostfix(String expression) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else {
                int b = stack.pop();
                int a = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(a / b);
                        break;
                }
            }
        }

        return stack.pop();
    }

    // ===================== Kiểm tra tính hợp lệ của file HTML =====================
    public static boolean validateHTML(String filePath) {
        Stack<String> stack = new Stack<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                int i = 0;
                while (i < line.length()) {
                    if (line.charAt(i) == '<') {
                        int j = line.indexOf('>', i);
                        if (j == -1) {
                            return false;
                        }

                        String tag = line.substring(i + 1, j);
                        if (!tag.startsWith("/")) {
                            stack.push(tag);
                        } else {
                            if (stack.isEmpty() || !stack.peek().equals(tag.substring(1))) {
                                return false;
                            }
                            stack.pop();
                        }
                        i = j;
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
            return false;
        }

        return stack.isEmpty();
    }

    // ===================== Phương thức main =====================
    public static void main(String[] args) {
        // 1. Chuyển đổi từ Infix sang Postfix
        String infixExpression = "3+(2*5)-8";
        String postfixExpression = infixToPostfix(infixExpression);
        System.out.println("Infix: " + infixExpression);
        System.out.println("Postfix: " + postfixExpression);

        // 2. Tính giá trị biểu thức Postfix
        String postfix = "235*+8-"; // Kết quả từ biểu thức trên
        int postfixResult = evaluatePostfix(postfix);
        System.out.println("Postfix Evaluation Result: " + postfixResult);

        // 3. Kiểm tra tính hợp lệ file HTML
        String filePath = "test.html"; // Đảm bảo file tồn tại
        boolean isValid = validateHTML(filePath);
        if (isValid) {
            System.out.println("File HTML hợp lệ.");
        } else {
            System.out.println("File HTML không hợp lệ.");
        }
    }
}
