package 슬라이딩윈도우;

import java.util.*;
import java.io.*;

public class b21921 {

    public static int maxPeople = 0;
    public static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());


        int[] dayVisiter = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dayVisiter[i] = Integer.parseInt(st.nextToken());
        }


        int currentSum = 0;
        for (int i = 0; i < X; i++) {
            currentSum += dayVisiter[i];
        }

        maxPeople = currentSum;
        cnt = 1;

        for (int i = X; i < N; i++) {
            currentSum += dayVisiter[i];
            currentSum -= dayVisiter[i - X];

            if (currentSum > maxPeople) {
                maxPeople = currentSum;
                cnt = 1;
            } else if (currentSum == maxPeople) {
                cnt++;
            }
        }


        if (maxPeople == 0) {
            System.out.print("SAD");
        } else {
            System.out.println(maxPeople);
            System.out.println(cnt);
        }
    }
}
