class Node {
    public Node next;
    public Object data; // Dữ liệu lưu trữ trong Node
}

class MyStack {
    private Node top;

    // Kiểm tra ngăn xếp rỗng
    public boolean isEmpty() {
        return top == null;
    }

    // Thêm phần tử vào ngăn xếp
    public void push(Object ele) {
        Node n = new Node();
        n.data = ele;
        n.next = top;
        top = n;
    }

    // Lấy phần tử khỏi ngăn xếp
    public Node pop() {
        if (isEmpty()) {
            return null; // Trả về null nếu ngăn xếp rỗng
        }
        Node d = top;
        top = top.next;
        return d;
    }
}
