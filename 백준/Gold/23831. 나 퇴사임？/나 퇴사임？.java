/*

 */

import java.io.*;
import java.util.*;

public class Main {

    static int IMPOSSIBLE = -100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int A = Integer.parseInt(input.nextToken());
        int B = Integer.parseInt(input.nextToken());

        int[][] feels = new int[N][4];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            feels[i][0] = Integer.parseInt(input.nextToken());
            feels[i][1] = Integer.parseInt(input.nextToken());
            feels[i][2] = Integer.parseInt(input.nextToken());
            feels[i][3] = Integer.parseInt(input.nextToken());
        }

        System.out.println(solution(N, A, B, feels));
    }

    public static int solution(int N, int A, int B, int[][] feels) {
        return find(0, 0, 0, 0, N, A, B, feels, new int[N][A + 1][B + 1][2]);
    }

    public static int find(int n, int a, int b, int restRoom, int N, int A, int B, int[][] feels, int[][][][] dp) {
        if (a > A) return IMPOSSIBLE;
        if (restRoom > 1) return IMPOSSIBLE;
        if (n == N) return b < B ? IMPOSSIBLE : 0;
        if (dp[n][a][b][restRoom] != 0) return dp[n][a][b][restRoom];

        int result = IMPOSSIBLE;

        result = Math.max(result, find(n + 1, a, Math.min(b + 1, B), 0, N, A, B, feels, dp) + feels[n][0]); // 정독실
        result = Math.max(result, find(n + 1, a, Math.min(b + 1, B), 0, N, A, B, feels, dp) + feels[n][1]); // 소학습실
        result = Math.max(result, find(n + 1, a, b, restRoom + 1, N, A, B, feels, dp) + feels[n][2]); // 휴게실
        result = Math.max(result, find(n + 1, a + 1, b, 0, N, A, B, feels, dp) + feels[n][3]); // 요양

        dp[n][a][b][restRoom] = result;

        return result;
    }
}