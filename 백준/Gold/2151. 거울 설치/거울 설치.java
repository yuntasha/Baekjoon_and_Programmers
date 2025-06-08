/*
상하좌우로 가는거 만들고 !가 있으면 그냥 직진도 하고 좌우로도 움직이게 만들자
dxdy를 상좌하우로 만들어서 알맞게 만들고 암튼 그렇게 ㄱㄱ
한쪽 방향으로 끝까지 진입하고 거기서 !만나면 다음 방향과 위치 지정하고 출발
 */

import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char WALL = '*';
    static char MIRROR = '!';
    static char DOOR = '#';
    static char EMPTY = '.';

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        char[][] map = new char[N][];

        for (int i = 0; i < N; i++) {
            map[i] = bf.readLine().toCharArray();
        }

        System.out.println(solution(N, map));
    }

    static int solution(int N, char[][] map) {
        List<Node> doors = findDoor(N, map);

        int[][][] visited = new int[N][N][4];

        ArrayDeque<Node> q = new ArrayDeque<>();

        for (int d = 0; d < 4; d++) {
            q.add(new Node(doors.get(0).x, doors.get(0).y, d));
            visited[doors.get(0).x][doors.get(0).y][d] = 1;
        }

        while (!q.isEmpty()) {
            Node node = q.remove();

            int d = node.d;
            int x = node.x + dx[d];
            int y = node.y + dy[d];
            int count = visited[node.x][node.y][d];

            while (isIn(x, y, N) && map[x][y] != WALL && visited[x][y][d] == 0) {
                if (map[x][y] == MIRROR) {
                    int right = (d + 1) % 4;
                    int left = (d + 3) % 4;

                    q.add(new Node(x, y, right));
                    q.add(new Node(x, y, left));

                    visited[x][y][right] = count + 1;
                    visited[x][y][left] = count + 1;
                }

                visited[x][y][d] = count;
                x += dx[d];
                y += dy[d];
            }
        }

        int result = 10000;

        for (int d = 0; d < 4; d++) {
            if (visited[doors.get(1).x][doors.get(1).y][d] != 0) {
                result = Math.min(result, visited[doors.get(1).x][doors.get(1).y][d]);
            }
        }

        return result - 1;
    }

    static boolean isIn(int x, int y, int N) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static List<Node> findDoor(int N, char[][] map) {
        List<Node> doors = new ArrayList<>(2);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == DOOR) {
                    map[i][j] = EMPTY;
                    doors.add(new Node(i, j));
                }
            }
        }

        return doors;
    }

    static class Node {
        int x;
        int y;
        int d;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}