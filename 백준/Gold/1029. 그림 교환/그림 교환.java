import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] dp = new int[15][1024*32];

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var arr = new int[N][N];

        for (int i=0; i<N; i++){
            arr[i] = Arrays.stream(bufferedReader.readLine().strip().split("")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[][] arr){
        dfs(N, arr, 0, 0, 0);
        return dp[0][1]+1;
    }

    static int dfs(int N, int[][] arr, int now, int cost, int visited) {
        visited = visited | (1<<now);
        if (dp[now][visited]>0) return dp[now][visited];

        for (int i=0; i<N; i++) {
            if ((visited&(1<<i))>0 || arr[now][i]<cost) continue;
            dp[now][visited] = Math.max(dp[now][visited], dfs(N, arr, i, arr[now][i], visited)+1);
        }
        return dp[now][visited];
    }
}