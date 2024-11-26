import java.util.ArrayList;
import java.util.List;

public class b7 {
    public static class Node {
        Node leftNode, rightNode;
        int data;

        public Node(int data) {
            this.data = data;
            this.leftNode = null;
            this.rightNode = null;
        }
    }

    public static class BinarySearchTree {
        Node root;

        public boolean insert(int value) {
            Node before = null, after = root;

            while (after != null) {
                before = after;
                if (value < after.data) {
                    after = after.leftNode;
                } else if (value > after.data) {
                    after = after.rightNode;
                } else {
                    return false; // Giá trị đã tồn tại
                }
            }

            Node newNode = new Node(value);
            if (root == null) {
                root = newNode;
            } else {
                if (value < before.data) {
                    before.leftNode = newNode;
                } else {
                    before.rightNode = newNode;
                }
            }
            return true;
        }

        public void traverseInOrder(Node parent) {
            if (parent != null) {
                traverseInOrder(parent.leftNode);
                System.out.print(parent.data + " ");
                traverseInOrder(parent.rightNode);
            }
        }

        public void traversePreOrder(Node parent) {
            if (parent != null) {
                System.out.print(parent.data + " ");
                traversePreOrder(parent.leftNode);
                traversePreOrder(parent.rightNode);
            }
        }

        public void traversePostOrder(Node parent) {
            if (parent != null) {
                traversePostOrder(parent.leftNode);
                traversePostOrder(parent.rightNode);
                System.out.print(parent.data + " ");
            }
        }

        private int minValueOfNode(Node node) {
            int minv = node.data;
            while (node.leftNode != null) {
                minv = node.leftNode.data;
                node = node.leftNode;
            }
            return minv;
        }

        public int findMin() {
            return minValueOfNode(root);
        }

        private int maxValueOfNode(Node node) {
            int maxv = node.data;
            while (node.rightNode != null) {
                maxv = node.rightNode.data;
                node = node.rightNode;
            }
            return maxv;
        }

        public int findMax() {
            return maxValueOfNode(root);
        }

        public int getTreeDepth() {
            return getTreeDepth(root);
        }

        private int getTreeDepth(Node parent) {
            if (parent == null) {
                return 0;
            } else {
                return Math.max(getTreeDepth(parent.leftNode), getTreeDepth(parent.rightNode)) + 1;
            }
        }

        public Node find(int value) {
            return find(value, root);
        }

        private Node find(int value, Node parent) {
            if (parent != null) {
                if (value == parent.data) return parent;
                if (value < parent.data) return find(value, parent.leftNode);
                else return find(value, parent.rightNode);
            }
            return null;
        }

        public void remove(int value) {
            root = remove(root, value);
        }

        private Node remove(Node parent, int key) {
            if (parent == null) return null;

            if (key < parent.data) {
                parent.leftNode = remove(parent.leftNode, key);
            } else if (key > parent.data) {
                parent.rightNode = remove(parent.rightNode, key);
            } else {
                if (parent.leftNode == null) return parent.rightNode;
                else if (parent.rightNode == null) return parent.leftNode;

                parent.data = minValueOfNode(parent.rightNode);
                parent.rightNode = remove(parent.rightNode, parent.data);
            }
            return parent;
        }

        public void convertBST2List(Node parent, List<Integer> list) {
            if (parent != null) {
                convertBST2List(parent.leftNode, list);
                list.add(parent.data);
                convertBST2List(parent.rightNode, list);
            }
        }

        public void printPythagoreanTriplets() {
            List<Integer> list = new ArrayList<>();
            convertBST2List(root, list);

            for (int i = 0; i < list.size() - 2; i++) {
                for (int j = i + 1; j < list.size() - 1; j++) {
                    for (int k = j + 1; k < list.size(); k++) {
                        if (checkPythagoreanTriplet(list.get(i), list.get(j), list.get(k))) {
                            System.out.println("(" + list.get(i) + ", " + list.get(j) + ", " + list.get(k) + ")");
                        }
                    }
                }
            }
        }

        private boolean checkPythagoreanTriplet(int a, int b, int c) {
            int max = Math.max(a, Math.max(b, c));
            int min = Math.min(a, Math.min(b, c));
            int cl = a + b + c - max - min;
            return Math.pow(max, 2) == Math.pow(min, 2) + Math.pow(cl, 2);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree binaryTree = new BinarySearchTree();
        binaryTree.insert(23);
        binaryTree.insert(16);
        binaryTree.insert(45);
        binaryTree.insert(3);
        binaryTree.insert(22);
        binaryTree.insert(37);
        binaryTree.insert(99);
        binaryTree.insert(39);
        binaryTree.insert(38);
        binaryTree.insert(40);
        binaryTree.insert(4);
        binaryTree.insert(5);

        binaryTree.printPythagoreanTriplets();
    }
}
