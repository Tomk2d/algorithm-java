package 과제테스트;

/*
*       10. equals 재정의의 함정 문제 다음 클래스에서
*           equals만 재정의했을 때 발생할 수 있는 문제를 설명하고
*           해결 방법을 제시하시오.
*           class Member { private Long id; }
* */

import java.util.Set;
import java.util.HashSet;

// 설명 : equals 를 오버라이딩하여 구현하는 경우, 해당 객체 내부의 값을 비교합니다.
// 하지만 Hash 기반의 데이터 셋 등을 활용한다면, 먼저 hashCode 를 비교하여 객체 동일성을 비교하는데,
// 이때 객체의 경우 해당 메모리 값을 기반으로 해쉬코드가 정해집니다.
// 하지만 우리가 하려는건 id 가 같다면 차단하는 행위이기 때문에, hashCode 를 재정의 해주어야 합니다.
public class hashcode_equals {
    public static void main(String[] args) {
        Set<Member> members = new HashSet<>();
        members.add(new Member(1L));
        members.add(new Member(2L));
        members.add(new Member(3L));
        members.add(new Member(4L));
        members.add(new Member(5L));

        members.add(new Member(1L));
        members.add(new Member(2L));
        members.add(new Member(3L));
        members.add(new Member(4L));
        members.add(new Member(5L));

        System.out.println(members.size());
    }

    public static class Member{
        private Long id;

        public Member(Long id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o){
            if(this == o) return true;
            if(!(o instanceof Member)) return false;

            Member member = (Member) o;
            return id.equals(member.id);
        }

        @Override
        public int hashCode(){
            return id.hashCode();
        }
    }
}
