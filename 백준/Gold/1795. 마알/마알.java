import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
각 자리에서 다른 애들까지의 거리를 구함
 */

public class Main {

    static int[] dx = {1, 1, 2, 2, -1, -1, -2, -2};
    static int[] dy = {2, -2, 1, -1, 2, -2, 1, -1};
    static int N;
    static int M;
    static char[][] map;
    static int FAIL = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(input.nextToken());
        M = Integer.parseInt(input.nextToken());

        int remain = 0;
        map = new char[N][];

        for (int i = 0; i < N; i++) {
            map[i] = bf.readLine().toCharArray();
            for (char c : map[i]) {
                if (c != '.') remain++;
            }
        }

        System.out.println(solution(remain));
    }

    static int solution(int remain) {
        int result = FAIL;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result = Math.min(result, find(i, j, remain));
            }
        }

        return result == FAIL ? -1 : result;
    }

    static int find(int n, int m, int remain) {
        int result = 0;

        boolean[][] visited = new boolean[N][M];
        ArrayDeque<Node> q = new ArrayDeque<>();

        q.add(new Node(n, m, 0));
        visited[n][m] = true;

        while (!q.isEmpty()) {
            Node now = q.remove();

            if (map[now.x][now.y] != '.') {
                result += now.t/(map[now.x][now.y] & 15) + (now.t%(map[now.x][now.y] & 15) == 0 ? 0 : 1);
                remain--;
            }

            for (int d = 0; d < 8; d++) {
                int x = now.x + dx[d];
                int y = now.y + dy[d];

                if (!isIn(x, y)) continue;
                if (visited[x][y]) continue;

                visited[x][y] = true;

                q.add(new Node(x, y, now.t + 1));
            }
        }

        return remain == 0 ? result : FAIL;
    }

    static boolean isIn(int n, int m) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }

    static class Node {
        int x;
        int y;
        int t;

        public Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
}