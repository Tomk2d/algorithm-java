package 과제테스트.옵셔널;

import java.util.Optional;
import 과제테스트.옵셔널.optional1.Profile;
import 과제테스트.옵셔널.optional1.User;

/*
*  User 객체가 존재할 때만 이름을 출력하도록 Optional을 사용하시오
* */

public class optional2 {
    public static void main(String[] args) {
        User uijin = new User(null);
        User realUijin = new User(new Profile("real me"));

        printUserName(uijin);
        printUserName(realUijin);
    }

    public static void printUserName(User user){
        Optional.ofNullable(user)
            .map(User::getProfile)
            .map(Profile::getName)
            .ifPresent(System.out::println);
    }
}
