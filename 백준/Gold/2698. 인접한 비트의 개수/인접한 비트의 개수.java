/*
정석으로 가자
n개의 비트, k개
dp[n][k] += dp[n - 1][k]; 0만 넣는 경우
dp[n][k] += dp[n - 2][k]; 10 넣는 경우
dp[n][k] += dp[n - a][k - a + 2]; 1여러개 넣는 경우 -> 1 4개 연속으로 그럼 마지막은 0으로 넣어줘야 깔끔하니 넣으면 3개짜리가 됨 -> 5개 넣으면 3개 추가된다는 소리
110 이런씩이 아니라 011로 생각하는게 맞겠다

마지막이 1인지 0인지, 해당 0, 1 위치, 현재 개수
 */

import java.io.*;
import java.util.*;

public class Main {

    // 현재 위치, 현재 개수, 마지막 비트
    static int[][][] dp = new int[101][101][2];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        solution();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int n = Integer.parseInt(input.nextToken());
            int m = Integer.parseInt(input.nextToken());

            result.append(dp[n - 1][m][0] + dp[n - 1][m][1]).append("\n");
        }

        System.out.print(result);
    }

    static void solution() {
        dp[0][0][0] = dp[0][0][1] = 1;

        for (int i = 1; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                dp[i][j][0] += dp[i - 1][j][0] + dp[i - 1][j][1];
                dp[i][j][1] += dp[i - 1][j][0];
                if (j > 0) {
                    dp[i][j][1] += dp[i - 1][j - 1][1];
                }
            }
        }
    }
}