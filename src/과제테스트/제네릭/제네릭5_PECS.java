package 과제테스트.제네릭;

import java.util.ArrayList;
import java.util.List;

public class 제네릭5_PECS {
    public static void main(String[] args) {
        List<Integer> data = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> empty = new ArrayList<>();

        copyList(data, empty);

        System.out.println(empty);
    }

    public static <T> void copyList(List<? extends T> source, List<? super T> target){
        for(T item : source){
            target.add(item);
        }
    }

    public static <T> boolean isValidMember(T value, List<T> memberList){
        return memberList.contains(value);
    }
}
