/*
차례대로 하면 왼 위를 보고 정하면 됨
그럼 제일 위는?
제일 왼쪽은?
근데 그거만 정하면 끝나는거 아님?
dfs로 제일 윗줄 정하고 윗줄에 있는거만하면 되려나???

그럼 시복은 100 * 2^10 EASY?
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static char OFF = '#';
    static char ON = 'O';
    static int L = 10;

    static int MAX = 1000;

    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, 1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        char[][] map = new char[L][];

        for (int i = 0; i < L; i++) {
            map[i] = bf.readLine().toCharArray();
        }

        System.out.println(solution(map));
    }

    public static int solution(char[][] map) {
        int result = find(0, 0, map, new char[L][L]);

        return result >= MAX ? -1 : result;
    }

    public static int find(int n, int change, char[][] map, char[][] temp) {
        if (n == L) {
            copy(map, temp);
            return getCount(OFF, temp) + change;
        }

        int result = MAX;

        result = Math.min(result, find(n + 1, change, map, temp));

        convert(0, n, map);
        result = Math.min(result, find(n + 1, change + 1, map, temp));
        convert(0, n, map);

        return result;
    }

    public static int getCount(char state, char[][] map) {
        int count = 0;

        for (int i = 1; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (map[i - 1][j] == state) continue;
                count++;
                convert(i, j, map);
            }
        }

        for (int i = 0; i < L; i++) {
            if (map[L - 1][i] != state) return MAX;
        }

        return count;
    }

    public static void convert(int sx, int sy, char[][] map) {
        for (int d = 0; d < 5; d++) {
            int x = sx + dx[d];
            int y = sy + dy[d];

            if (!isIn(x, y)) continue;
            if (map[x][y] == ON) map[x][y] = OFF;
            else map[x][y] = ON;
        }
    }

    public static boolean isIn(int x, int y) {
        return 0 <= x && x < L && 0 <= y && y < L;
    }

    public static void copy(char[][] ori, char[][] result) {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                result[i][j] = ori[i][j];
            }
        }
    }
}