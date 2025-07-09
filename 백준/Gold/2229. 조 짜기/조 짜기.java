/*
쉽게쉽게 가보자
N번째에서 끝나는 경우로 만들어
그래서 ㅇㅇ
 */

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.print(solution(N, arr));
    }

    public static int solution(int N, int[] arr) {
        int[] dp = new int[N];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            dp[i] = max - min;
        }

        for (int s = 1; s < N; s++) {
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            for (int n = s; n < N; n++) {
                min = Math.min(min, arr[n]);
                max = Math.max(max, arr[n]);
                dp[n] = Math.max(dp[n], max - min + dp[s - 1]);
            }
        }

        return dp[N - 1];
    }
}