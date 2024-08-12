import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());
        var arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var T = Integer.parseInt(bufferedReader.readLine());

        var se = new int[T][2];

        for (int i=0; i<T; i++) se[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(s-> Integer.parseInt(s)-1).toArray();
        System.out.println(solution(N, arr, T, se));
    }

    static String solution(int N, int[] arr, int T, int[][] se){
        var sb = new StringBuilder();
        fillDp(N, arr);
        for (int[] s: se){
            sb.append(dp[s[0]][s[1]]);
            sb.append("\n");
        }
        return sb.toString();
    }

    static void fillDp(int N, int[] arr){
        dp = new int[N][N];
        for (int i=0; i<N; i++){
            var s = i;
            var e = i;
            while (s>=0 && e<N){
                if (arr[s]!=arr[e]) break;
                dp[s--][e++] = 1;
            }
            s = i;
            e = i+1;
            while (s>=0 && e<N){
                if (arr[s]!=arr[e]) break;
                dp[s--][e++] = 1;
            }
        }
    }
}