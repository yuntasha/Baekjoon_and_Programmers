import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    static int solution(int N) {
        var dp = new int[N+1];

        if (N==1) return 1;
        if (N==2) return 2;

        dp[1] = 1;
        dp[2] = 2;

        for (int i=3; i<N+1; i++) {
            dp[i] = (dp[i-1] + dp[i-2])%10007;
        }

        return dp[N];
    }
}