/*
가장 느린 사람
350 * 10만 ㄱㄱ

가장 큰거 골라야함
이전과 비교했을때 큰 것
 */



import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int H = Integer.parseInt(input.nextToken());
        int N = Integer.parseInt(input.nextToken());

        int[][] people = new int[N][2];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());

            people[i][0] = Integer.parseInt(input.nextToken()); // 키
            people[i][1] = Integer.parseInt(input.nextToken()); // 속도
        }


        System.out.println(solution(N, H, people));
    }

    public static int solution(int N, int H, int[][] people) {
        int[][] dp = new int[N][H + 1];
        dp[0][0] = Integer.MAX_VALUE;
        dp[0][people[0][0]] = people[0][1];

        for (int i = 1; i < N; i++) {
            for (int h = 0; h <= H; h++) {
                if (dp[i - 1][h] > 0) dp[i][h] = dp[i - 1][h];
                if (h >= people[i][0] && dp[i - 1][h - people[i][0]] > 0) {
                    int now = Math.min(people[i][1], dp[i - 1][h - people[i][0]]);
                    dp[i][h] = Math.max(dp[i][h], now);
                }
            }
        }

        return dp[N - 1][H];
    }
}