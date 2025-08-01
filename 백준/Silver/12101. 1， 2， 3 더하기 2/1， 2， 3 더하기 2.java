/*
1, 2, 3으로 해당 값을 넣는 것
흠
dp로 해당 값을 만들 수 있는 가짓수를 미리 구하고 백트래킹으로 구해도 괜찮을 느낌인데?
1 + 1 + 2 이런것도 봐주잖아
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, K));
    }

    public static String solution(int N, int K) {
        int[] dp = new int[Math.max(3, N + 1)];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
        }

        if (dp[N] < K) return "-1";

        List<Integer> arr = new ArrayList<>();

        find(N, K, dp, arr);

        return arr.stream().map(String::valueOf).collect(Collectors.joining("+"));
    }

    public static void find(int n, int k, int[] dp, List<Integer> arr) {
        if (n == 0) return;

        for (int i = 1; i <= Math.min(3, n); i++) {
            if (dp[n - i] >= k) {
                arr.add(i);
                find(n - i, k, dp, arr);
                return;
            }
            k -= dp[n - i];
        }
    }
}