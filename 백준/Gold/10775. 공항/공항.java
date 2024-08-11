import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main {

    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var G = Integer.parseInt(bufferedReader.readLine());
        var P = Integer.parseInt(bufferedReader.readLine());

        var g = new int[P];

        for (int i=0; i<P; i++){
            g[i] = Integer.parseInt(bufferedReader.readLine())-1;
        }

        System.out.println(solution(G, P, g));
    }

    static int solution(int G, int P, int[] g){
        dp = IntStream.range(0, G).toArray();
        var result = 0;
        for (int i: g){
            var now = findGate(i);
            if (now==-1) break;
            dp[now] = now-1;
            result++;
        }
        return result;
    }

    static int findGate(int n) {
        if (dp[n]==n || dp[n]==-1) return dp[n];
        dp[n] = findGate(dp[n]);
        return dp[n];
    }
}