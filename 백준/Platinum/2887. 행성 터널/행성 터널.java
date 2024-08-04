import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var arr = new int[N][4];

        for (int i=0; i<N; i++) {
            var now = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            arr[i][0] = i;
            arr[i][1] = now[0];
            arr[i][2] = now[1];
            arr[i][3] = now[2];
        }

        System.out.println(solution(N, arr));
    }

    static long solution(int N, int[][] arr){
        var pq = new PriorityQueue<Line>(Comparator.comparingInt(Line::getValue));

        Arrays.sort(arr, Comparator.comparingInt(i -> i[1]));

        for (int i=0; i<N-1; i++){
            pq.add(new Line(arr[i][0], arr[i+1][0], arr[i+1][1]-arr[i][1]));
        }

        Arrays.sort(arr, Comparator.comparingInt(i -> i[2]));

        for (int i=0; i<N-1; i++){
            pq.add(new Line(arr[i][0], arr[i+1][0], arr[i+1][2]-arr[i][2]));
        }

        Arrays.sort(arr, Comparator.comparingInt(i -> i[3]));

        for (int i=0; i<N-1; i++){
            pq.add(new Line(arr[i][0], arr[i+1][0], arr[i+1][3]-arr[i][3]));
        }

        var graph = IntStream.range(0, N).toArray();
        var cnt = 0;
        var sum = 0L;
        while (cnt<N-1){
            var now = pq.remove();
            var x = findParent(now.x, graph);
            var y = findParent(now.y, graph);
            if (x==y) continue;
            graph[Math.max(x, y)] = Math.min(x, y);
            sum+=now.value;
            cnt++;
        }
        return sum;
    }

    static int findParent(int n, int[] graph){
        if (n==graph[n]) return n;
        graph[n] = findParent(graph[n], graph);
        return graph[n];
    }

    static class Line{
        int x;
        int y;
        int value;

        Line(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}