import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        System.out.println(solution(N));
    }

    public static int solution(int N){
        var dp = new int[50001];
        int now = 1;
        dp[1] = 1;
        for (int i=2; i<234; i++){

            while (now<=N){
                if (now == i*i){
                    dp[now] = 1;
                    now++;
                    break;
                }
                for (int j=1; j<i; j++){
                    var n = dp[now - (j*j)]+1;
                    if (dp[now]==0||dp[now]>n){
                        dp[now] = n;
                    }
                }
                now++;
            }
        }
        return dp[N];
    }
}