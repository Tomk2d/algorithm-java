package 과제테스트.상속;

/*
        1. 기본 상속 + 필드 재사용
        Person 클래스에
        * name
        * age
        를 두고,
        Student 클래스가 이를 상속받아
        * studentId
        를 추가하시오.
* */

public class 상속1 {
    public static void main(String[] args) {

        Student student = new Student("의진", 100, 1);
        System.out.println(student.getName());
        System.out.println(student.getAge());
        System.out.println(student.getStudentId());
    }

    public static class Student extends Person {
        private int studentId;

        public Student(String name, int age, int studentId){
            super(name, age);
            this.studentId = studentId;
        }

        public int getStudentId() {
            return studentId;
        }
    }

    public static class Person{
        private String name;
        private int age;

        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
