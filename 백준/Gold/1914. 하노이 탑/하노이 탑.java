/*
그러니까
4 높이를 만들려면
1 -> 3이라면
3 높이를 나머지인 2에 만들어야함
1 -> 2만들어야하고
2 높이를 나머지인 3에 만들어야함
1 -> 3
1의 높이를 나머지인

dp[높이][시작][출발] = dp[높이 - 1][시작][다른 거] + "시작 도착" + dp[높이 - 1][다른 거][도착]
*/

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = read();

        System.out.println(solution(N));
    }

    public static String solution(int N) {
        if (N > 20) {
            BigInteger result = new BigInteger("1");
            BigInteger one = new BigInteger("1");
            BigInteger two = new BigInteger("2");

            for (int i = 1; i < N; i++) {
                result = result.multiply(two).add(one);
            }

            return result.toString(10);
        }

        String[][][] dp = new String[N][3][3];
        int[] cdp = new int[N];
        cdp[0] = 1;

        for (int h = 1; h <= N; h++) {
            if (h != 1) cdp[h - 1] = (cdp[h - 2] << 1) + 1;
            for (int s = 0; s < 3; s++) {
                for (int e = 0; e < 3; e++) {
                    if (s == e) continue;
                    if (h == 1) {
                        dp[h - 1][s][e] = (s + 1) + " " + (e + 1);
                        continue;
                    }

                    int r = 3 - s - e;
                    StringBuilder now = new StringBuilder();
                    now.append(dp[h - 2][s][r]).append("\n").append(dp[0][s][e]).append("\n").append(dp[h - 2][r][e]);
                    dp[h - 1][s][e] = now.toString();
                }
            }
        }

        return cdp[N - 1] + "\n" + dp[N - 1][0][2];
    }

    static int read() throws IOException {
        int n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}