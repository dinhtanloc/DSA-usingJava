

import java.util.*;
import java.util.LinkedList;

public class b3 {
    public static void main(String[] args) {
        // Tạo một Stack và thêm phần tử
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);

        int x = 4; // Giá trị cần tìm và xóa
        int c;
        Queue<Integer> queue = new LinkedList<>();

        // Chuyển các phần tử không phải x từ stack sang queue
        while (!stack.isEmpty() && (c = stack.pop()) != x) {
            queue.add(c);
        }

        // Đưa các phần tử từ queue trở lại stack
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }

        // In các phần tử trong stack
        for (int v : stack) {
            System.out.print(v + " ");
        }

        /* Thêm phần MyStack nếu cần:
        MyStack ms = new MyStack();
        ms.push(1);
        ms.push(2);
        ms.push(3); // xoá
        ms.push(4);
        ms.push(5);

        MyStack temp = new MyStack();
        int c;
        while ((c = (int) ms.pop().data) != 3) {
            if (c != 3)
                temp.push(c);
        }

        do {
            ms.push(temp.pop().data);
        } while (!temp.isEmpty());

        Stack<Integer> s1 = new Stack<>();
        s1.push(1);
        s1.push(2);
        boolean cs = s1.pop() == 1;

        Stack<Integer> s2 = new Stack<>();
        s1.push(1);
        s2.push(2);
        boolean css = s2.pop() == 1;

        Queue<Integer> q2 = new LinkedList<>();
        q2.add(1);
        q2.add(2);
        */
    }
}



