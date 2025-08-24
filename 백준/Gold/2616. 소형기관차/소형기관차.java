/*

 */

import java.io.*;
import java.util.*;

public class Main {

    static int IMPOSSIBLE = -100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];

        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(input.nextToken());

        int K = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, arr, K));
    }

    public static int solution(int N, int[] arr, int K) {
        return find(N - 1, 3, arr, K, new int[N][4]);
    }

    public static int find(int n, int r, int[] arr, int K, int[][] dp) {
        if (n < K - 1) return 0;
        if (r == 0) return 0;
        if (dp[n][r] > 0) return dp[n][r];

        int add = 0;

        for (int i = n; i > n - K; i--) {
            add += arr[i];
        }

        return dp[n][r] = Math.max(find(n - 1, r, arr, K, dp), find(n - K, r - 1, arr, K, dp) + add);
    }
}