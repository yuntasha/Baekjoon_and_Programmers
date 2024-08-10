import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        var N = NM[0];
        var M = NM[1];

        System.out.println(solution(N, M));
    }

    static long solution(long N, long M){
        var mSize = (int) (Math.log(M)/Math.log(2));
        var dp = new long[mSize+1];
        dp[0] = 1L;
        for (int i=1; i<mSize+1; i++){
            dp[i] = (dp[i-1]<<1)+ (1L<<i);
        }
        return cnt1(M, dp) - cnt1(N-1, dp);
    }

    static long cnt1(long N, long[] dp) {
        long count = N & 1;
        int size = (int) (Math.log(N)/Math.log(2));

        for(int i=size;i>0;i--) {
            if((N & (1L<<i)) != 0L) {
                count += dp[i-1] +(N - (1L<<i) + 1);
                N -= (1L << i);
            }
        }
        return count;
    }
}