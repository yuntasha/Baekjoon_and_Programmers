/*
처음 갈 수 있는 거리는 1
+1 -1해서 갈 수 있다
dp[현위치][이전 거리]
 */

import java.io.*;
import java.util.*;

public class Main {

    static int INF = 1_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        boolean[] small = new boolean[N + 1];

        for (int i = 0; i < M; i++) small[Integer.parseInt(bf.readLine())] = true;

        System.out.print(solution(N, M, small));
    }

    public static int solution(int N, int M, boolean[] small) {
        int[][] dp = new int[142][N + 1];

        for (int[] d : dp) Arrays.fill(d, INF + 1);

        int result = find(2, 1, N, small, dp) + 1;

        return result >= INF ? -1 : result;
    }

    public static int find(int now, int move, int N, boolean[] small, int[][] dp) {
        if (now > N) return INF;
        if (move == 0) return INF;
        if (now == N) return 0;
        if (small[now]) return INF;
        if (dp[move][now] <= INF) return dp[move][now];

        dp[move][now] = Math.min(dp[move][now], find(now + move, move, N, small, dp) + 1);
        dp[move][now] = Math.min(dp[move][now], find(now + move - 1, move - 1, N, small, dp) + 1);
        dp[move][now] = Math.min(dp[move][now], find(now + move + 1, move + 1, N, small, dp) + 1);

        return dp[move][now];
    }
}