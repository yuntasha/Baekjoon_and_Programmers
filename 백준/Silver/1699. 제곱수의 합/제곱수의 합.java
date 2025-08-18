/*
DP로 풀자
탑다운
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    public static int solution(int N) {
        int[] dp = new int[N + 1];

        for (int i = 0; i <= N; i++) dp[i] = i;

        for (int s = 0; s < N; s++) {
            for (int i = 1; i * i + s <= N; i++) {
                dp[s + i * i] = Math.min(dp[s + i * i], dp[s] + 1);
            }
        }

        return dp[N];
    }
}