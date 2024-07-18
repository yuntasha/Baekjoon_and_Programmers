import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][][] dp = new int[101][11][1024];
    static final int MAX_VALUE = 1_000_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        System.out.println(solution(N));
    }

    static int solution(int N){
        var result = 0;
        var exist = 0;
        for (int i=1; i<10; i++){
            result += dfs(N, i, exist);
            result %= MAX_VALUE;
        }
        return result;
    }

    static int dfs(int N, int now, int exist){
        exist = exist | (1<<now);
        if (N==1) {
            return exist==1023?1:0;
        }
        if (dp[N][now][exist] != 0) return dp[N][now][exist];
        var result = 0;
        if (0<now) {
            dp[N-1][now-1][exist] = dfs(N - 1, now - 1, exist);
            result += dp[N-1][now-1][exist];
            result %= MAX_VALUE;
        }
        if (now<9) {
            dp[N-1][now+1][exist] = dfs(N - 1, now + 1, exist);
            result += dp[N-1][now+1][exist];
            result %= MAX_VALUE;
        }
        return result;
    }
}