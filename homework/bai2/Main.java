package homework.bai2;

import java.util.*;

// Bai1 - Thiết kế và thực thi một lớp cho phép giáo viên theo dõi điểm của một môn học
class Bai1 {
    public static class GradeTracker {
        private double[] scores;

        // Constructor to initialize the scores array
        public GradeTracker(int numberOfStudents) {
            scores = new double[numberOfStudents];
        }

        // Method to add a score to the array
        public void addScore(int index, double score) {
            if (index >= 0 && index < scores.length) {
                scores[index] = score;
            }
        }

        // Method to calculate the average score
        public double calculateAverage() {
            double sum = 0;
            for (double score : scores) {
                sum += score;
            }
            return sum / scores.length;
        }

        // Method to find the highest score
        public double findHighest() {
            double max = scores[0];
            for (double score : scores) {
                if (score > max) {
                    max = score;
                }
            }
            return max;
        }

        // Method to find the lowest score
        public double findLowest() {
            double min = scores[0];
            for (double score : scores) {
                if (score < min) {
                    min = score;
                }
            }
            return min;
        }
    }
}

// Bai2 - Chỉnh sửa bài tập 1 để chương trình có thể theo dõi điểm của nhiều môn học
class Bai2 {
    public static class MultiSubjectGradeTracker {
        private List<Bai1.GradeTracker> subjectGrades;

        // Constructor to initialize the subject grades list
        public MultiSubjectGradeTracker(int numberOfSubjects) {
            subjectGrades = new ArrayList<>();
            for (int i = 0; i < numberOfSubjects; i++) {
                subjectGrades.add(new Bai1.GradeTracker(5)); // Giả sử mỗi môn học có 5 học sinh
            }
        }

        // Method to add a score for a specific subject and student
        public void addScore(int subjectIndex, int studentIndex, double score) {
            if (subjectIndex >= 0 && subjectIndex < subjectGrades.size()) {
                Bai1.GradeTracker tracker = subjectGrades.get(subjectIndex);
                tracker.addScore(studentIndex, score);
            }
        }

        // Method to calculate the average score for a specific subject
        public double calculateAverage(int subjectIndex) {
            if (subjectIndex >= 0 && subjectIndex < subjectGrades.size()) {
                Bai1.GradeTracker tracker = subjectGrades.get(subjectIndex);
                return tracker.calculateAverage();
            }
            return 0;
        }

        // Method to find the highest score for a specific subject
        public double findHighest(int subjectIndex) {
            if (subjectIndex >= 0 && subjectIndex < subjectGrades.size()) {
                Bai1.GradeTracker tracker = subjectGrades.get(subjectIndex);
                return tracker.findHighest();
            }
            return 0;
        }

        // Method to find the lowest score for a specific subject
        public double findLowest(int subjectIndex) {
            if (subjectIndex >= 0 && subjectIndex < subjectGrades.size()) {
                Bai1.GradeTracker tracker = subjectGrades.get(subjectIndex);
                return tracker.findLowest();
            }
            return 0;
        }
    }
}

// Bai3 - Viết lại bài tập 1 bằng cách sử dụng List
class Bai3 {
    public static class GradeTrackerList {
        private List<Double> scores;

        // Constructor to initialize the scores list
        public GradeTrackerList() {
            scores = new ArrayList<>();
        }

        // Method to add a score to the list
        public void addScore(double score) {
            scores.add(score);
        }

        // Method to calculate the average score
        public double calculateAverage() {
            double sum = 0;
            for (double score : scores) {
                sum += score;
            }
            return sum / scores.size();
        }

        // Method to find the highest score
        public double findHighest() {
            double max = scores.get(0);
            for (double score : scores) {
                if (score > max) {
                    max = score;
                }
            }
            return max;
        }

        // Method to find the lowest score
        public double findLowest() {
            double min = scores.get(0);
            for (double score : scores) {
                if (score < min) {
                    min = score;
                }
            }
            return min;
        }
    }
}

