/*
i번째를 픽하는 애의 최소값
상대방은 그 누적합 - 최소값
N * N으로 전체 탐색하면서 최소 값 넣어버려

 */

import java.io.*;
import java.util.*;

public class Main {

    static String WIN = "A";
    static String SAME = "D";
    static String LOSE = "B";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        System.out.println(solution(N, Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        System.out.println(solution(N, Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
    }

    static String solution(int N, int[] arr) {
        int[] dp = new int[N];

        dp[0] = arr[0];
        for (int i = 1; i < N; i++) {
            arr[i] += arr[i - 1];
            dp[i] = arr[i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = i; j < N; j++) {
                dp[j] = Math.min(dp[j], arr[j] - dp[i - 1]);
            }
        }

//        System.out.println(Arrays.toString(dp));

        int total = arr[N - 1];
        if (total > (dp[N - 1] << 1)) {
            return WIN;
        }

        if (total < (dp[N - 1] << 1)) {
            return LOSE;
        }

        return SAME;
    }
}