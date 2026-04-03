package 과제테스트.옵셔널;

/*
*       6. Optional 활용 문제
*       다음 메서드를 Optional을 이용하여
*       NPE가 발생하지 않도록 수정하시오.
*/

import java.util.Optional;

public class optional1 {
    public static void main(String[] args) {
        User uijin = new User(new Profile("uijinshin"));

        User hk = new User(null);

        System.out.println(getUserName(uijin));
        System.out.println(getUserName(hk));

    }

    public static String getUserName(User user) {
        return Optional.ofNullable(user)
            .map(User::getProfile)
            .map(Profile::getName)
            .orElse(null);
    }

    public static class User {
        Profile profile;

        User(Profile profile) {
            this.profile = profile;
        }

        public Profile getProfile() {
            return profile;
        }
    }

    public static class Profile{
        private String name;

        Profile(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
