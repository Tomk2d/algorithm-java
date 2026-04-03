package 과제테스트.제네릭;

/*
*   1. 제네릭 클래스 기본
    임의 타입을 저장하는 제네릭 클래스 Box<T>를 작성하시오.
    조건
    * 값을 저장하는 set
    * 값을 꺼내는 get
* */

public class 제네릭1 {
    public static void main(String[] args) {
        Box box = new Box();
        box.setValue(1234);
        System.out.println(box.getValue());

        box.setValue("ggggg");
        System.out.println(box.getValue());

    }

    public static class Box<T> {
        private T value;

        public void setValue(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

}
