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
        return find(N, new int[N + 1]);
    }

    public static int find(int n, int[] dp) {
        if (dp[n] > 0) return dp[n];
        if (n == 0) return 0;

        dp[n] = n;

        for (int i = 1; i * i <= n; i++) {
            dp[n] = Math.min(dp[n], find(n - (i * i), dp) + 1);
        }

        return dp[n];
    }
}