package 과제테스트.제네릭;

public class 제네릭2 {
    public static void main(String[] args) {
        System.out.println(isEquals("Tom", "Tom"));
    }

    public static <T> boolean isEquals(T a, T b){
        return java.util.Objects.equals(a, b);
    }
}
