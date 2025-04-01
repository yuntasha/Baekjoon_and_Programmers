/*
총 얼마를 가지고 있는지
그 금액의 절반을 가져올 수 있는지
100 - 10만 가능? 불가능

*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int MAX_COIN = 100_000;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 0; t < 3; t++) {
            int N = Integer.parseInt(bf.readLine());

            int[][] coins = new int[N][2];

            for (int i = 0; i < N; i++) {
                StringTokenizer input = new StringTokenizer(bf.readLine());

                coins[i][0] = Integer.parseInt(input.nextToken());
                coins[i][1] = Integer.parseInt(input.nextToken());
            }

            System.out.println(solution(N, coins));
        }
    }

    public static int solution(int N, int[][] coins) {
        boolean[][] dp = new boolean[2][MAX_COIN + 1];

        for (int i = 0; i <= coins[0][1]; i++) {
            dp[0][i * coins[0][0]] = true;
        }

        for (int i = 1; i < N; i++) {
            int[] coin = coins[i];

            for (int prev = 0; prev <= MAX_COIN; prev++) {
                if (!dp[(i - 1) & 1][prev]) continue;
                for (int c = 0; c <= coin[1]; c++) {
                    dp[i & 1][prev + coin[0] * c] = true;
                }
            }
        }

        int max = 0;

        for (int p = MAX_COIN; p >= 0; p--) {
            if (dp[(N - 1) & 1][p]) {
                max = p;
                break;
            }
        }

        if ((max & 1) == 1) return 0;

        return dp[(N - 1) & 1][max >> 1] ? 1 : 0;
    }
}