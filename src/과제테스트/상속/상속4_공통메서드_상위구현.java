package 과제테스트.상속;

/*

        7. 공통 로직을 부모 클래스로 올리는 리팩토링 문제 다음
        두 클래스에 중복 코드가 있다.
        * FileLogger
        * ConsoleLogger 두 클래스의 공통 로직을
        부모 클래스 BaseLogger로 추출하도록
        상속 구조로 리팩토링하시오.

        class FileLogger {

            public void log(String message) {
                System.out.println("log start");
                System.out.println("write file: " + message);
                System.out.println("log end");
            }
        }

        class ConsoleLogger {

            public void log(String message) {
                System.out.println("log start");
                System.out.println("console: " + message);
                System.out.println("log end");
            }
        }
*/

public class 상속4_공통메서드_상위구현 {
    public static void main(String[] args) {
        FileLogger fileLogger = new FileLogger();
        ConsoleLogger consoleLogger = new ConsoleLogger();

        fileLogger.log("파일로그 입니다");
        System.out.println();
        consoleLogger.log("콘솔로그 입니다");

    }

    public static abstract class BaseLogger{

        public void log(String message){
            System.out.println("log start");
            write(message);
            System.out.println("log end");
        }
        protected abstract void write(String message);
    }

    public static class FileLogger extends BaseLogger{

        @Override
        protected void write(String message){
            System.out.println("write file : " + message);
        }
    }

    public static class ConsoleLogger extends BaseLogger {

        @Override
        protected void write(String message){
            System.out.println("write console : " + message);
        }
    }
}
