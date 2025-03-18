/*
1. 현재 곰팡이 1개로 이루어져있는지 확인하기
2. 곰팡이 늘리기

1개로 이루어져있는지 저거는 그냥 만들면되고
곰팡이 늘리기도 BFS로 하자
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        int N = read();
        int M = read();

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = readN();
            }
        }

        System.out.println(solution(N, M, map));
    }

    public static int solution(int N, int M, int[][] map) {
        ArrayDeque<Node> q = new ArrayDeque<>();

        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) q.add(new Node(i, j, map[i][j]));
            }
        }

        while (!isOne(N, M, map)) {
            go(N, M, map, q);
//            System.out.println(Arrays.stream(map).map(arr -> Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(""))).collect(Collectors.joining("\n")));
            result++;
        }

        return result;
    }

    public static void go(int N, int M, int[][] map, ArrayDeque<Node> q) {
        int n = q.size();

        for (int i = 0; i < n; i++) {
            Node now = q.remove();

            for (int x = now.x - now.v; x <= now.x + now.v; x++) {
                for (int y = now.y - now.v; y <= now.y + now.v; y++) {
                    if (!isIn(x, y, N, M)) continue;
                    if (map[x][y] >= now.v) continue;
                    q.add(new Node(x, y, now.v));
                    map[x][y] = now.v;
                }
            }
        }
    }

    public static boolean isOne(int N, int M, int[][] map) {
        boolean[][] visited = new boolean[N][M];
        boolean isFirst = false;

        ArrayDeque<Node> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                if (visited[i][j]) continue;
                if (isFirst) return false;
                isFirst = true;

                q.add(new Node(i, j, 0));
                visited[i][j] = true;

                while (!q.isEmpty()) {
                    Node now = q.remove();

                    for (int d = 0; d < 4; d++) {
                        int x = now.x + dx[d];
                        int y = now.y + dy[d];

                        if (!isIn(x, y, N, M)) continue;
                        if (visited[x][y]) continue;
                        if (map[x][y] == 0) continue;

                        q.add(new Node(x, y, 0));
                        visited[x][y] = true;
                    }
                }
            }
        }

        return true;
    }

    static boolean isIn(int x, int y, int N, int M) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static int readN() throws IOException {
        int n;
        while ((n = System.in.read()) < '0');
        return n & 15;
    }

    public static int read() throws IOException {
        int n = 0;
        int c;
        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    public static class Node {
        int x;
        int y;
        int v;

        public Node(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }
}