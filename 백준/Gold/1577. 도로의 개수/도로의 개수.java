import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(bf.readLine());

        int[][] map = new int[N+1][M+1];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(bf.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            map[Math.min(x1, x2)][Math.min(y1, y2)] = map[Math.min(x1, x2)][Math.min(y1, y2)] | (1 << (x1 == x2 ? 0 : 1));
        }

        System.out.println(solution(N, M, map));
    }

    static long solution(int N, int M, int[][] map) {
        long[][] result = new long[N+1][M+1];

        result[0][0] = 1;

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (isIn(i + 1, j, N, M) && (map[i][j] & 2) == 0) {
                    result[i + 1][j] += result[i][j];
                }

                if (isIn(i, j + 1, N, M) && (map[i][j] & 1) == 0) {
                    result[i][j + 1] += result[i][j];
                }
            }
        }

        return result[N][M];
    }

    static boolean isIn(int x, int y, int N, int M) {
        return 0 <= x && x <= N && 0 <= y && y <= M;
    }
}