/*
바텀업 DP로 하자
동전 작은거부 그냥 앞에서부터 여러개 사용해도 괜찮으니까
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        List<Integer> coins = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            coins.add(Integer.parseInt(bf.readLine()));
        }

        System.out.println(solution(N, K, coins));
    }

    public static int solution(int N, int K, List<Integer> coins) {
        int[] dp = new int[K + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= K; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[K];
    }
}