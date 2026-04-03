package 과제테스트;

import 과제테스트.옵셔널.optional1.User;

/*
*       8. 제네릭 메서드 작성 임의 타입의 두 값을 받아
*       같으면 true 다르면 false 를 반환하는
*       제네릭 메서드를 작성하시오.
*
*
* */

public class generic {
    public static void main(String[] args) {
        User user1 = new User(null);
        User2 user2 = new User2("a", 1);
        User3 user3 = new User3("a", 1);
        User2 user22 = new User2("a", 1);


        //System.out.println(isEqual(user1, user2));
        System.out.println(isEqual(user2, user3));
        System.out.println(isEqual(user22, user2));
    }

    public static <T> boolean isEqual(T a, T b){
        return java.util.Objects.equals(a, b);
    }

    public static class User2{
        String name;
        int age;

        User2(String name,int age){
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            // 동일한 인스턴스일때
            if (this == o) return true;
            // 같은 타입인지
            if (!(o instanceof User2)) return false;

            User2 u = (User2) o;
            return age == u.age && name.equals(u.name);
        }
    }

    public static class User3{
        String name;
        int age;
        User3(String name,int age){
            this.name = name;
            this.age = age;
        }
    }
}
