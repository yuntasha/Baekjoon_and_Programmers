import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NMX = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        final var N = NMX[0];
        final var M = NMX[1];
        final var X = NMX[2];

        var arr = new int[N][N];

        for (int i=0; i<M; i++){
            var line = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            arr[line[0]-1][line[1]-1] = line[2];
        }

        System.out.println(solution(N, M, X, arr));
    }

    static int solution(int N, int M, int X, int[][] arr){
//        System.out.println(Arrays.stream(arr).map(a -> Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.joining(" "))).collect(Collectors.joining("\n","------------\n", "\n-------------" )));
        for (int i=0; i<N; i++){ // 거쳐갈 노드
            for (int j=0; j<N; j++){ // 출발할 노드
                if (i==j) continue;
                for (int k=0; k<N; k++) { // 도착할 노드
                    if (arr[j][i] == 0 || arr[i][k]==0 || j==k) continue;
//                    System.out.println(j + " " + i + " " + k);
                    if (arr[j][k] == 0) arr[j][k] = arr[j][i] + arr[i][k] ;
                    else arr[j][k] = Math.min(arr[j][i] + arr[i][k], arr[j][k]);
//                    System.out.println(Arrays.stream(arr).map(a -> Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.joining(" "))).collect(Collectors.joining("\n","------------\n", "\n-------------" )));
                }
            }
        }
        var max = 0;
        for (int i=0; i<N; i++){
            if (i==X-1) continue;
            max = Math.max(max, arr[X-1][i] + arr[i][X-1]);
        }
//        System.out.println(Arrays.stream(arr).map(a -> Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.joining(" "))).collect(Collectors.joining("\n","------------\n", "\n-------------" )));
        return max;
    }

    static class Distance{
        int dest;
        int cost;

        Distance(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }
    }
}