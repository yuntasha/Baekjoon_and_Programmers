import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    static int MAX_VALUE = 500_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var arr = new int[N][];

        for (int i=0; i<N; i++) {
            arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[][] arr) {
        var pq = new PriorityQueue<Road>(Comparator.comparingInt(Road::getT));
        var time = new int[N][N];

        for (int i=0; i<N; i++) {
            Arrays.fill(time[i], MAX_VALUE);
            time[i][i] = 0;
            for (int j=i+1; j<N; j++) {
                pq.add(new Road(i, j, arr[i][j]));
            }
        }

        var result = 0;

        while (!pq.isEmpty()) {
            var now = pq.remove();

            if (time[now.x][now.y] == now.t) continue;
            if (time[now.x][now.y] < now.t) return -1;
            result+=now.t;

            time[now.x][now.y] = time[now.y][now.x] = now.t;

            for (int i=0; i<N; i++) {
                for (int j=i+1; j<N; j++) {
                    time[i][j] = time[j][i] = Math.min(time[i][j], Math.min(time[i][now.x] + now.t + time[now.y][j], time[i][now.y] + now.t + time[now.x][j]));
                }
            }
        }

        return result;
    }


    static class Road {
        int x;
        int y;
        int t;

        public Road(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }

        public int getT() {
            return t;
        }
    }
}