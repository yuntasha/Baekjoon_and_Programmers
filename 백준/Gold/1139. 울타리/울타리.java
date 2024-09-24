import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static double[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        dp = new double[1<<N];

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        System.out.println(solution(N, arr));
    }

    static double solution(int N, int[] arr) {
        return find(N, arr, 0, 0.0);
    }

    static double find(int N, int[] arr, int bit, double now) {
        if (dp[bit]>=now && (bit!=0)) return -1;
        dp[bit]=now;
        var result = now;
        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                for (int k=j+1; k<N; k++) {
                    if ((bit&(1<<i)) + (bit&(1<<j)) + (bit&(1<<k))>0) continue;
                    if (arr[i]+arr[j]<=arr[k]) continue;
                    result = Math.max(result, find(N, arr, bit|1<<i|1<<j|1<<k, now + cal(arr[i], arr[j], arr[k])));
                }
            }
        }
        return result;
    }

    static double cal(int a, int b, int c) {
        var p = (a+b+c)/2.0;

        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
}