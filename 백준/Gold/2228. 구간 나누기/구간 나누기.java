/*
M개로 정확하게 나눠지고
서로 붙어있으면 안된다
아예 나누는게 불가능한건 없고
음음
그럼 최소값이  -32768 * 100이고
-3_276_800

0개부터 M개까지 구간함 사용할때 최댓값
바로 이전 것 계승 or -2꺼에 구간합 만들기?
음
구간합으로 만들고

바텀업으로 올려보자
0부터 시작하는 구간합 만드는 경우 ~~~
1부터 시작하는 구간합 만드는 경우 ~~~


 */

import java.io.*;
import java.util.*;

public class Main {

    static int MIN = -100_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(bf.readLine());

        System.out.print(solution(N, M, arr));
    }

    public static int solution(int N, int M, int[] arr) {
        int[] sum = new int[N + 1];

        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        // a ~ b 구간합이면 sum[b + 1] - sum[a]

        int[][] dp = new int[N + 2][M + 1]; // [시작 가능 위치][구간 개수]

        for (int[] d : dp) {
            Arrays.fill(d, MIN);
            d[0] = 0;
        }

        for (int s = 0; s < N; s++) {
            if (s > 0) {
                for (int m = 0; m <= M; m++) dp[s][m] = Math.max(dp[s - 1][m], dp[s][m]);
            }
            for (int e = s; e < N; e++) {
                int now = sum[e + 1] - sum[s];
                for (int m = 0; m < M; m++) {
                    dp[e + 2][m + 1] = Math.max(dp[e + 2][m + 1], dp[s][m] + now);
                }
            }
        }

        return Math.max(Math.max(dp[N - 1][M], dp[N][M]), dp[N + 1][M]);
    }
}