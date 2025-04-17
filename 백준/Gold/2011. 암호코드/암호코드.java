import java.io.*;
import java.util.*;

public class Main {

    static int MAX = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(solution(bf.readLine()));
    }

    static int solution(String s) {
        int[] dp = new int[s.length()];
        if (s.length() == 0) return 0;
        if (s.charAt(0) != '0') dp[0] = 1;
        else dp[0] = -1;
        if (s.length() >= 2) {
            int n = Integer.parseInt(s.substring(0, 2));
            if (10 <= n && n <= 26) {
                dp[1] = 1;
            }
            if (s.charAt(1) != '0') {
                dp[1] += Math.max(dp[0], 0);
            }
            if (dp[1] == 0) {
                dp[1] = -1;
            }
        }
//        find(s, s.length() - 1, dp);
//        System.out.println(Arrays.toString(dp));

        return find(s, s.length() - 1, dp);
    }

    static int find(String s, int n, int[] dp) {
        if (dp[n] != 0) {
            return Math.max(dp[n], 0);
        }
        int a = Integer.parseInt(s.substring(n - 1, n + 1));
        if (10 <= a && a <= 26) {
//            System.out.println("a = " + a);
            dp[n] += find(s, n - 2, dp);
            dp[n] %= MAX;
        }

        a = Integer.parseInt(s.substring(n, n + 1));
        if (1 <= a) {
//            System.out.println("b = " + a);
            dp[n] += find(s, n - 1, dp);
            dp[n] %= MAX;
        }

        if (dp[n] == 0) {
            dp[n] = -1;
        }

        return Math.max(dp[n], 0);
    }
}