package 손코딩_대비;

public class handCodingMain {


    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9};
        int[] array1 = {1,2,3,4,5,6,7,8,9,10};
        int[] array2 = {1,2,3,4,5,6,7,8,9,10,1,4,2,4,5,7,3,4,5,5,5,2,2,1,3,5,89,7,6,5};

        ReverseMethod reverseMethod = new ReverseMethod();
        reverseMethod.reverse(array);
        reverseMethod.reverse(array1);
        System.out.println();

        MinMaxMethod minMaxMethod = new MinMaxMethod();
        minMaxMethod.max(array);
        minMaxMethod.min(array1);
        System.out.println();

        DuplicateCheckMethod duplicateCheckMethod = new DuplicateCheckMethod();
        duplicateCheckMethod.duplicateCheck(array2);
        System.out.println();

        Gugudan gugudan = new Gugudan();
        gugudan.makeGugudan();
    }

}
