package homework.bai4;

import java.util.ArrayList;
import java.util.List;

class SinhVien {
    int id;
    String hoten;
    double diemtb;

    public SinhVien(int id, String hoten, double diemtb) {
        this.id = id;
        this.hoten = hoten;
        this.diemtb = diemtb;
    }

    @Override
    public String toString() {
        return "SinhVien{id=" + id + ", hoten='" + hoten + "', diemtb=" + diemtb + '}';
    }
}

public class Main {

    // Linear Search
    public static SinhVien linearSearch(List<SinhVien> list, int id) {
        for (SinhVien sv : list) {
            if (sv.id == id) {
                return sv;
            }
        }
        return null; // Không tìm thấy
    }

    // Sentinel Search
    public static SinhVien sentinelSearch(List<SinhVien> list, int id) {
        int n = list.size();
        SinhVien last = list.get(n - 1); // Lưu phần tử cuối
        if (last.id == id) return last; // Nếu phần tử cuối là phần tử cần tìm

        list.set(n - 1, new SinhVien(id, "", 0)); // Đặt Sentinel
        int i = 0;
        while (list.get(i).id != id) {
            i++;
        }

        list.set(n - 1, last); // Khôi phục phần tử cuối
        if (i < n - 1 || last.id == id) {
            return list.get(i);
        }
        return null;
    }

    // Binary Search (Yêu cầu danh sách được sắp xếp)
    public static SinhVien binarySearch(List<SinhVien> list, int id) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).id == id) {
                return list.get(mid);
            } else if (list.get(mid).id < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Danh sách sinh viên
        List<SinhVien> list = new ArrayList<>();
        list.add(new SinhVien(1, "Nguyen Van A", 8.5));
        list.add(new SinhVien(2, "Le Thi B", 7.0));
        list.add(new SinhVien(3, "Tran Van C", 9.0));
        list.add(new SinhVien(4, "Hoang Van D", 6.5));
        list.add(new SinhVien(5, "Pham Thi E", 8.0));

        // Sắp xếp danh sách theo id để phục vụ Binary Search
        list.sort((sv1, sv2) -> Integer.compare(sv1.id, sv2.id));

        // Tìm kiếm ID cần tìm
        int idToFind = 3;

        // Đo thời gian Linear Search
        long start = System.nanoTime();
        SinhVien resultLinear = linearSearch(list, idToFind);
        long end = System.nanoTime();
        System.out.println("Linear Search result: " + resultLinear + " (Time: " + (end - start) + " ns)");

        // Đo thời gian Sentinel Search
        start = System.nanoTime();
        SinhVien resultSentinel = sentinelSearch(list, idToFind);
        end = System.nanoTime();
        System.out.println("Sentinel Search result: " + resultSentinel + " (Time: " + (end - start) + " ns)");

        // Đo thời gian Binary Search
        start = System.nanoTime();
        SinhVien resultBinary = binarySearch(list, idToFind);
        end = System.nanoTime();
        System.out.println("Binary Search result: " + resultBinary + " (Time: " + (end - start) + " ns)");
    }
}
