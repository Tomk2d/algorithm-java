package 과제테스트.옵셔널;

import java.util.NoSuchElementException;
import 과제테스트.옵셔널.optional1.User;
import 과제테스트.옵셔널.optional1.Profile;
import java.util.Optional;

/*
*       어디서 NPE 가 발생했는지를 예외로 던지고자 합니다.
*       이 부분을 찾아서 "유저없음", "프로필없음", "이름없음"
*       으로 메시지를 던져주세요.
*
* */

public class optional4 {
    public static void main(String[] args) {
        User noProfile = new User(null);
        User noName = new User(new Profile(null));
        User perfect = new User(new Profile("perfect man"));
        System.out.println(getNameForUser(perfect));
        //System.out.println(getNameForUser(noProfile));
        System.out.println(getNameForUser(noName));
    }

    public static String getNameForUser(User user) {
        User u = Optional.ofNullable(user)
            .orElseThrow(() -> new NullPointerException("유저없음"));

        Profile p = Optional.ofNullable(u.getProfile())
            .orElseThrow(() -> new NullPointerException("프로필 없음"));

        String name = Optional.ofNullable(p.getName())
            .orElseThrow(() -> new NoSuchElementException("이름없음"));

        return name;
    }
}
