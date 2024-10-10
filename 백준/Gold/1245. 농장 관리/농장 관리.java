import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

public class Main {

    static int INF = 100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        var map = new int[N][];

        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, M, map));
    }

    static int solution(int N, int M, int[][] map) {
        var result = 0;
        var visited = new boolean[N][M];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                result += isTop(N, M, map, visited, i, j);
            }
        }

        return result;
    }

    static int isTop(int N, int M, int[][] map, boolean[][] visited, int x, int y) {
        var dx = new int[]{0, 1, 0, -1, 1, 1, -1, -1};
        var dy = new int[]{1, 0, -1, 0, 1, -1, 1, -1};
        var q = new LinkedList<Map.Entry<Integer, Integer>>();
        if (visited[x][y]) return 0;
        var h = map[x][y];
        visited[x][y] = true;
        q.add(Map.entry(x, y));

        var result = 1;

        while (!q.isEmpty()) {
            var now = q.remove();

            for (int i=0; i<8; i++) {
                var nx = now.getKey() + dx[i];
                var ny = now.getValue() + dy[i];
                if (0>nx || nx>=N || 0>ny || ny>=M) continue;

                if (map[nx][ny] > h) {
                    result = 0;
                } else if (map[nx][ny]==h) {
                    if (visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    q.add(Map.entry(nx, ny));
                }
            }
        }

        return result;
    }
}