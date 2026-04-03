package 과제테스트.인터페이스;

/*
    6. 디폴트 메서드 활용 문제
    다음 인터페이스를 작성하시오.
    * log(String message) 는 default 메서드로 제공
    * write(String message) 는 구현 클래스에서 반드시 구현
    그리고 이를 구현하는 FileLogger 클래스를 작성하시오.
*
* */

public class 인터페이스4_공통구현 {
    public static void main(String[] args) {
        Logger logger = new FileLogger();
        logger.log("sdaf");
        logger.write("sdaf");
    }

    static interface Logger{

        default void log(String msg){
            System.out.println("logger msg : " + msg);
        }

        public void write(String msg);
    }

    public static class FileLogger implements Logger{
        @Override
        public void write(String msg) {
            System.out.println("file mssage : " + msg);
        }
    }
}
