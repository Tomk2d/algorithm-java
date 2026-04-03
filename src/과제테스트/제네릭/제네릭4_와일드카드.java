package 과제테스트.제네릭;

import java.util.Collection;
import java.util.Set;
import java.util.List;

/*
*   4. 와일드카드(?) 사용 문제
    다음 메서드가
    - Number를 상속한 타입의 List/Set 등의 자료구조 만 받을 수 있도록
    메서드 시그니처를 완성하시오.
* */

public class 제네릭4_와일드카드 {
    public static void main(String[] args) {
        Set<Integer> set = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Double> list = List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0);

        printNumbers(set);
        printNumbers(list);
    }

    public static void printNumbers(Collection<? extends Number> numbers){
        System.out.println(numbers);
    }
}
