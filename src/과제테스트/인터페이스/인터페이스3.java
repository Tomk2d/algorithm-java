package 과제테스트.인터페이스;

/*
*       3. 인터페이스 다형성 활용 문제
        다음 인터페이스를 이용하여

        interface Notifier {
            void send(String message);
        }

        * EmailNotifier
        * SmsNotifier
        두 구현 클래스를 만들고,
        Notifier 타입 배열로 모든 알림을 한 번에 전송하는 코드를 작성하시오.
*
* */

public class 인터페이스3 {
    public static void main(String[] args) {
        Notifier[] notifiers = {
            new EmailNotifier(),
            new SmsNotifier(),
        };

        for(Notifier notifier : notifiers){
            notifier.send("이 메시지는");
        }
    }

    static interface Notifier{
        void send(String message);
    }

    static class EmailNotifier implements Notifier{
        @Override
        public void send(String message) {
            System.out.println(message + " for email");
        }
    }

    static class SmsNotifier implements Notifier{
        @Override
        public void send(String message) {
            System.out.println(message + " for sms");
        }
    }
}
