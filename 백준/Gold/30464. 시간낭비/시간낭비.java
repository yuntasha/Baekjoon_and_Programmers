/*
dp[칸 번호][반전한 숫자]
1부터 시작

 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static final int MAX = 1 << 15;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];
        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, arr));
    }

    public static int solution(int N, int[] arr) {
        int[][] dp = new int[N][3];

        dp[0][0] = 1;

        for (int i = 0; i < N - 1; i++) {
            if (dp[i][0] == 0) continue;
            if (arr[i] + i < N) dp[i + arr[i]][0] = Math.max(dp[i + arr[i]][0], dp[i][0] + 1);
            if (i - arr[i] >= 0) dp[i - arr[i]][1] = Math.max(dp[i - arr[i]][1], dp[i][0] + 1);
        }

        for (int i = N - 2; i >= 0; i--) {
            if (dp[i][1] == 0) continue;
            if (arr[i] + i < N) dp[i + arr[i]][2] = Math.max(dp[i + arr[i]][2], dp[i][1] + 1);
            if (i - arr[i] >= 0) dp[i - arr[i]][1] = Math.max(dp[i - arr[i]][1], dp[i][1] + 1);
        }

        for (int i = 0; i < N - 1; i++) {
            if (dp[i][2] == 0) continue;
            if (arr[i] + i < N) dp[i + arr[i]][2] = Math.max(dp[i + arr[i]][2], dp[i][2] + 1);
        }

        return Math.max(dp[N - 1][0], Math.max(dp[N - 1][1], dp[N - 1][2])) - 1;
    }
}