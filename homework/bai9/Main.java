package homework.bai9;

import java.util.*;
import java.util.LinkedList;

class Vertex {
    public boolean wasVisited;
    public String label;

    public Vertex(String label) {
        this.label = label;
        this.wasVisited = false;
    }
}

class Graph {
    private int NUM_VERTICES;
    private Vertex[] vertices;
    private int[][] adjMatrix;
    private int numVerts;

    public Graph(int number_of_vertex) {
        NUM_VERTICES = number_of_vertex;
        vertices = new Vertex[NUM_VERTICES];
        adjMatrix = new int[NUM_VERTICES][NUM_VERTICES];
        numVerts = 0;
        for (int j = 0; j < NUM_VERTICES; j++) {
            for (int k = 0; k < NUM_VERTICES; k++) {
                adjMatrix[j][k] = 0;
            }
        }
    }

    public void addVertex(String label) {
        vertices[numVerts] = new Vertex(label);
        numVerts++;
    }

    public void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    public void addEdge(String start, String end) {
        int x = findVertexLabel(start);
        int y = findVertexLabel(end);
        addEdge(x, y);
    }

    public int findVertexLabel(String label) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].label.equals(label)) {
                return i;
            }
        }
        return -1;
    }

    public void showVertex(int v) {
        System.out.print(vertices[v].label + " ");
    }

    private int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < NUM_VERTICES; j++) {
            if (adjMatrix[v][j] == 1 && !vertices[j].wasVisited) {
                return j;
            }
        }
        return -1;
    }

    public void depthFirstSearch() {
        vertices[0].wasVisited = true;
        showVertex(0);
        Stack<Integer> gStack = new Stack<>();
        gStack.push(0);
        int v;

        while (!gStack.isEmpty()) {
            v = getAdjUnvisitedVertex(gStack.peek());
            if (v == -1) {
                gStack.pop();
            } else {
                vertices[v].wasVisited = true;
                showVertex(v);
                gStack.push(v);
            }
        }

        for (int j = 0; j < NUM_VERTICES; j++) {
            vertices[j].wasVisited = false;
        }
    }

    public void breadthFirstSearch() {
        Queue<Integer> gQueue = new LinkedList<>();
        vertices[0].wasVisited = true;
        showVertex(0);
        gQueue.offer(0);
        int vert1, vert2;

        while (!gQueue.isEmpty()) {
            vert1 = gQueue.poll();
            vert2 = getAdjUnvisitedVertex(vert1);

            while (vert2 != -1) {
                vertices[vert2].wasVisited = true;
                showVertex(vert2);
                gQueue.offer(vert2);
                vert2 = getAdjUnvisitedVertex(vert1);
            }
        }

        for (int i = 0; i < NUM_VERTICES; i++) {
            vertices[i].wasVisited = false;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println();
        Graph graph = new Graph(13);
        graph.addVertex("A");
        graph.addVertex("B"); // 0 1
        graph.addVertex("C");
        graph.addVertex("D"); // 2 3
        graph.addVertex("E");
        graph.addVertex("F"); // 4 5
        graph.addVertex("G");
        graph.addVertex("H"); // 6 7
        graph.addVertex("I");
        graph.addVertex("J"); // 8 9
        graph.addVertex("K");
        graph.addVertex("L"); // 10 11
        graph.addVertex("M"); // 12

        graph.addEdge("A", "B");
        graph.addEdge(0, 4);
        graph.addEdge(0, 7);
        graph.addEdge(0, 10);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);
        graph.addEdge(10, 11);
        graph.addEdge(11, 12);

        System.out.print("DFS: ");
        graph.depthFirstSearch();
        System.out.println();
        System.out.print("BFS: ");
        graph.breadthFirstSearch();
    }
}
