package 과제테스트;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
*       3. Comparator 작성 이미 만들어진 Product 클래스에 대해
*       price 내림차순 price가 같으면 name 오름차순 정렬이 되도록
*       Comparator를 작성하시오.
* */

public class comparator {
    public static void main(String[] args) {



        List<Student> students = new ArrayList<>();
        students.add(new Student("Kim", 90));
        students.add(new Student("Lee", 100));
        students.add(new Student("Park", 90));
        students.add(new Student("Choi", 100));

        students.sort(comparator);

        for(Student student : students){
            System.out.println(student.getName() + " : " + student.getScore());
        }
    }

    public static Comparator<Student> comparator = Comparator.comparing(Student::getScore)
        .reversed().thenComparing(Student::getName);

    public static class Student{
        private String name;
        private int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore(){
            return score;
        }
    }
}
