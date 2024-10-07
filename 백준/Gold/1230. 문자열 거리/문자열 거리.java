import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][][] dp = new int[1001][1001][2];
    static int INF = 99999;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var O = bf.readLine().toCharArray();
        var N = bf.readLine().toCharArray();

        System.out.println(solution(O, N));
    }

    static int solution(char[] O, char[] N) {
        if (O.length>N.length) return -1;
        dp[0][0][0] = INF;

        for (int i=1; i<N.length; i++) {
            dp[0][i][1] = INF;
            dp[0][i][0] = 1;
        }

        for (int i=0; i<O.length; i++) {
            for (int j=0; j<N.length; j++) {
                dp[i+1][j][0] = dp[i+1][j][1] = INF;
            }
            for (int j=i; j<N.length; j++) {
                if (O[i]==N[j]) {
                    dp[i+1][j+1][1] = Math.min(dp[i][j][1], dp[i][j][0]);
                } else {
                    dp[i+1][j+1][1] = INF;
                }
                dp[i+1][j+1][0] = Math.min(dp[i+1][j][0], dp[i+1][j][1] + 1);
            }
        }


        var result = Math.min(dp[O.length][N.length][0], dp[O.length][N.length][1]);
        if (result>=INF) return -1;
        else return result;
    }
}