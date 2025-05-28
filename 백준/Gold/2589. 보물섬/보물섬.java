import java.io.*;
import java.util.*;

/*
그냥 전체 위치에서 갈 수 있는 최장거리 뽑아서 가져오자
 */

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            char[] cs = bf.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = cs[j] == 'L' ? 1 : 0;
            }
        }

        System.out.println(solution(N, M, map));
    }

    public static int solution(int N, int M, int[][] map) {
        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result = Math.max(result, getDist(i, j, N, M, map));
            }
        }

        return result;
    }

    public static int getDist(int n, int m, int N, int M, int[][] map) {
        if (map[n][m] == 0) return 0;

        int result = 0;
        ArrayDeque<Node> q = new ArrayDeque<>();
        int[][] dist = new int[N][M];

        dist[n][m] = 1;
        q.add(new Node(n, m));

        while (!q.isEmpty()) {
            Node now = q.remove();

            result = dist[now.n][now.m];

            for (int d = 0; d < 4; d++) {
                int x = now.n + dx[d];
                int y = now.m + dy[d];

                if (!isIn(x, y, N, M)) continue;
                if (map[x][y] == 0) continue;
                if (dist[x][y] > 0) continue;
                dist[x][y] = dist[now.n][now.m] + 1;
                q.add(new Node(x, y));
            }
        }

        return result - 1;
    }

    static boolean isIn(int n, int m, int N, int M) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }

    static class Node {
        int n;
        int m;

        public Node(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}