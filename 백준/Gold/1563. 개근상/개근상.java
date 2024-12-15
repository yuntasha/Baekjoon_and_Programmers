import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int MAX_VALUE = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    static int solution(int N) {
        int[][][] dp = new int[N][3][2];

        dp[0][0][0] = 1;
        dp[0][1][0] = 1;
        dp[0][0][1] = 1;

        for (int n=1; n<N; n++) {
            dp[n][0][0] = (dp[n-1][0][0] + dp[n-1][1][0] + dp[n-1][2][0]) % MAX_VALUE;
            dp[n][1][0] = dp[n-1][0][0];
            dp[n][2][0] = dp[n-1][1][0];
            dp[n][0][1] = (dp[n-1][0][1] + dp[n-1][1][1] + dp[n-1][2][1] + dp[n][0][0]) % MAX_VALUE;
            dp[n][1][1] = dp[n-1][0][1];
            dp[n][2][1] = dp[n-1][1][1];
        }

        int result = 0;

        for (int i=0; i<3; i++) {
            for (int j=0; j<2; j++) {
                result = result + dp[N-1][i][j];
            }
        }

        return result % MAX_VALUE;
    }
}