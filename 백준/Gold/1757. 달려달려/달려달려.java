import java.io.*;
import java.util.*;

/*
DP문제
끝날 때 0이여야하고
한번 달리면 0까지 못쉼
1개부터 남은거의 절반까지 하고 거기 + 이전거 그대로 업데이트
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        System.out.println(solution(N, M, arr));
    }

    static int solution(int N, int M, int[] arr) {
        int[] dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            int now = 0;
            for (int j = 1; j <= M; j++) {
                if (i + 2 * j > N) break;
                now += arr[i + j - 1];
                dp[i + 2 * j] = Math.max(dp[i + 2 * j], dp[i] + now);
            }
        }

        return dp[N];
    }
}