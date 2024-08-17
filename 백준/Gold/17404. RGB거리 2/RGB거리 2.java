import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var costs = new int[N][3];

        for (int i=0; i<N; i++) {
            costs[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }


        System.out.println(solution(N, costs));
    }

    static int solution(int N, int[][] costs){
        var dp = new int[N][3][3];

        for (int i=0; i<3; i++) {
            dp[0][i][i] = costs[0][i];
        }
        for (int n=0; n<N-1; n++){
            for (int now=0; now<3; now++){
                for (int first=0; first<3; first++) {
                    if (dp[n][now][first]==0) continue;
                    for (int next=0; next<3; next++){
                        if (now==next) continue;
                        if (dp[n+1][next][first]==0) {
                            dp[n+1][next][first] = dp[n][now][first] + costs[n+1][next];
                        } else {
                            dp[n+1][next][first] = Math.min(dp[n+1][next][first], dp[n][now][first] + costs[n+1][next]);
                        }
                    }
                }
            }
        }
        var result=Integer.MAX_VALUE;
        for (int first=0; first<3; first++){
            for (int last=0; last<3; last++){
                if (first==last || dp[N-1][last][first]==0) continue;
                result = Math.min(result, dp[N-1][last][first]);
            }
        }
        return result;
    }
}