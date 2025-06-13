/*
홀짝 기준으로 교환 가능
실제 정렬해보자
그리고 홀짝 위치 바뀐거 체크하자
합이였네
1000 * 1000이잖아
그러면 1_000_000_000인데
개오바임

 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] map = new int[N][];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, M, map));
    }

    static int solution(int N, int M, int[][] map) {
        int[][] dp = new int[N][M];

        dp[0][0] = map[0][0];

        for (int i = 1; i < M; i++) {
            dp[0][i] = map[0][i] + dp[0][i - 1];
        }

        for (int i = 0; i < N - 1; i++) {
            Arrays.fill(dp[i + 1], Integer.MIN_VALUE);
            for (int s = 0; s < M; s++) {
                int now = 0;
                for (int e = s; e < M; e++) {
                    now += map[i + 1][e];
                    dp[i +  1][e] = Math.max(dp[i + 1][e], dp[i][s] + now);
                    dp[i +  1][s] = Math.max(dp[i + 1][s], dp[i][e] + now);
                }
            }
        }

        return dp[N - 1][M - 1];
    }
}