import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[][] map;
    static int[][] result;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        var bf = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][];

        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution());
    }

    static int solution() {
        result = new int[N][M];
        result[N-1][M-1] = 1;
        return dfs(0, 0);
    }

    static int dfs(int n, int m) {
        if (result[n][m] > 0) return result[n][m];
        if (result[n][m] == -1) return 0;

        for (int d=0; d<4; d++) {
            var x = n + dx[d];
            var y = m + dy[d];

            if (x<0 || N<=x || y<0 || M<=y) continue;

            if (map[n][m] > map[x][y]) {
                result[n][m] += dfs(x, y);
            }
        }

        if (result[n][m] == 0) {
            result[n][m] = -1;
            return 0;
        }

        return result[n][m];
    }
}