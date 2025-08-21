/*
오른쪽 아래로만 갈 수 있음
H는 못가고
더 꺾으면 못하고
흠흠
50 50 2 4
가로 세로 방향 바꾼 숫자
2만
50번이니까
100만

탑다운 버전으로 ㄱㄱ
 */

import java.io.*;
import java.util.*;

public class Main {

    static char EMPTY = '.';
    static char HAYBALE = 'H';

    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        StringBuilder output = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int N = Integer.parseInt(input.nextToken());
            int K = Integer.parseInt(input.nextToken());

            char[][] map = new char[N][];

            for (int i = 0; i < N; i++) map[i] = bf.readLine().toCharArray();

            output.append(solution(N, K, map)).append("\n");
        }

        System.out.print(output);
    }

    public static int solution(int N, int K, char[][] map) {
        int[][][][] dp = new int[N][N][K + 1][2];
        return find(0, 1, 0, 0, N, K, map, dp) + find(1, 0, 0, 1, N, K, map, dp);
    }

    public static int find(int n, int m, int k, int dir, int N, int K, char[][] map, int[][][][] dp) {
        if (!isIn(n, m, N)) return 0;
        if (map[n][m] == HAYBALE) return 0;
        if (k > K) return 0;
        if (n == N - 1 && m == N - 1) return 1;
        if (dp[n][m][k][dir] != 0) return Math.max(dp[n][m][k][dir], 0);

        int result = find(n + dx[dir], m + dy[dir], k, dir, N, K, map, dp) + find(n + dx[dir ^ 1], m + dy[dir ^ 1], k + 1, dir ^ 1, N, K, map, dp);

        if (result == 0) dp[n][m][k][dir] = -1;
        else dp[n][m][k][dir] = result;

        return Math.max(dp[n][m][k][dir], 0);
    }

    public static boolean isIn(int n, int m, int N) {
        return 0 <= n && n < N && 0 <= m && m < N;
    }
}