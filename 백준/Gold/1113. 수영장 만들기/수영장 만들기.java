import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = NM[0];
        M = NM[1];

        map = new int[N][M];

        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(solution());
    }

    static int solution() {
        var result = 0;
        for (int h=1; h<10; h++) {
            for (int n=0; n<N; n++) {
                for (int m=0; m<M; m++) {
                    result+=fill(n, m, h);
                }
            }
        }
        return result;
    }

    static void printMap() {
        System.out.println(Arrays.stream(map).map(arr -> Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" "))).collect(Collectors.joining("\n", "\n=============================\n", "\n=============================\n")));
    }

    static int fill(int n, int m, int h) {
        var result = 0;

        var q = new LinkedList<Point>();
        q.add(new Point(n, m));
        while (!q.isEmpty()) {
            var now = q.remove();
            if (!isIn(now.n, now.m)) {
                result = -1;
                continue;
            }
            if (map[now.n][now.m] >= h) continue;
            if (result>=0) result++;
            map[now.n][now.m] = h;
            for (int i=0; i<4; i++) {
                var x = dx[i] + now.n;
                var y = dy[i] + now.m;
                q.add(new Point(x, y));
            }
        }

        if (result==-1) return 0;
        return result;
    }

    static boolean isIn(int n, int m) {
        return 0<=n && n<N && 0<=m && m<M;
    }

    static class Point {
        int n;
        int m;

        Point(int n, int m){
            this.n = n;
            this.m = m;
        }
    }
}
