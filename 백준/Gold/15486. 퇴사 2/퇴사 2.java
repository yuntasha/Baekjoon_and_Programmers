import java.io.*;
import java.util.*;

public class Main {

    static String WIN = "A";
    static String SAME = "D";
    static String LOSE = "B";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] pay = new int[N];
        int[] time = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            time[i] = Integer.parseInt(input.nextToken());
            pay[i] = Integer.parseInt(input.nextToken());
        }

        System.out.println(solution(N, pay, time));
    }

    static int solution(int N, int[] pay, int[] time) {
        int[] dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            if (i + time[i] > N) continue;
            dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + pay[i]);
        }

        return dp[N];
    }
}