import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

       var N = Integer.parseInt(bf.readLine());

       var arr = new int[N][];

       for (int i=0; i<N; i++) {
           arr[i] = Arrays.stream(bf.readLine().split("")).mapToInt(s -> s.equals("H")?0:1).toArray();
       }

        System.out.println(solution(N, arr));
    }


    static int solution(int N, int[][] arr) {
        return dfs(N, arr, 0);
    }

    static int dfs(int N, int[][] arr, int now) {
        if (N==now) return cal(N, arr);
        var result = Integer.MAX_VALUE;
        result = Math.min(result, dfs(N, arr, now+1));
        rH(N, arr, now);
        result = Math.min(result, dfs(N, arr, now+1));
        rH(N, arr, now);
        return result;
    }

    static int cal(int N, int[][] arr) {
        var result = 0;
        for (int i=0; i<N; i++) {
            var now = sum(N, arr[i]);
            now = Math.min(now, N-now);
            result += now;
        }
        return result;
    }

    static int sum(int N, int[] arr) {
        var result = 0;
        for (int i=0; i<N; i++) result+=arr[i];
        return result;
    }

    static void rH(int N, int[][] arr, int n) {
        for (int i=0; i<N; i++) {
            arr[i][n]+=1;
            arr[i][n]%=2;
        }
    }
}