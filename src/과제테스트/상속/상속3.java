package 과제테스트.상속;

/*
        * 3. super 키워드 사용 문제
        Employee 클래스에
        Employee(String name)생성자가 있을 때,
        Manager 클래스에서
        * name
        * department
        를 받아 부모 생성자를 반드시 호출하도록 구현하시오.
* */

public class 상속3 {
    public static void main(String[] args) {

    }

    public static class Manager extends Employee {
        String department;

        Manager(String name, String department){
            super(name);
            this.department = department;
        }
    }

    public static class Employee{
        String name;

        Employee(String name) {
            this.name = name;
        }
    }
}
