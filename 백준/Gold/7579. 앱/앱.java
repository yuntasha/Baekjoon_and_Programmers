import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

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
        var minCost = 10000;
        for (int i=0; i<N; i++){
            for (int j=minCost-cost[i]; j>=0; j--) {
                if (j!=0 && costMaxSize[j]==0) continue;
                costMaxSize[j+cost[i]] = Math.max(costMaxSize[j+cost[i]], costMaxSize[j]+size[i]);
                if (costMaxSize[j+cost[i]]>=M) minCost=j+cost[i];
            }
        }
        return minCost;
    }
}