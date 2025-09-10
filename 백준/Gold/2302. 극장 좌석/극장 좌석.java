/*
2명씩 스왑하는게 전부인 느낌
현재 위치가 고정석이라면 그냥 다음꺼로 넘겨주고
dp[n] = dp[n - 1] + dp[n - 2]
이때 n - 1 또는 n이 vip면 그냥 이전꺼만
이게 맞나
1
12
21
132
123
213
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int M = Integer.parseInt(bf.readLine());

        boolean[] isVIP = new boolean[N];

        for (int i = 0; i < M; i++) isVIP[Integer.parseInt(bf.readLine()) - 1] = true;

        System.out.println(solution(N, M, isVIP));
    }

    public static int solution(int N, int M, boolean[] isVIP) {
        if (N == 1) return 1;

        int[] dp = new int[N];

        dp[0] = 1;
        dp[1] = 1;
        if (!isVIP[0] && !isVIP[1]) dp[1] = 2;

        for (int i = 2; i < N; i++) {
            dp[i] = dp[i - 1];
            if (!isVIP[i - 1] && !isVIP[i]) dp[i] += dp[i - 2];
        }

        return dp[N - 1];
    }
}