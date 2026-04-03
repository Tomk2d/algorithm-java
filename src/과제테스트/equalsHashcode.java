package 과제테스트;

import java.util.*;
import java.io.*;

/*
    1. equals / hashCode 재정의
    다음 조건을 만족하는 User 클래스를 작성하시오.
    id가 같으면 같은 객체로 판단한다.
    equals와 hashCode를 함께 재정의한다.
*/

public class equalsHashcode {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());


    }

    public static class User{
        private Long id;

        @Override
        public boolean equals(Object o){
            if(this == o) return true;
            if(!(o instanceof User)) return false;

            User user = (User) o;
            return Objects.equals(id, user.id);
        }

        @Override
        public int hashCode(){
            return Objects.hash(id);
        }
    }
}
