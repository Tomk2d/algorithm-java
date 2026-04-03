package 과제테스트.옵셔널;

import java.util.Optional;
import 과제테스트.옵셔널.optional1.User;
import 과제테스트.옵셔널.optional1.Profile;

/*
*       User 에서 이름을 Optional<String> 으로
*       반환하는 메서드로 작성하시오.
* */

public class optional3 {
    public static void main(String[] args) {
        User fake = new User(null);
        User fake2 = new User(new Profile(null));
        User real = new User(new Profile("REAL"));

        getUserNameOptional(fake).ifPresent(System.out::println);
        getUserNameOptional(fake2).ifPresent(System.out::println);
        getUserNameOptional(real).ifPresent(System.out::println);
    }

    public static Optional<String> getUserNameOptional(User user){
        return Optional.ofNullable(user)
            .map(User::getProfile)
            .map(Profile::getName);
    }

    public static Optional<String> getUserNameOptional(Profile profile){
        return Optional.ofNullable(profile)
            .map(Profile::getName);
    }
}
