package 손코딩_대비;

public class ReverseMethod {
    public static void reverse(int[] array) {
        int N = array.length;
        int half = N/2;

        for(int i=0; i<half; i++) {
            int temp = array[i];
            array[i] = array[N-1-i];
            array[N-1-i] = temp;
        }
        System.out.println("Reversed array is: ");
        for (int n : array){
            System.out.print(n+" ");
        }
        System.out.println();
    }
}
