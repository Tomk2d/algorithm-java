package 과제테스트;

import java.util.Arrays;
import java.util.List;

public class streamAPI {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 4, 4, 4, 4, 12, 20, 12, 8, 22, 15, 20, 30, 30, 20, 10, 10, 14, 14, 14, 16);

        List<Integer> result = list.stream()
            .filter(a -> a % 2 ==0)
            .filter(a -> a > 10)
            .distinct()
            .sorted()
            .toList();

        System.out.println(result);
    }
}
