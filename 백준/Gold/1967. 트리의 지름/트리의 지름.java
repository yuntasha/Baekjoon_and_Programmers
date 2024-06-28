import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MAX_VALUE = 1_000_001;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        var N = Integer.parseInt(bufferedReader.readLine());

        var arr = new ArrayList<ArrayList<Road>>();

        for (int i=0; i<N; i++) arr.add(new ArrayList<>());

        for (int i=0; i<N-1; i++){
            var line = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(num -> Integer.parseInt(num)-1).toArray();
            arr.get(line[0]).add(new Road(line[1], line[2]+1));
            arr.get(line[1]).add(new Road(line[0], line[2]+1));
        }

        System.out.println(solution(N, arr));
    }

    static int solution(int N, ArrayList<ArrayList<Road>> arr) {
        var findEdge = dijkstra(N, arr, 0);
        int maxValue = 0;
        int maxNode = 0;
        for (int i=0; i<N; i++){
            if (findEdge[i]>maxValue) {
                maxValue = findEdge[i];
                maxNode = i;
            }
        }
        return Arrays.stream(dijkstra(N, arr, maxNode)).max().getAsInt();
    }

    static int[] dijkstra(int N, ArrayList<ArrayList<Road>> arr, int start){
        var result = new int[N];
        var visited = new boolean[N];

        for (int i=0; i<N-1; i++){
            int nextNode = -1;
            int minValue = MAX_VALUE;
            for (int j=0; j<N; j++){
                if ((result[j]==0 && j!=start) || visited[j] || minValue<=result[j]) continue;
                nextNode = j;
                minValue = result[j];
            }
            visited[nextNode] = true;

            for (Road r: arr.get(nextNode)){
                if (visited[r.dest]) continue;
                if (result[r.dest] == 0) {
                    result[r.dest] = r.cost+result[nextNode];
                } else {
                    result[r.dest] = Math.min(result[r.dest], r.cost + result[nextNode]);
                }
            }
        }

        return result;
    }

    static class Road{
        int dest;
        int cost;

        Road(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }
    }
}