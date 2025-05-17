import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
1 ~ T까지의 수
A개가 있음
1 <= S <= K <= B <= A
순서 상관이 없음
숫자 n이 몇개 들어가냐의 문제

1 <= T <= 200
1 <= A <= 4000
1 <= S <= B <= A

전체 순회를 돌아서 각각 몇개의 숫자가 들어갔는지 확인
각 숫자별로 몇개 넣을 수 있는지 체크
3 4 5 6 7

dp[숫자][개수]
이렇게 구할 수 있을듯
토글링도 가능할 것 같지만 굳이..?
 */

public class Main {

    static int MOD = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int T = Integer.parseInt(input.nextToken());
        int A = Integer.parseInt(input.nextToken());
        int S = Integer.parseInt(input.nextToken());
        int B = Integer.parseInt(input.nextToken());

        int[] count = new int[T + 1];

        input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < A; i++) {
            count[Integer.parseInt(input.nextToken())]++;
        }

        System.out.println(solution(T, A, S, B, count));
    }

    static int solution(int T, int A, int S, int B, int[] count) {
        int[][] dp = new int[T + 1][B + 1];
        dp[0][0] = 1;

        for (int n = 1; n <= T; n++) {
            for (int i = 0; i <= B; i++) {
                dp[n][i] = dp[n - 1][i];
            }

            for (int i = 1; i <= count[n]; i++) {
                for (int before = 0; before <= B - i; before++) {
                    dp[n][before + i] += dp[n - 1][before];
                    dp[n][before + i] %= MOD;
                }
            }
        }

//        for (int[] d : dp) System.out.println(Arrays.toString(d));

        int result = 0;

        for (int i = S; i <= B; i++) {
            result += dp[T][i];
            result %= MOD;
        }

        return result;
    }
}