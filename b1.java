import java.lang.reflect.Array;

public class b1 {
    public static <T> void swap(T[] a, int indexA, T[] b, int indexB) {
        T temp = a[indexA];
        a[indexA] = b[indexB];
        b[indexB] = temp;
    }

    @SuppressWarnings("unchecked")
    public static <T> T add(T a, T b) {
        if (a instanceof Object[] && b instanceof Object[]) {
            Object[] arrayA = (Object[]) a;
            Object[] arrayB = (Object[]) b;
    
            // Tạo mảng có kiểu giống với arrayA
            T sum = (T) Array.newInstance(arrayA.getClass().getComponentType(), arrayA.length + arrayB.length);
            System.arraycopy(arrayA, 0, sum, 0, arrayA.length);
            System.arraycopy(arrayB, 0, sum, arrayA.length, arrayB.length);
    
            return sum; // Trả về mảng với kiểu đúng
        } else {
            throw new UnsupportedOperationException("Addition is not supported for non-array types");
        }
    }
    

    public static void main(String[] args) {
        Integer[] x = {2};
        Integer[] y = {3};
        String[] s1 = {"di"};
        String[] s2 = {"hoc"};
        String[] ar = {"1", "2"};
        String[] br = {"3", "4", "5"};
    
        System.out.println("Before Swap: x=" + x[0] + ", y=" + y[0]);
        swap(x, 0, y, 0);
        System.out.println("After Swap: x=" + x[0] + ", y=" + y[0]);
    
        System.out.println("Before Swap: s1=" + s1[0] + ", s2=" + s2[0]);
        swap(s1, 0, s2, 0);
        System.out.println("After Swap: s1=" + s1[0] + ", s2=" + s2[0]);
    
        // Array addition
        String[] cr = add(ar, br);
        System.out.print("Added Arrays: ");
        for (String v : cr) {
            System.out.print(v + " ");
        }
        System.out.println();
    
        // Measure time for array addition
        long startTime = System.nanoTime();
        String[] result = add(ar, br);
        System.out.print("Result: "+ result);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Time: " + (elapsedTime / 1_000_000) + " ms");
    }
}    