// Bai4 - Viết lại bài tập 1 bằng cách sử dụng ArrayList
class Bai4 {
    public static class GradeTrackerArrayList {
        private ArrayList<Double> scores;

        // Constructor to initialize the scores array list
        public GradeTrackerArrayList() {
            scores = new ArrayList<>();
        }

        // Method to add a score to the list
        public void addScore(double score) {
            scores.add(score);
        }

        // Method to calculate the average score
        public double calculateAverage() {
            double sum = 0;
            for (double score : scores) {
                sum += score;
            }
            return sum / scores.size();
        }

        // Method to find the highest score
        public double findHighest() {
            double max = scores.get(0);
            for (double score : scores) {
                if (score > max) {
                    max = score;
                }
            }
            return max;
        }

        // Method to find the lowest score
        public double findLowest() {
            double min = scores.get(0);
            for (double score : scores) {
                if (score < min) {
                    min = score;
                }
            }
            return min;
        }
    }
}

// Main class to run the program
public class Main {
    public static void main(String[] args) {
        // Bai1 - GradeTracker Example
        System.out.println("Bai 1: GradeTracker Example");
        Bai1.GradeTracker tracker1 = new Bai1.GradeTracker(5);
        tracker1.addScore(0, 85);
        tracker1.addScore(1, 90);
        tracker1.addScore(2, 78);
        tracker1.addScore(3, 88);
        tracker1.addScore(4, 92);
        System.out.println("Average score: " + tracker1.calculateAverage());
        System.out.println("Highest score: " + tracker1.findHighest());
        System.out.println("Lowest score: " + tracker1.findLowest());

        // Bai2 - MultiSubjectGradeTracker Example
        System.out.println("\nBai 2: MultiSubjectGradeTracker Example");
        Bai2.MultiSubjectGradeTracker multiSubjectTracker = new Bai2.MultiSubjectGradeTracker(3); // 3 môn học
        multiSubjectTracker.addScore(0, 0, 85); // Môn 1, Học sinh 0
        multiSubjectTracker.addScore(0, 1, 90); // Môn 1, Học sinh 1
        multiSubjectTracker.addScore(1, 0, 80); // Môn 2, Học sinh 0
        multiSubjectTracker.addScore(1, 1, 95); // Môn 2, Học sinh 1
        multiSubjectTracker.addScore(2, 0, 70); // Môn 3, Học sinh 0
        multiSubjectTracker.addScore(2, 1, 85); // Môn 3, Học sinh 1

        System.out.println("Môn 1 - Average: " + multiSubjectTracker.calculateAverage(0));
        System.out.println("Môn 2 - Average: " + multiSubjectTracker.calculateAverage(1));
        System.out.println("Môn 3 - Average: " + multiSubjectTracker.calculateAverage(2));

        // Bai3 - GradeTrackerList Example
        System.out.println("\nBai 3: GradeTrackerList Example");
        Bai3.GradeTrackerList tracker3 = new Bai3.GradeTrackerList();
        tracker3.addScore(85);
        tracker3.addScore(90);
        tracker3.addScore(78);
        tracker3.addScore(88);
        tracker3.addScore(92);
        System.out.println("Average score: " + tracker3.calculateAverage());
        System.out.println("Highest score: " + tracker3.findHighest());
        System.out.println("Lowest score: " + tracker3.findLowest());

        // Bai4 - GradeTrackerArrayList Example
        System.out.println("\nBai 4: GradeTrackerArrayList Example");
        Bai4.GradeTrackerArrayList tracker4 = new Bai4.GradeTrackerArrayList();
        tracker4.addScore(85);
        tracker4.addScore(90);
        tracker4.addScore(78);
        tracker4.addScore(88);
        tracker4.addScore(92);
        System.out.println("Average score: " + tracker4.calculateAverage());
        System.out.println("Highest score: " + tracker4.findHighest());
        System.out.println("Lowest score: " + tracker4.findLowest());
    }
}
