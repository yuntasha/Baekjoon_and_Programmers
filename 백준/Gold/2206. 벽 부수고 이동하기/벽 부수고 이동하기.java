import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        var arr = new int[N][M];
        for (int i=0; i<N; i++){
            arr[i] = Arrays.stream(bufferedReader.readLine().strip().split("")).mapToInt(Integer::parseInt).toArray();
        }

        var result = solution(N, M, arr);
        System.out.println(result == 0? -1:result);
    }

    static int solution(int N, int M, int[][] arr){
        LinkedList<Point> ll = new LinkedList<>();
        ll.addFirst(new Point(0,0,0));
        var time = new int[2][N][M];
        time[0][0][0] = 1;

        var dx = new int[] {0, 1, 0, -1};
        var dy = new int[] {1, 0, -1, 0};

        while (!ll.isEmpty()) {
            var now = ll.removeLast();
            for (int i=0; i<4; i++){
                var x = now.n + dx[i];
                var y = now.m + dy[i];

                if (!(0<=x && x<N && 0<=y && y<M)) continue;
                if (arr[x][y] == 1) {
                    if (now.destroy == 1 || time[1][x][y]!=0) continue;
                    time[1][x][y] = time[now.destroy][now.n][now.m] + 1;
                    ll.addFirst(new Point(x, y, 1));
                } else {
                    if (time[now.destroy][x][y] != 0) continue;
                    time[now.destroy][x][y] = time[now.destroy][now.n][now.m] + 1;
                    ll.addFirst(new Point(x, y, now.destroy));
                }
            }
        }
        if (time[0][N-1][M-1] * time[1][N-1][M-1] == 0) return time[0][N-1][M-1] + time[1][N-1][M-1];
        return Math.min(time[0][N-1][M-1], time[1][N-1][M-1]);
    }

    static class Point{
        int destroy;
        int n;
        int m;

        Point(int n, int m, int destroy){
            this.destroy = destroy;
            this.n = n;
            this.m = m;
        }
    }
}