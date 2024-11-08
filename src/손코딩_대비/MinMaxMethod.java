package 손코딩_대비;

public class MinMaxMethod {
    public void min(int[] array){
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (min > array[i]) min = array[i];
        }
        System.out.println("min : "+ min);
    }
    public void max(int[] array){
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) max = array[i];
        }
        System.out.println("max : "+ max);
    }
}
