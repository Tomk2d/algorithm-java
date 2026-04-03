package 과제테스트;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class comparable {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(100));
        list.add(new Student(90));
        list.add(new Student(99));

        // 오름차순
        Comparator<Student> comparator = (a, b) -> a.compareTo(b);
        list.sort(comparator);

        for (Student student : list) {
            System.out.println(student.score);
        }

        System.out.println("==========================");

        // 내림차순
        comparator = (a, b) -> b.compareTo(a);
        list.sort(comparator);
        for (Student student : list) {
            System.out.println(student.score);
        }
    }

    public static class Student implements Comparable<Student>{
        private int score;

        Student(int score){
            this.score = score;
        }

        @Override
        public int compareTo(Student o) {
            return Integer.compare(this.score, o.score);
        }
    }
}
