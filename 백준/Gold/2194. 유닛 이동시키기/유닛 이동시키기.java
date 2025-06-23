/*
맵을 쭉 탐색하면서 장애물이 있으면 못가는 위치찍어두자
그리고 BFS하면 끝날듯
 */

import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//
//        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = readI();
        int M = readI();
        int A = readI();
        int B = readI();
        int K = readI();

        int[][] map = new int[N - A + 1][M - B + 1];

        for (int i = 0; i < K; i++) {
            int a = readI() - 1;
            int b = readI() - 1;

            for (int n = Math.max(a - A + 1, 0); n <= Math.min(a, N - A); n++) {
                for (int m = Math.max(b - B + 1, 0); m <= Math.min(b, M - B); m++) {
                    map[n][m] = -1;
                }
            }
        }

//        for (int[] m : map) System.out.println(Arrays.toString(m));

        int[] start = {readI() - 1, readI() - 1};
        int[] end = {readI() - 1, readI() - 1};

        System.out.println(solution(N, M, A, B, K, map, start, end));
    }

    static int solution(int N, int M, int A, int B, int K, int[][] map, int[] start, int[] end) {
        ArrayDeque<Point> q = new ArrayDeque<>();

        q.add(new Point(start[0], start[1]));
        map[start[0]][start[1]] = 1;

        while (!q.isEmpty()) {
            Point now = q.remove();

            for (int d = 0; d < 4; d++) {
                int x = now.x + dx[d];
                int y = now.y + dy[d];

                if (!isIn(x, y, N, M, A, B)) continue;
                if (map[x][y] > 0 || map[x][y] == -1) continue;
                map[x][y] = map[now.x][now.y] + 1;
                q.add(new Point(x, y));
            }
        }

//        for (int[] m : map) System.out.println(Arrays.toString(m));

        return Math.max(map[end[0]][end[1]] - 1, -1);
    }

    static boolean isIn(int x, int y, int N, int M, int A, int B) {
        return 0 <= x && x <= N - A && 0 <= y && y <= M - B;
    }

    static long readUL() throws IOException {
        int n = System.in.read();
        return n == '-' ? -readL() : readL(n & 15);
    }

    static int readI() throws IOException {
        int n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }

    static long readL() throws IOException {
        long n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }

    static long readL(long n) throws IOException {
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}