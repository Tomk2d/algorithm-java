package 과제테스트.상속;

/*
*
    4. 오버라이딩 + 다형성 확인 문제
    Animal 클래스 void sound() 를 만들고,
    * Dog
    * Cat
    이 이를 오버라이딩하도록 구현한 뒤,

    Animal a = new Dog();
    a.sound();

    형태로 호출되는 메서드를 확인하시오.
*
* */

public class 상속4 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();

        dog.sound();
        cat.sound();
    }

    public static class Dog extends Animal {

        @Override
        public void sound(){
            System.out.println("bow Oow");
        }
    }

    public static class Cat extends Animal {

        @Override
        public void sound(){
            System.out.println("mew mew");
        }
    }

    public static class Animal{
        public void sound(){
        }
    }
}
