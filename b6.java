class Node {
    public Object element; // data
    public Node link; // next node link

    public Node() {
        this.element = null;
        this.link = null;
    }

    public Node(Object element) {
        this.element = element;
        this.link = null;
    }
}

// Singly linked list class
class LinkedList {
    public Node header; // head node

    public LinkedList() {
        header = new Node("header");
    }

    public Node find(Object ele) {
        Node current = header;
        while (!current.element.equals(ele) && current.link != null) {
            current = current.link;
        }
        return current;
    }

    public void insert(Object newEle, Object after) {
        Node current = find(after);
        Node newNode = new Node(newEle);
        newNode.link = current.link;
        current.link = newNode;
    }

    public Node findPrev(Object ele) {
        Node current = header;
        while (current.link != null && !current.link.element.equals(ele)) {
            current = current.link;
        }
        return current;
    }

    public void remove(Object ele) {
        Node current = findPrev(ele);
        if (current.link != null) {
            current.link = current.link.link;
        }
    }

    public void swap(Object o1, Object o2) {
        Object n1 = findPrev(o1).element;
        Object n2 = findPrev(o2).element;
        remove(o1);
        remove(o2);
        insert(o1, n2);
        insert(o2, n1);
    }

    public void sort() {
        Begin: 
        while (true) {
            Node current = header.link;
            while (current.link != null) {
                Node current2 = current.link;
                while (current2 != null) {
                    if ((int) current.element > (int) current2.element) {
                        swap(current2.element, current.element);
                        continue Begin;
                    }
                    current2 = current2.link;
                }
                current = current.link;
            }
            break;
        }
    }
}

// Node2 class for doubly linked list
class Node2 {
    public Object element;
    public Node2 flink, blink; // forward and backward links

    public Node2() {
        this.element = null;
        this.flink = null;
        this.blink = null;
    }

    public Node2(Object element) {
        this.element = element;
        this.flink = null;
        this.blink = null;
    }
}

// Doubly linked list class
class DoubleLinkedList {
    public Node2 header;

    public DoubleLinkedList() {
        header = new Node2("Header");
    }

    private Node2 find(Object element) {
        Node2 current = header;
        while (current != null && !current.element.equals(element)) {
            current = current.flink;
        }
        return current;
    }

    public void insert(Object newElement, Object afterElement) {
        Node2 current = find(afterElement);
        Node2 newNode = new Node2(newElement);
        if (current != null) {
            newNode.flink = current.flink;
            newNode.blink = current;
            if (current.flink != null) {
                current.flink.blink = newNode;
            }
            current.flink = newNode;
        }
    }

    public void remove(Object element) {
        Node2 current = find(element);
        if (current != null && current.flink != null) {
            current.blink.flink = current.flink;
            current.flink.blink = current.blink;
            current.flink = null;
            current.blink = null;
        }
    }

    private Node2 findLast() {
        Node2 current = header;
        while (current.flink != null) {
            current = current.flink;
        }
        return current;
    }

    public void print() {
        Node2 current = findLast();
        while (current.blink != null) {
            System.out.println(current.element);
            current = current.blink;
        }
    }
}

// Book class
class Book {
    public String title, author;
    public long price;

    public Book(String title, String author, long price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return "[Book: " + title + ", " + author + ", " + price + "]";
    }
}

// Main class

public class b6 {
    public static void main(String[] args) {
        DoubleLinkedList list3 = new DoubleLinkedList();
        Book b1 = new Book("Gone with the Wind", "Margaret Mitchell", 100000);
        list3.insert(b1, "Header");
        Book b2 = new Book("Kieu's Story", "Nguyen Du", 120000);
        list3.insert(b2, b1);
        list3.print();
    }
}
