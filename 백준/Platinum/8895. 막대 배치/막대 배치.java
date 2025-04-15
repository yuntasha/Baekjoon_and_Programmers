/*
가장 작은 것을 두는 위치
왼쪽 왼쪽에 +1을 할 수 있다
오른쪽 오른쪽에 +1을 할 수 있다
그 중간 양쪽 변화가 없다
현재가 3인 경우 2개중에서 1개에 들어갈 수 있다 즉 N - 2

3 1 2를 구하려면
2 1 1 => 0
2 0 2
2 1 2
중 하나를 찾아

 */

import java.io.*;
import java.util.*;

public class Main {

    static long[][][] dp = new long[21][21][21];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        StringBuilder output = new StringBuilder();

        dp[1][1][1] = 1;

        for (int i = 0; i < T; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            output.append("\n").append(solution(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken())));
        }

        System.out.println(output.substring(1));
    }

    static long solution(int n, int l, int r) {
        if (l > n || r > n || l <= 0 || r <= 0) { // 실패예시
            return 0;
        }
        if (dp[n][l][r] == 0) {
            dp[n][l][r] = solution(n - 1, l - 1, r) + solution(n - 1, l, r - 1) + solution(n - 1, l, r) * (n - 2);
            if (dp[n][l][r] == 0) {
                dp[n][l][r] = -1;
            }
        }


        return Math.max(dp[n][l][r], 0);
    }
}