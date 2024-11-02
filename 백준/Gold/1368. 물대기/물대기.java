import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var dig = new int[N];
        
        for (int i=0; i<N; i++) {
            dig[i] = Integer.parseInt(bf.readLine());
        }

        var cost = new int[N][N];

        for (int i=0; i<N; i++) {
            cost[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, dig, cost));
    }

    static int solution(int N, int[] dig, int[][] cost) {
        var pq = new PriorityQueue<Line>(Comparator.comparingInt(Line::getCost));
        var now = Arrays.stream(dig).reduce(Integer::sum).orElse(0);
        var result = now;
        var group = IntStream.range(0, N).toArray();

        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                pq.add(new Line(i, j, cost[i][j]));
            }
        }

        while (!pq.isEmpty()) {
            var line = pq.remove();
            var x = find(line.x, group);
            var y = find(line.y, group);

            if (x==y) {
                continue;
            }
            if (Math.max(dig[x], dig[y]) < line.cost) {
                continue;
            }

            group[Math.max(x, y)] = Math.min(x, y);

            now -= Math.max(dig[x], dig[y]);
            dig[Math.min(x, y)] = Math.min(dig[x], dig[y]);
            now += line.cost;
            result = Math.min(result, now);
        }

        return result;
    }

    static int find(int n, int[] group) {
        if (group[n]!=n) {
            group[n] = find(group[n], group);
        }
        return group[n];
    }

    static class Line {
        int x;
        int y;
        int cost;

        Line (int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }
    }
}