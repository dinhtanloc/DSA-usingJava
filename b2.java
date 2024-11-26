import java.util.ArrayList;
import java.util.List;

public class b2 {

    // Tạo mảng Fibonacci 1D
    public static int[] create1DFibArray(int n) {
        int[] ar = new int[n];
        if (n > 0) ar[0] = 0; 
        if (n > 1) ar[1] = 1; 
        for (int i = 2; i < n; i++) {
            ar[i] = ar[i - 1] + ar[i - 2];
        }
        return ar;
    }

    public static void print1DArray(int[] ar) {
        for (int v : ar) {
            System.out.print(v + "  ");
        }
        System.out.println();
    }

    public static void convert1DTo2D(int x, int m, int n, int[] result) {
        result[0] = (x + n - 1) / n; 
        result[1] = (x % n == 0) ? n : x % n; // beta
    }

    public static int[][] create2DFibArray(int m, int n) {
        int[][] ar = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = i * n + j + 1; // Chỉ số 1D
                if (x == 1) {
                    ar[i][j] = 0; // Giá trị đầu tiên
                } else if (x == 2) {
                    ar[i][j] = 1; // Giá trị thứ hai
                } else {
                    int[] prev1 = new int[2];
                    int[] prev2 = new int[2];
                    convert1DTo2D(x - 1, m, n, prev1);
                    convert1DTo2D(x - 2, m, n, prev2);
                    ar[i][j] = ar[prev1[0] - 1][prev1[1] - 1] + ar[prev2[0] - 1][prev2[1] - 1];
                }
            }
        }
        return ar;
    }

    // In mảng 2D
    public static void print2DArray(int[][] ar) {
        for (int[] row : ar) {
            for (int v : row) {
                System.out.print(v + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Mảng Fibonacci 1D
        int[] fib1D = create1DFibArray(10);
        print1DArray(fib1D);

        // Mảng Fibonacci 2D
        int[][] fib2D = create2DFibArray(5, 3);
        print2DArray(fib2D);

        // Danh sách Fibonacci bằng ArrayList
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        for (int i = 2; i <= 10; i++) {
            list.add(list.get(i - 1) + list.get(i - 2));
        }
        System.out.println(list);

        // Danh sách Fibonacci bằng ArrayList kiểu khác
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        for (int i = 2; i <= 10; i++) {
            arrayList.add(arrayList.get(i - 1) + arrayList.get(i - 2));
        }
        System.out.println(arrayList);
    }
}
