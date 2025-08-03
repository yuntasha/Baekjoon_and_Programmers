/*
10개 * 최대 값 1000
10_000 인데 그냥 10만으로 바꿉시다
 */

import java.io.*;
import java.util.*;

public class Main {

    static int MAX = 100_000;

    public static void main(String[] args) throws IOException {

        int N = read();

        int[] prices = new int[N];

        for (int i = 0; i < N; i++) prices[i] = read();

        int[][] sales = new int[N][N];

        for (int i = 0; i < N; i++) {
            int M = read();

            for (int j = 0; j < M; j++) {
                sales[i][read() - 1] += read();
            }
        }

        System.out.println(solution(N, prices, sales));
    }

    public static int solution(int N, int[] prices, int[][] sales) {
        int[] dp = new int[(1 << N)];

        Arrays.fill(dp, MAX);

        dp[0] = 0;

        for (int i = 0; i < (1 << N); i++) {
            for (int next = 0; next < N; next++) {
                if ((i & (1 << next)) > 0) continue;
                int now = prices[next];

                for (int s = 0; s < N; s++) {
                    if ((i & (1 << s)) == 0) continue;
                    now -= sales[s][next];
                }

                dp[i | (1 << next)] = Math.min(dp[i | (1 << next)], dp[i] + Math.max(now, 1));
            }
        }

        return dp[(1 << N) - 1];
    }

    public static int read() throws IOException {
        int n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}