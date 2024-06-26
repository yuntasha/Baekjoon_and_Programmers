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

        var arr = new int[N][N];

        for (int i=0; i<M; i++){
            var line = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            arr[line[1]-1][line[0]-1] = arr[line[0]-1][line[1]-1] = line[2];
        }
        
        var nodes = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)-1).toArray();

        System.out.println(solution(N, M, arr, nodes));
    }

    static int solution(int N, int M, int[][] arr, int[] nodes){
        var node1 = dijkstra(N, arr, nodes[0]);
        var node2 = dijkstra(N, arr, nodes[1]);
        if (node1[nodes[1]] == 0 || node1[N-1] == 0 || node2[0] == 0) return -1;
        return Math.min(node1[0] + node1[nodes[1]] + node2[N-1], node2[0] + node1[nodes[1]] + node1[N-1]);
    }

    static int[] dijkstra(int N, int[][] arr, int start){
        var result = new int[N];
        var pq = new PriorityQueue<Distance>(Comparator.comparingInt(a -> a.cost));
        pq.add(new Distance(start, 0));
        while (!pq.isEmpty()) {
            var now = pq.remove();
            if (result[now.dest] != 0) continue;
            result[now.dest] = now.cost;
            for (int i=0; i<N; i++){
                if (i == start || result[i] != 0) continue;
                if (arr[now.dest][i] != 0) {
                    pq.add(new Distance(i, now.cost + arr[now.dest][i]));
                }
            }
        }
        return result;
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