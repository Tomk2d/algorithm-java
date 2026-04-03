package 과제테스트.상속;

/*
*       다음 클래스를 설계하시오.
*       * Payment
*           * CardPayment
*           * CashPayment
*       그리고 Payment[] payments 배열에
*       서로 다른 구현 객체를 담아
*       공통 메서드를 호출하시오.
* */
public class 상속5_부모메서드타입으로_배열에_넣기 {
    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(),
            new CashPayment()
        };

        for(Payment payment : payments){
            payment.pay();
        }
    }

    public static abstract class Payment{
        protected abstract void pay();
    }

    public static class CardPayment extends Payment {

        @Override
        protected void pay() {
            System.out.println("카드결제");
        }
    }

    public static class CashPayment extends Payment {

        @Override
        protected void pay() {
            System.out.println("현금결제");
        }
    }

}
