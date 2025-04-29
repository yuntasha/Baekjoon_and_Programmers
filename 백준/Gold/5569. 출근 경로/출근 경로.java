/*
정석으로해 양방향 가능한지 넣고 이전 방향 지정해서 넣어두자
이전 방향, 바꿀 수 있는지
 */

import java.io.*;
import java.util.*;

public class Main {

    static int MAX = 100_000;

    static int[] dx = new int[]{0, 1};
    static int[] dy = new int[]{1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());

        System.out.println(solution(n, m));
    }

    static int solution(int n, int m) {
        // x, y, 바꿀 수 있는지(1 못바꿈), 이전 방향
        int[][][][] dp = new int[n][m][2][2];

        dp[0][0][1][0] = dp[0][0][1][1] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < 2; d++) {

                    int x = i + dx[d];
                    int y = j + dy[d];

                    if (!(x < n && y < m)) continue;

                    dp[x][y][1][d] += dp[i][j][0][d ^ 1];
                    dp[x][y][1][d] %= MAX;

                    dp[x][y][0][d] += dp[i][j][0][d] + dp[i][j][1][d];
                    dp[x][y][0][d] %= MAX;
                }
            }
        }

        return (dp[n - 1][m - 1][0][0] + dp[n - 1][m - 1][0][1] + dp[n - 1][m - 1][1][0] + dp[n - 1][m - 1][1][1]) % MAX;
    }
}