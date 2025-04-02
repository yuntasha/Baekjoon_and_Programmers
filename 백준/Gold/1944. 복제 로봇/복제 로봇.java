/*
로봇 복사가 얼마든지 가능함
그러면 각 출발지와 각 열쇠의 거리를 구하고
최소 스패닝 트리를 구하면 정답이 나올 듯 합니다.

총 2500블럭을 탐색하니 한번당 최대 1만번 탐색
그리고 열쇠의 개수가 250개니 250만개
탐색하고
250*250 * log 250 * 250 = 600만?
맞나?
뭐 그정도
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static int FAIL = -1;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char WALL = '1';
    static char EMPTY = '0';
    static char KEY = 'K';
    static char START = 'S';

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        char[][] map = new char[N][];

        for (int i = 0; i < N; i++) {
            map[i] = bf.readLine().toCharArray();
        }

        System.out.println(solution(N, M, map));
    }

    public static int solution(int N, int M, char[][] map) {
        int[][] keyNum = findKey(N, map);

        List<Route> routes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == START || map[i][j] == KEY) {
                    find(N, M, map, keyNum, i, j, routes);
                }
            }
        }

        if (routes.size() < M * (M + 1) / 2) return FAIL;

        routes.sort(Comparator.comparingInt(Route::getV));

        int result = 0;
        int count = 0;
        int[] g = IntStream.rangeClosed(0, M).toArray();

        for (Route route : routes) {
            if (count == M) break;
            int a = findG(route.a, g);
            int b = findG(route.b, g);

            if (a == b) continue;
            g[Math.max(a, b)] = Math.min(a, b);
            result += route.v;
        }

        return result;
    }

    static int findG(int n, int[] g) {
        if (g[n] != n) g[n] = findG(g[n], g);
        return g[n];
    }

    static int[][] findKey(int N, char[][] map) {
        int[][] keyNum = new int[N][N];
        int keyIdx = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == KEY) {
                    keyNum[i][j] = keyIdx++;
                }
            }
        }

        return keyNum;
    }

    static void find(int N, int M, char[][] map, int[][] keyNum, int sx, int sy, List<Route> routes) {
        int start = keyNum[sx][sy];

        boolean[][] visited = new boolean[N][N];

        visited[sx][sy] = true;
        ArrayDeque<Node> q = new ArrayDeque<>();

        q.add(new Node(sx, sy, 0));

        while (!q.isEmpty()) {
            Node now = q.remove();

            if (map[now.x][now.y] == KEY && start < keyNum[now.x][now.y]) routes.add(new Route(start, keyNum[now.x][now.y], now.v));

            for (int d = 0; d < 4; d++) {
                int x = now.x + dx[d];
                int y = now.y + dy[d];

                if (!isIn(x, y, N)) continue;
                if (map[x][y] == WALL) continue;
                if (visited[x][y]) continue;
                q.add(new Node(x, y, now.v + 1));
                visited[x][y] = true;
            }
        }
    }

    public static boolean isIn(int x, int y, int N) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static class Node {
        int x;
        int y;
        int v;

        public Node(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }

    static class Route {
        int a;
        int b;
        int v;

        public Route(int a, int b, int v) {
            this.a = a;
            this.b = b;
            this.v = v;
        }

        public int getV() {
            return v;
        }
    }
}
