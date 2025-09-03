/*
그냥 제일 위에 올려서...
제일 위부터 성공하면
 */

import java.io.*;
import java.util.*;


public class Main {

    static char EMPTY = '.';
    static char WALL = 'x';
    static char IMPOSSIBLE = 'X';

    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        char[][] map = new char[N][];

        for (int i = 0; i < N; i++) map[i] = bf.readLine().toCharArray();

        System.out.println(solution(N, M, map));
    }

    public static int solution(int N, int M, char[][] map) {
        int result = 0;

        for (int i = 0; i < N; i++) {
            if (make(i, 0, N, M, map)) result++;
        }

        return result;
    }

    public static boolean make(int n, int m, int N, int M, char[][] map) {
        if (!isIn(n, m, N, M)) return false;
        if (map[n][m] == WALL || map[n][m] == IMPOSSIBLE) return false;
        if (m == M - 1) return true;

        for (int d = 0; d < 3; d++) {
            if (make(n + dx[d], m + dy[d], N, M, map)) {
                map[n][m] = WALL;
                return true;
            }
        }

        map[n][m] = IMPOSSIBLE;

        return false;
    }

    public static boolean isIn(int n, int m, int N, int M) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}