import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var M = NM[0];
        var N = NM[1];

        var map = new int[N][];

        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, M, map));
    }

    static int solution(int N, int M, int[][] map) {
        var q = new LinkedList<Map.Entry<Integer, Integer>>();

        var result = new int[N][M];

        var dx = new int[]{0, 1, 0, -1};
        var dy = new int[]{1, 0, -1, 0};

        for (int i=0; i<N; i++) Arrays.fill(result[i], N*M+1);

        result[0][0] = 0;

        q.add(Map.entry(0, 0));

        while (!q.isEmpty()) {
            var now = q.remove();

            for (int i=0; i<4; i++) {
                var x = now.getKey() + dx[i];
                var y = now.getValue() + dy[i];

                if (x<0 || x>=N || y<0 || y>=M) continue;

                var nowValue = map[x][y]==1?result[now.getKey()][now.getValue()]+1:result[now.getKey()][now.getValue()];
                if (nowValue>=result[x][y]) continue;

                result[x][y] = nowValue;
                q.add(Map.entry(x, y));
            }
        }

        return result[N-1][M-1];
    }
}