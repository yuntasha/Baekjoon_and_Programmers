import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var arr1 = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var arr2 = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr1, arr2));
    }

    static int solution(int N, int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        var dp = new int[N][N];
        Arrays.fill(dp[0], getResult(arr1[0], arr2[0]));

        for (int i=1; i<N; i++) {
            dp[i][0] = getResult(arr1[i], arr2[0]);
        }

        for (int i=1; i<N; i++) {
            for (int j=1; j<N; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1] + getResult(arr1[i], arr2[j]), Math.max(dp[i-1][j], dp[i][j-1]));
            }
        }

        return dp[N-1][N-1];
    }

    static int getResult(int a, int b) {
        if (a>b) return 2;
        if (a==b) return 1;
        return 0;
    }
}