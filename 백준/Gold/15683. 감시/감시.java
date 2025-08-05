/*
단순한 접근
 */

import java.io.*;
import java.util.*;

public class Main {

    static int EMPTY = 0;
    static int WALL = 6;
    static int VIEW = 7;

    static int[] UP = {-1, 0};
    static int[] DOWN = {1, 0};
    static int[] LEFT = {0, -1};
    static int[] RIGHT = {0, 1};

    // 번호, 방향, 한방향에서 볼 수 있는 곳, xy
    static int[][][][] CCTV = {
            {},
            {{UP}, {DOWN}, {LEFT}, {RIGHT}},
            {{UP, DOWN}, {LEFT, RIGHT}},
            {{UP, RIGHT}, {RIGHT, DOWN}, {DOWN, LEFT}, {LEFT, UP}},
            {{UP, RIGHT, DOWN}, {RIGHT, DOWN, LEFT}, {DOWN, LEFT, UP}, {LEFT, UP, RIGHT}},
            {{UP, RIGHT, DOWN, LEFT}}
    };


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        System.out.println(solution(N, M, map));
    }

    public static int solution(int N, int M, int[][] map) {
        List<Node> cctvs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] <= 5 && map[i][j] >= 1) cctvs.add(new Node(i, j));
            }
        }

        return find(0, new int[cctvs.size()], cctvs, N, M, map);
    }

    public static int find(int n, int[] directs, List<Node> cctvs, int N, int M, int[][] map) {
        if (n == cctvs.size()) {
            return getNotView(directs, cctvs, N, M, map);
        }

        int result = N * M;

        for (int i = 0; i < CCTV[map[cctvs.get(n).x][cctvs.get(n).y]].length; i++) {
            directs[n] = i;
            result = Math.min(result, find(n + 1, directs, cctvs, N, M, map));
        }

        return result;
    }

    public static int getNotView(int[] directs, List<Node> cctvs, int N, int M, int[][] map) {
        boolean[][] canView = new boolean[N][M];

        for (int c = 0; c < cctvs.size(); c++) {
            Node cctv = cctvs.get(c);
            for (int[] xy : CCTV[map[cctv.x][cctv.y]][directs[c]]) {
                int x = cctv.x + xy[0];
                int y = cctv.y + xy[1];

                while (0 <= x && x < N && 0 <= y && y < M && map[x][y] != WALL) {
                    canView[x][y] = true;
                    x += xy[0];
                    y += xy[1];
                }
            }
        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!canView[i][j] && map[i][j] == EMPTY) result++;
            }
        }

        return result;
    }

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}