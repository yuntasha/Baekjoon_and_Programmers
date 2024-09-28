import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        var NK = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NK[0];
        var K = NK[1];

        var arr = new int[N];

        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        System.out.println(solution(N, K, arr));
    }



    static long solution(int N, int K, int[] arr) {
        var dp = new long[1<<N][N];
        for (int i=0; i<N; i++) dp[1<<i][i] = 1;
        var result = 0L;
        for (int i=0; i<N; i++) {
            result += find(N, K, arr, dp, i, (1<<(N))-1);
        }
        return result;
    }

    static long find(int N, int K, int[] arr, long[][] dp, int last, int nowBit) {
        if (nowBit!=0 && dp[nowBit][last]!=0) return dp[nowBit][last];
        for (int i=0; i<N; i++) {
            if ((nowBit & (1<<i))==0) continue;
            if (Math.abs(arr[last]-arr[i])<=K) continue;
            dp[nowBit][last] += find(N, K, arr, dp, i, nowBit^(1<<last));
        }
        return dp[nowBit][last];
    }
}