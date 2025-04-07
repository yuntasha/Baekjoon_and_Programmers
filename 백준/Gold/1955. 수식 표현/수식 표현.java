import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
곱, 더하기, 팩토리얼 3개로만 숫자를 만들 수 있음
1의 개수니까 DP로 접근하면 가능할듯?
더하기 = 이전 것들 전부 0 ~ N까지 최소 합 구하면 된다. 1만
곱하기 = 투 포인터로 구하면 된다. 1만
팩토리얼 = 미리 넣어두자. 이건 상수 (금방 함)
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.print(solution(N));
    }

    static int solution(int N) {
        int[] dp = new int[N + 1];
        int[] facto = new int[8];

        facto[1] = 1;
        for (int i = 2; i < 8; i++) {
            facto[i] = facto[i - 1] * i;
        }

        dp[1] = 1;

        int fIdx = 2;

        for (int i = 2; i <= N; i++) {
            dp[i] = i;

            for (int n = 1; n <= (i >> 1); n++) {
                dp[i] = Math.min(dp[i], dp[n] + dp[i - n]);
            }

            int s = 1;
            int e = i - 1;

            while (s <= e) {
                if (s * e == i) {
                    dp[i] = Math.min(dp[i], dp[s] + dp[e]);
                    s++;
                    e--;
                } else if (s * e < i) {
                    s++;
                } else {
                    e--;
                }
            }

            if (fIdx < 8 && facto[fIdx] == i) {
                dp[i] = Math.min(dp[i], dp[fIdx++]);
            }
        }

        return dp[N];
    }
}