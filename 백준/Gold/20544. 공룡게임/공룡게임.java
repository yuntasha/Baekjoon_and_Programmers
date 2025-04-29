/*
처음은 반드시 비어있어야함
2가 반드시 한번은 포함되있어야함
1, 2만
21, 12, 11

1,000,000,007

1만을 선택해 구성하는 경우
이건 귀찮은데

1, 11로 구성됨

DP 2개 하자
한번하고 한칸을 띄워야하니까
+2, +3으로 처리하자
+2는 1, 2 각각으로 2배
+3은 3배

정확히는 012 01 이런 느낌으로 들어가도록

아무것도 안넣는 경우도 있겠지?
그러니까 +1도 하나 그냥 해주자
+1 1배
+2 2배
+3 3배

+1 1배
+2 1배
+3 1배

아 %= 때문에 오차 생기겠구나...
 */

import java.io.*;
import java.util.*;

public class Main {

    static long MAX = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    static long solution(int N) {
        long[] dp = new long[N + 1];
        long[] dp1 = new long[N + 1];

        dp[0] = dp1[0] = 1;

        for (int i = 0; i < N; i++) {
            if (i + 1 <= N) {
                // 0
                dp[i + 1] += dp[i];
                dp[i + 1] %= MAX;

                // 0
                dp1[i + 1] += dp1[i];
                dp1[i + 1] %= MAX;
            }

            if (i + 2 <= N) {
                // 0 1, 0 2
                dp[i + 2] += dp[i] * 2;
                dp[i + 2] %= MAX;

                // 0 1
                dp1[i + 2] += dp1[i];
                dp1[i + 2] %= MAX;
            }

            if (i + 3 <= N) {
                // 0 1 2, 0 1 1, 0 2 1
                dp[i + 3] += dp[i] * 3;
                dp[i + 3] %= MAX;

                // 0 1 1
                dp1[i + 3] += dp1[i];
                dp1[i + 3] %= MAX;
            }
        }

//        System.out.println(Arrays.toString(dp));
//        System.out.println(Arrays.toString(dp1));

        return dp[N] < dp1[N] ? dp[N] - dp1[N] + MAX : dp[N] - dp1[N];
    }
}