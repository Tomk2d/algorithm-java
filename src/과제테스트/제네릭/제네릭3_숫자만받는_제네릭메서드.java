package 과제테스트.제네릭;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class 제네릭3_숫자만받는_제네릭메서드 {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Double> list2 = List.of(1.2, 2.4, 4.6, 2.4, 1.4);
        Set<Integer> set = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(sumGeneric(set) + "," + sumGeneric(list2) + "," + sumGeneric(list));

    }

    public static <T extends Number> double sumGeneric(Collection<T> numList){
        double sum = 0;

        for (T num : numList) {
            sum += num.doubleValue();
        }

        return sum;
    }

    public static double sumWildCard(Collection<? extends Number> nums){
        double sum = 0;

        for(Number num : nums){
            sum += num.doubleValue();
        }

        return sum;
    }
}
