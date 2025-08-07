/*
그냥 DP아닌가?
2^5 32 * 2만
DP[사람][현재 나온 비트]
 */

import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] people = new int[N][];

        for (int i = 0; i < N; i++) people[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, people));
    }

    public static int solution(int N, int[][] people) {
        int[][] dp = new int[N][1 << 5];

        for (int i = 0; i < 5; i++) {
            dp[0][1 << i] = people[0][i];
        }

        for (int p = 1; p < N; p++) {
            for (int i = 0; i < (1 << 5); i++) {
                dp[p][i] = Math.max(dp[p][i], dp[p - 1][i]);
                for (int j = 0; j < 5; j++) {
                    if ((i & (1 << j)) > 0) continue;
                    dp[p][i | (1 << j)] = Math.max(dp[p][i | (1 << j)], dp[p - 1][i] + people[p][j]);
                }
            }
        }

        return dp[N - 1][(1 << 5) - 1];
    }
}