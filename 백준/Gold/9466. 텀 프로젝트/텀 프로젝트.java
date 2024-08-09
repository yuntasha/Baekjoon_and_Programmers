import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var T = Integer.parseInt(bufferedReader.readLine());

        for (int i=0; i<T; i++) {
            var N = Integer.parseInt(bufferedReader.readLine());
            var arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)-1).toArray();
            System.out.println(solution(N, arr));
        }
    }

    static int solution(int N, int[] arr){
        visited = new int[N];
        for (int i=0; i<N; i++){
            dfs(N, arr, i);
        }
        var cnt=0;
        for (int i=0; i<N; i++) {
            if (arr[i]!=i) cnt++;
        }
        return cnt;
    }

    static int dfs(int N, int[] arr, int n){
        if (visited[n]==2) {
            visited[n] = 2;
            return -1;
        } else if (visited[n]==1) {
            visited[n] = 2;
            return n;
        }
        visited[n] = 1;
        if (arr[n] == n) {
            visited[n] = 2;
            return -1;
        }
        var r = dfs(N, arr, arr[n]);
        if (r==n) {
            arr[n] = n;
            visited[n] = 2;
            return -1;
        } else if (r>=0) {
            arr[n] = n;
            visited[n] = 2;
            return r;
        } else {
            visited[n] = 2;
            return -1;
        }
    }
}