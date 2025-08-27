/*
3 7 5 2 6 1 4
1 7 5 2 6 3 4
1 2 5 7 6 3 4
1 2 3 7 6 5 4
그리디하게는 안됨

그냥 순서대로 확인 ㄱㄱ
그래서
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        System.out.println(solution(N, arr));
    }

    public static int solution(int N, int[] arr) {
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int result = 0;

        for (int i = 0; i < N; i++) result = Math.max(result, dp[i]);

        return N - result;
    }
}
