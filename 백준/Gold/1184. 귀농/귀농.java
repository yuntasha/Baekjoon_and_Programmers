import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var arr = new int[N][N];

        for (int i=0; i<N; i++) {
            arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, arr));
    }



    static long solution(int N, int[][] arr) {
        dp = new int[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (i==0 && j==0) dp[i][j] = arr[i][j];
                else if (i==0) dp[i][j] = dp[i][j-1] + arr[i][j];
                else if (j==0) dp[i][j] = dp[i-1][j] + arr[i][j];
                else dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + arr[i][j];
            }
        }

        var result = 0;

        for (int i=0; i<N-1; i++) {
            for (int j=0; j<N-1; j++) {
                var lu = find(N, i, j, -1, -1);
                var rd = find(N, i+1, j+1, 1, 1);
                var ld = find (N, i+1, j, 1, -1);
                var ru = find(N, i, j+1, -1, 1);
                for (int k: lu.keySet()) {
                    if (rd.containsKey(k)) result += lu.get(k)*rd.get(k);
                }
                for (int k: ld.keySet()) {
                    if (ru.containsKey(k)) result += ld.get(k)*ru.get(k);
                }
            }
        }

        return result;
    }

    static TreeMap<Integer, Integer> find(int N, int x, int y, int dx, int dy) {
        var tm = new TreeMap<Integer, Integer>();
        for (int i=x; i<N && i>=0; i+=dx) {
            for (int j=y; j<N && j>=0; j+=dy) {
                var now = sum(Math.min(i, x), Math.min(j, y), Math.max(i, x), Math.max(j, y));
                tm.put(now, tm.getOrDefault(now, 0)+1);
            }
        }
        return tm;
    }

    static int sum(int x1, int y1, int x2, int y2) {
        var result = 0;
        result +=  dp[x2][y2];
        if (x1>0) result -= dp[x1-1][y2];
        if (y1>0) result -= dp[x2][y1-1];
        if (x1>0 && y1>0) result += dp[x1-1][y1-1];
        return result;
    }
}