/*
DP로 가보자
DP[가로 좌표][세로 좌표] = 가장 큰 정사각형 크기
DP[가로][세로] = max(DP[가로 - 1][세로 - 1], DP[가로 - 1][세로], DP[가로][세로 - 1]) + 1
*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = read();
        int M = read();

        int[][] map = new int[N][];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, M, map));
    }

    public static int solution(int N, int M, int[][] map) {
        int[][] dp = new int[N][M];

        int result = 0;

        for (int i = 0; i < N; i++) {
            dp[i][0] = map[i][0];
            result = Math.max(result, dp[i][0]);
        }
        for (int j = 0; j < M; j++) {
            dp[0][j] = map[0][j];
            result = Math.max(result, dp[0][j]);
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (map[i][j] == 0) continue;
                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                result = Math.max(result, dp[i][j]);
            }
        }

        return result * result;
    }

    static int read() throws IOException {
        int n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}