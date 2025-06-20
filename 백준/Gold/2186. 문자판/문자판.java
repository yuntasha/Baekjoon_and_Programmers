/*
DFS로 그냥 찾자
100 * 100 * 80 = 800_000 80만
DFS에 DP로 가야한다
그냥 -1로 초깃값 설정하고 진행하자
 */

import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        char[][] map = new char[N][];

        for (int i = 0; i < N; i++) {
            map[i] = bf.readLine().toCharArray();
        }

        char[] word = bf.readLine().toCharArray();

        System.out.println(solution(N, M, K, map, word));
    }

    static int solution(int N, int M, int K, char[][] map, char[] word) {
        int result = 0;

        int[][][] dp = new int[N][M][word.length];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (word[0] == map[i][j]) {
                    result += dfs(i, j, 1, N, M, K, map, word, dp);
                }
            }
        }

        return result;
    }

    static int dfs(int n, int m, int now, int N, int M, int K, char[][] map, char[] word, int[][][] dp) {
        if (now == word.length) return 1;
        if (dp[n][m][now] != -1) return dp[n][m][now];

        dp[n][m][now] = 0;

        for (int d = 0; d < 4; d++) {
            int x = n;
            int y = m;

            for (int i = 0; i < K; i++) {
                x += dx[d];
                y += dy[d];
                if (!isIn(x, y, N, M)) break;
                if (map[x][y] == word[now]) {
                    dp[n][m][now] += dfs(x, y, now + 1, N, M, K, map, word, dp);
                }
            }
        }

        return dp[n][m][now];
    }

    static boolean isIn(int x, int y, int N, int M) {
        return 0 <= x && 0 <= y && x < N && y < M;
    }
}