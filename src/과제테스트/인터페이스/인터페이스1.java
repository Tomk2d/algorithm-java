package 과제테스트.인터페이스;

/*
*   1. 인터페이스 기본 구현 문제
    다음 요구사항을 만족하는 Payment 인터페이스를 작성하고
    이를 구현하는 CardPayment 클래스를 작성하시오.
    요구사항
    * pay(int amount) 메서드
    * 결제 방식 이름을 반환하는 getType() 메서드
* */

public class 인터페이스1 {
    public static void main(String[] args) {
        Payment pay = new CardPayment();
        System.out.println(pay.getType() + " = " + pay.pay(10000));
    }

    public static interface Payment{
        public int pay(int amount);
        public String getType();
    }

    public static class CardPayment implements Payment {
        public int pay(int amount) {
            return amount * 2;
        }
        public String getType() {
            return "Card Payment";
        }
    }
}
