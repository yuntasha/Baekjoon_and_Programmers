import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        for (int i=0; i<N; i++){
            var M = Integer.parseInt(bufferedReader.readLine());
            var array = new int[M][2];


            var array1 = Arrays.stream(bufferedReader.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<M; j++){
                array[j][0] = array1[j];
            }

            var array2 = Arrays.stream(bufferedReader.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<M; j++){
                array[j][1] = array2[j];
            }

            System.out.println(solution(M, array));
        }
    }

    public static int solution(int N, int[][] array){
        int[][] dp = new int[N][2];

        dp[0][0] = array[0][0];
        dp[0][1] = array[0][1];
        if (N>1) {
            dp[1][0] = array[1][0] + array[0][1];
            dp[1][1] = array[1][1] + array[0][0];
        }

        for (int i=2; i<N; i++){
            dp[i][0] = maxThree(dp[i-2][0], dp[i-2][1], dp[i-1][1]) + array[i][0];
            dp[i][1] = maxThree(dp[i-2][0], dp[i-2][1], dp[i-1][0]) + array[i][1];
        }

        return Math.max(dp[N-1][0], dp[N-1][1]);
    }

    private static int maxThree(int a, int b, int c){
        return Math.max(a, Math.max(b, c));
    }
}