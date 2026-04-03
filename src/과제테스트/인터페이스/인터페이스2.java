package 과제테스트.인터페이스;

/*
    2. 추상 클래스 기본 구조 문제
    다음 조건을 만족하는 추상 클래스 Animal을 작성하시오.
    * name 필드
    * eat() 은 공통 구현
    * sound() 는 하위 클래스에서 반드시 구현하도록 한다
    그리고 Dog 클래스를 작성하여 상속하시오.
* */
public class 인터페이스2 {
    public static void main(String[] args) {
        Dog dog = new Dog("멍구");
        Cat cat = new Cat("나비");

        dog.eat();
        cat.eat();
    }

    public static abstract class Animal{
        String name;

        Animal(String name){
            this.name = name;
        }

        public void eat(){
            sound();
        }

        protected abstract void sound();
    }

    public static class Dog extends Animal{
        public Dog(String name){
            super(name);
        }

        @Override
        protected void sound() {
            System.out.println("멍멍쩝쩝");
        }
    }

    public static class Cat extends Animal{
        public Cat(String name){
            super(name);
        }

        @Override
        protected void sound() {
            System.out.println("냥냥쩝쩝");
        }
    }
}
