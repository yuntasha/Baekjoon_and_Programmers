/*
이거 전에 풀었던 문제 느낌이랑 비슷함
근데 디피를 사용해야할 느낌
dp[현재 개수][마지막 숫자][현재 합] = 개수
그럼 10 220 220
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, M, K));
    }

    public static String solution(int N, int M, int K) {
        int[][][] dp = new int[11][221][221];

        makeDP(0, 1, 0, dp, N, M);

        int[] result = find(dp, N, M, K);

        return Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    public static int[] find(int[][][] dp, int N, int M, int K) {
        int[] result = new int[N];

        int remain = K;
        int last = 1;
        int sum = 0;

        for (int n = 0; n < N - 1; n++) {
            for (int now = last; now < M - sum; now++) {
                if (dp[n + 1][now][sum + now] - 1 < remain) {
                    remain -= dp[n + 1][now][sum + now] - 1;
                } else {
                    result[n] = now;
                    sum += now;
                    last = now;
                    break;
                }
            }
        }

        result[N - 1] = M - sum;

        return result;
    }

    public static int makeDP(int n, int last, int sum, int[][][] dp, int N, int M) {
        if (dp[n][last][sum] > 0) return dp[n][last][sum] - 1;

        if (n == N) {
            if (sum == M) return (dp[n][last][sum] = 2) - 1;
            else return (dp[n][last][sum] = 1) - 1;
        }

        int result = 1;

        for (int i = last; i <= M - sum; i++) {
            result += makeDP(n + 1, i, sum + i, dp, N, M);
        }

        dp[n][last][sum] = result;

        return dp[n][last][sum] - 1;
    }
}