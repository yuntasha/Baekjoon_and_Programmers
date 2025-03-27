/*
그냥 코드로 정육면체를 그리자
그리고 안됨
봐봐 옆으로 움직이는 최대가 4개야
그냥 굴릴까?
4가지 방향이니 굴리고
*/

import java.io.*;
import java.util.*;

public class Main {

    static final int LEFT = 0;
    static final int UP = 1;
    static final int RIGHT = 2;
    static final int DOWN = 3;

    static final String YES = "yes";
    static final String NO = "no";

    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 0; t < 3; t++) {
            int[][] map = new int[6][];
            for (int i = 0; i < 6; i++) {
                map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            System.out.println(solution(map));
        }
    }

    public static String solution(int[][] map) {
        Cube cube = new Cube();

        int x = -1;
        int y = -1;

        Loop : for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j] == 1) {
                    x = i;
                    y = j;
                    break Loop;
                }
            }
        }

        return rolling(map, cube, x, y, new boolean[6][6]) ? YES : NO;
    }

    static boolean rolling(int[][] map, Cube cube, int nx, int ny, boolean[][] visited) {
        visited[nx][ny] = true;
//        System.out.println("nx = " + nx);
//        System.out.println("ny = " + ny);
        for (int d = 0; d < 4; d++) {
            int x = nx + dx[d];
            int y = ny + dy[d];

            if (!isIn(x, y)) continue;
            if (map[x][y] == 0) continue;
            if (visited[x][y]) continue;

            if (!cube.turn(d)) {
//                System.out.println("d = " + d);
//                System.out.println("sadsad");
                return false;
            }
            if (!rolling(map, cube, x, y, visited)) return false;
            cube.turn((d + 2) % 4);
        }

        return true;
    }

    static boolean isIn(int x, int y) {
        return 0 <= x && x < 6 && 0 <= y && y < 6;
    }

    static class Cube {
        boolean[] visited;

        public Cube() {
            this.visited = new boolean[6];
            visited[0] = true;
        }

        boolean turn(int d) {
            switch (d) {
                case LEFT:
//                    System.out.println("LEFT");
                    left();
                    break;
                case RIGHT:
//                    System.out.println("RIGHT");
                    right();
                    break;
                case DOWN:
//                    System.out.println("DOWN");
                    down();
                    break;
                case UP:
//                    System.out.println("UP");
                    up();
                    break;
            }

//            System.out.println((Arrays.toString(visited)));
            if (visited[0]) return false;
            visited[0] = true;
            return true;
        }

        void left() {
            boolean[] next = new boolean[6];

            next[0] = visited[2];
            next[1] = visited[1];
            next[2] = visited[5];
            next[3] = visited[0];
            next[4] = visited[4];
            next[5] = visited[3];

            visited = next;
        }

        void right() {
            boolean[] next = new boolean[6];

            next[0] = visited[3];
            next[1] = visited[1];
            next[2] = visited[0];
            next[3] = visited[5];
            next[4] = visited[4];
            next[5] = visited[2];

            visited = next;
        }

        void up() {
            boolean[] next = new boolean[6];

            next[0] = visited[4];
            next[1] = visited[0];
            next[2] = visited[2];
            next[3] = visited[3];
            next[4] = visited[5];
            next[5] = visited[1];

            visited = next;
        }

        void down() {
            boolean[] next = new boolean[6];

            next[0] = visited[1];
            next[1] = visited[5];
            next[2] = visited[2];
            next[3] = visited[3];
            next[4] = visited[0];
            next[5] = visited[4];

            visited = next;
        }
    }

    static int read() throws IOException {
        int n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}