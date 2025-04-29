/*
자두는 초기에 1번에 위치
최대 W번만큼 움직여서 가장 많은 자두를 받고 싶음
그냥 n번 움직여서 얻는 최대 값을 저장하면 될 듯
0번 나무하고 1번 나무로 바꿔 1 빼서 받자
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int W = Integer.parseInt(input.nextToken());

        int[] falls = new int[N];

        for (int i = 0; i < N; i++) falls[i] = Integer.parseInt(bf.readLine()) - 1;

        System.out.println(solution(N, W, falls));
    }

    static int solution(int N, int W, int[] falls) {
        int[] dp = new int[W + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= W; j++) {
                if (j == 0) {
                    if (falls[i] == 0) dp[0]++;
                    continue;
                }

                dp[j] = Math.max(dp[j], dp[j - 1]);

                if ((falls[i] & 1) == (j & 1)) { // 현 위치에서 떨어짐
                    dp[j]++;
                }
            }
        }

        int result = 0;

        for (int i = 0; i <= W; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}