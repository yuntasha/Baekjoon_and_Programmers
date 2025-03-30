/*
판다는 점점 더 높은 위치로 옮겨감
무지성 조회는 n * n * n * n = 500 ^ 4 = 62_500_000_000
근데 잘 생각해보면 각 점에서 움직일 수 있는 위치가 한정되어 있고 같은 위치 계속 움직임
따라서 가장 큰 위치부터 정렬해서 더 작은 애한테 자기 자신 + 1 해주면 옮긴 위치 업데이트 된다..!

대나무 양 최대 100만
최대 움직일 수 있는 수 250_000

전체 탐색 n * n + 정렬 n * n log n * n + 탐색 n * n = 1_849_485
*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int N;
    static int[][] map;
    static int[][][] block = {
            {{0, -1}, {0, 0}, {0, 1}},
            {{-1, 0}, {0, 0}, {1, 0}}
    };

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        map = new int[N][N];
        int[][] start = new int[3][2];
        int[][] end = new int[3][2];
        int sIdx = 0;
        int eIdx = 0;

        for (int i = 0; i < N; i++) {
            char[] input = bf.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                char now = input[j];

                if (now == 'B') {
                    start[sIdx][0] = i;
                    start[sIdx++][1] = j;
                    map[i][j] = 0;
                } else if (now == 'E') {
                    end[eIdx][0] = i;
                    end[eIdx++][1] = j;
                    map[i][j] = 0;
                } else {
                    map[i][j] = now & 15;
                }
            }
        }

        System.out.println(solution(N, map, start, end));
    }

    public static int solution(int N, int[][] map, int[][] start, int[][] end) {
        Tree s = new Tree(start[1][0], start[1][1], start[1][0] - start[0][0]);
        Tree e = new Tree(end[1][0], end[1][1], end[1][0] - end[0][0]);

        int[][][] visited = new int[N][N][2];

        ArrayDeque<Tree> q = new ArrayDeque<>();

        q.add(s);
        visited[s.x][s.y][s.isI] = 1;

        while (!q.isEmpty()) {
            Tree now = q.remove();

            Tree next = now.up();
            if (next.isPossible()) {
                if (visited[next.x][next.y][next.isI] == 0) {
                    q.add(next);
                    visited[next.x][next.y][next.isI] = visited[now.x][now.y][now.isI] + 1;
                }
            }

            next = now.down();
            if (next.isPossible()) {
                if (visited[next.x][next.y][next.isI] == 0) {
                    q.add(next);
                    visited[next.x][next.y][next.isI] = visited[now.x][now.y][now.isI] + 1;
                }
            }

            next = now.left();
            if (next.isPossible()) {
                if (visited[next.x][next.y][next.isI] == 0) {
                    q.add(next);
                    visited[next.x][next.y][next.isI] = visited[now.x][now.y][now.isI] + 1;
                }
            }

            next = now.right();
            if (next.isPossible()) {
                if (visited[next.x][next.y][next.isI] == 0) {
                    q.add(next);
                    visited[next.x][next.y][next.isI] = visited[now.x][now.y][now.isI] + 1;
                }
            }

            next = now.rotate();
            if (next.canRot()) {
                if (visited[next.x][next.y][next.isI] == 0) {
                    q.add(next);
                    visited[next.x][next.y][next.isI] = visited[now.x][now.y][now.isI] + 1;
                }
            }
        }

        return Math.max(0, visited[e.x][e.y][e.isI] - 1);
    }

    static class Tree {
        int x;
        int y;
        int isI;

        public Tree(int x, int y, int isI) {
            this.x = x;
            this.y = y;
            this.isI = isI;
        }

        boolean isPossible() {
            for (int i = 0; i < 3; i++) {
                int x = this.x + block[isI][i][0];
                int y = this.y + block[isI][i][1];

                if (!isIn(x, y) || map[x][y] == 1) return false;
            }
            return true;
        }

        private static boolean isIn(int x, int y) {
            return 0 <= x && x < N && 0 <= y && y < N;
        }

        public Tree left() {
            return new Tree(x, y - 1, isI);
        }

        public Tree right() {
            return new Tree(x, y + 1, isI);
        }

        public Tree up() {
            return new Tree(x - 1, y, isI);
        }

        public Tree down() {
            return new Tree(x + 1, y, isI);
        }

        public Tree rotate() {
            return new Tree(x, y, isI ^ 1);
        }

        boolean canRot() {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (!isIn(x + i, y + j) || map[x + i][y + j] == 1) return false;
                }
            }

            return true;
        }
    }
}