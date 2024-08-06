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
        var costMaxSize = new int[10001];
        var visited = new boolean[10001];
        visited[0] = true;
        for (int i=0; i<N; i++){
            for (int j=10000; j>=0; j--) {
                if (!visited[j]) continue;
                costMaxSize[j+cost[i]] = Math.max(costMaxSize[j+cost[i]], costMaxSize[j]+size[i]);
                visited[j+cost[i]] = true;
            }
        }
        for (int i=0; i<10001; i++){
            if (costMaxSize[i]>=M) {
                return i;
            }
        }
        return 10000;
    }
}