import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int MAX_VALUE = 10_000_001;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];


        var size = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var cost = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, M, size, cost));
    }

    static int solution(int N, int M, int[] size, int[] cost){
        var arr = new int[M];
        var visited = new boolean[M];
        var result = Integer.MAX_VALUE;
        for (int i=0; i<N; i++){
            for (int j=M-1; j>0; j--){
                if (!visited[j]) continue;
                if (j+size[i]>=M) {
                    result = Math.min(arr[j]+cost[i], result);
                } else {
                    arr[j+size[i]] = visited[j+size[i]] && arr[j+size[i]]<arr[j]+cost[i]?arr[j+size[i]]:arr[j]+cost[i];
                    visited[j+size[i]] = true;
                }
            }
            if (size[i]>=M) result = Math.min(result, cost[i]);
            else {
                arr[size[i]] = visited[size[i]] && arr[size[i]] < cost[i] ? arr[size[i]] : cost[i];
                visited[size[i]] = true;
            }
        }

        return result;
    }
}