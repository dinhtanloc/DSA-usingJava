

public class b4 {
    public static int seqSearch(int[] arr, int value) {
        int i = 0;
        while (arr[i] != value)
            i++;
        return i;
    }

    public static int seqSearchLast(int[] arr, int value) {
        int i = arr.length - 1;
        while (arr[i] != value)
            i--;
        return i;
    }

    public static int recuSearch(int[] arr, int from, int value) {
        if (arr[from] == value)
            return from;
        else
            return recuSearch(arr, from + 1, value);
    }

    public static int recuSearch2(int[] arr, int from, int value) {
        if (arr[from] == value)
            return from;
        if (from + 1 >= arr.length)
            return -1;
        return recuSearch2(arr, from + 1, value);
    }

    public static int senSearch(int[] arr, int value) {
        int x = arr[arr.length - 1];
        arr[arr.length - 1] = value;
        int i = 0;
        while (arr[i] != value)
            i++;
        arr[arr.length - 1] = x;
        if (i < arr.length - 1 || arr[arr.length - 1] == value)
            return i;
        else
            return -1;
    }

    public static int senRecuSearch(int[] arr, int from, int value) {
        int x = arr[arr.length - 1];
        arr[arr.length - 1] = value;
        if (arr[from] == value) {
            if (from < arr.length - 1)
                return from;
            else {
                arr[arr.length - 1] = x;
                if (arr[arr.length - 1] == value)
                    return from;
                else
                    return -1;
            }
        } else {
            arr[arr.length - 1] = x;
            return senRecuSearch(arr, from + 1, value);
        }
    }

    public static int binSearch(int[] arr, int L, int R, int value) {
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] == value)
                return mid;
            else if (value > arr[mid]) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return -1; // Nếu không tìm thấy
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 6, 9, 12, 15};
        int value = 12;

        // int res = recuSearch(arr, 0, value);
        // int res = seqSearch(arr, value);
        // int res = recuSearch2(arr, 0, value);
        // int res = senSearch(arr, value);
        // int res = senRecuSearch(arr, 0, value);
        int res = binSearch(arr, 0, arr.length - 1, value);

        System.out.println("Vị trí cần tìm: " + res);
    }
}
