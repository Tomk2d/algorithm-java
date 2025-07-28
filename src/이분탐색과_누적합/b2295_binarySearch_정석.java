package 이분탐색과_누적합;

import java.util.*;

public class b2295_binarySearch_정석
{
    private static List<Integer> numbers = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] array = new int[N];
        for(int i=0; i<N; i++){
            array[i] = sc.nextInt();
        }

        Arrays.sort(array);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                numbers.add(array[i] + array[j]);
            }
        }

        Collections.sort(numbers);



        for (int k = N - 1; k >= 0; k--) {
            for (int z = 0; z < N; z++) {
                int target = array[k] - array[z];
                if(binarySearch(target)){
                    System.out.println(array[k]);
                    return;
                }
            }
        }

    }

    private static boolean binarySearch(int target){
        int start = 0;
        int end = numbers.size()-1;

        while(start <= end){
            int mid = (start+end) / 2;
            int number = numbers.get(mid);
            if(number == target) return true;
            else if(number < target) start = mid+1;
            else if(number > target) end = mid-1;
        }
        return false;
    }
}

