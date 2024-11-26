package book.bai5;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame balancedParenthesesApp = new BalancedParenthesesApp();
            balancedParenthesesApp.setVisible(true);

            JFrame calculatorApp = new CalculatorApp();
            calculatorApp.setVisible(true);

            JFrame helpDeskApp = new HelpDeskApp();
            helpDeskApp.setVisible(true);
        });
    }
}

class BalancedParenthesesApp extends JFrame {
    public BalancedParenthesesApp() {
        // Set up the frame
        setTitle("Balanced Parentheses Checker");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // UI components
        JTextField inputField = new JTextField(20);
        JButton checkButton = new JButton("Check Parens");
        JLabel resultLabel = new JLabel("");

        add(inputField);
        add(checkButton);
        add(resultLabel);

        // Button click event
        checkButton.addActionListener(e -> {
            String expression = inputField.getText();
            int errorIndex = checkBalancedParentheses(expression);
            if (errorIndex == -1) {
                resultLabel.setText("The parentheses are balanced.");
                resultLabel.setForeground(Color.GREEN);
            } else {
                resultLabel.setText("Unbalanced parenthesis at index: " + errorIndex);
                resultLabel.setForeground(Color.RED);
            }
        });
    }

    private int checkBalancedParentheses(String expression) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                stack.push(i);
            } else if (expression.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    return i; // Extra closing parenthesis
                }
                stack.pop();
            }
        }
        return stack.isEmpty() ? -1 : stack.peek(); // Unbalanced opening parenthesis
    }
}

class CalculatorApp extends JFrame {
    public CalculatorApp() {
        // Set up the frame
        setTitle("Calculator");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // UI components
        JTextField inputField = new JTextField(20);
        JButton convertButton = new JButton("Convert & Evaluate");
        JLabel resultLabel = new JLabel("");

        add(inputField);
        add(convertButton);
        add(resultLabel);

        // Button click event
        convertButton.addActionListener(e -> {
            String infixExpression = inputField.getText();
            String postfixExpression = infixToPostfix(infixExpression);
            int result = evaluatePostfix(postfixExpression);
            resultLabel.setText("Evaluation Result: " + result);
        });
    }

    private String infixToPostfix(String expression) {
        Stack<Character> operators = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch)) {
                postfix.append(ch);
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    postfix.append(operators.pop());
                }
                operators.pop(); // Remove '('
            } else { // Operators: +, -, *, /
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    postfix.append(operators.pop());
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            postfix.append(operators.pop());
        }

        return postfix.toString();
    }

    private int evaluatePostfix(String postfix) {
        Stack<Integer> operands = new Stack<>();

        for (char ch : postfix.toCharArray()) {
            if (Character.isDigit(ch)) {
                operands.push(ch - '0');
            } else {
                int op2 = operands.pop();
                int op1 = operands.pop();
                switch (ch) {
                    case '+': operands.push(op1 + op2); break;
                    case '-': operands.push(op1 - op2); break;
                    case '*': operands.push(op1 * op2); break;
                    case '/': operands.push(op1 / op2); break;
                }
            }
        }

        return operands.pop();
    }

    private int precedence(char op) {
        return (op == '+' || op == '-') ? 1 : 2;
    }
}

class HelpDeskApp extends JFrame {
    private PriorityQueue<HelpRequest> queue = new PriorityQueue<>();

    public HelpDeskApp() {
        // Set up the frame
        setTitle("Help Desk Manager");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // UI components
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> jobList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(jobList);
        add(scrollPane, BorderLayout.CENTER);

        JButton completeButton = new JButton("Complete Job");
        add(completeButton, BorderLayout.SOUTH);

        // Load initial requests
        loadRequests();
        updateJobList(listModel);

        completeButton.addActionListener(e -> {
            if (!queue.isEmpty()) {
                queue.poll(); // Remove highest priority job
                updateJobList(listModel);
                if (queue.isEmpty()) {
                    loadRequests(); // Load next batch
                    updateJobList(listModel);
                }
            }
        });
    }

    private void loadRequests() {
        try (Scanner scanner = new Scanner(new File("help_requests.txt"))) {
            int count = 0;
            while (scanner.hasNextLine() && count < 5) {
                String[] parts = scanner.nextLine().split(",");
                int priority = Integer.parseInt(parts[0]);
                String id = parts[1];
                String time = parts[2];
                queue.add(new HelpRequest(priority, id, time));
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateJobList(DefaultListModel<String> listModel) {
        listModel.clear();
        for (HelpRequest request : queue) {
            listModel.addElement(request.toString());
        }
    }
}

class HelpRequest implements Comparable<HelpRequest> {
    private int priority;
    private String id;
    private String time;

    public HelpRequest(int priority, String id, String time) {
        this.priority = priority;
        this.id = id;
        this.time = time;
    }

    @Override
    public int compareTo(HelpRequest other) {
        return Integer.compare(other.priority, this.priority); // Higher priority first
    }

    @Override
    public String toString() {
        return "Priority: " + priority + ", ID: " + id + ", Time: " + time;
    }
}
