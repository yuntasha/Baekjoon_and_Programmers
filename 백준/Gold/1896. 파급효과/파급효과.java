/*
위 - 1
오 - 2
아 - 4
왼 - 8

일단 비트로 싹 다 돌아서 or 연산으로 양방향으로 이어주기
그리고 최대 숫자가 15니까 비트로 어떻게 되는지 확인
각 그룹당 숫자가 제대로 있는지 확인 비트로 했으니까 1일때 전부 >>해서 0만났을때 0이면 성공
visited로 처리해서 이미 왔다간 곳 처리 X

그리고 숫자사이 숫자는 그 뭐시냐 가로를 책임지는 애들 + 세로를 책임지는 애들로 완탐
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int UP = 0;
    static int RIGHT = 1;
    static int DOWN = 2;
    static int LEFT = 3;
    static String SUCCESS = "valid";
    static String FAIL = "invalid";
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        StringBuilder result = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int R = Integer.parseInt(input.nextToken());
            int C = Integer.parseInt(input.nextToken());

            int[][] map = new int[R][];

            for (int i = 0; i < R; i++) {
                map[i] = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            }

            int[][] lines = new int[R][C];

            for (int i = 0; i < R; i++) {
                input = new StringTokenizer(bf.readLine());
                for (int j = 0; j < C; j++) {
                    lines[i][j] = Integer.parseInt(input.nextToken());
                }
            }

            result.append(solution(R, C, lines, map)).append("\n");
        }

        System.out.print(result.toString());
    }

    public static String solution(int R, int C, int[][] lines, int[][] map) {
//        connect(R, lines);

        boolean[][] visited = new boolean[R][C];

//        System.out.println(Arrays.stream(lines).map(Arrays::toString).collect(Collectors.joining("\n", "\n===========\n", "\n===========\n")));
//        System.out.println(Arrays.stream(map).map(Arrays::toString).collect(Collectors.joining("\n", "\n===========\n", "\n===========\n")));

//        System.out.println("start");

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < R; j++) {
                if (visited[i][j]) continue;
//                System.out.println(i + " " + j);
                if (!groupCheck(R, C, lines, map, visited, j, i)) return FAIL;
            }
        }

//        System.out.println("check");

        int[] lastI = new int[9];

        for (int j = 0; j < C; j++) {
            Arrays.fill(lastI, -1);
            for (int i = 0; i < R; i++) {
                int now = map[i][j];
                if (lastI[now] != -1 && i - lastI[now] <= now) {
//                    System.out.println(i + " " + j + " i");
                    return FAIL;
                }
                lastI[now] = i;
            }
        }

        int[] lastJ = new int[9];

        for (int i = 0; i < R; i++) {
            Arrays.fill(lastJ, -1);
            for (int j = 0; j < C; j++) {
                int now = map[i][j];
                if (lastJ[now] != -1 && j - lastJ[now] <= now) {
//                    System.out.println(i + " " + j + " j");
                    return FAIL;
                }
                lastJ[now] = j;
            }
        }
//        System.out.println("last");

        return SUCCESS;
    }

    static boolean groupCheck(int R, int C, int[][] lines, int[][] map, boolean[][] visited, int x, int y) {
        int bits = 0;
        int count = 0;

        ArrayDeque<Node> q = new ArrayDeque<>();

        q.add(new Node(x, y));
        visited[y][x] = true;

        while(!q.isEmpty()) {
            Node now = q.remove();
            count++;
            bits |= (1 << (map[now.y][now.x] - 1));
//            System.out.println((1 << (map[now.y][now.x] - 1)) + " -> " + bits + " " + map[now.y][now.x]);

//            System.out.println(now.x + " " + now.y);

//            System.out.println(Arrays.stream(visited).map(Arrays::toString).collect(Collectors.joining("\n", "\n===========\n", "\n===========\n")));

            for (int d = 0; d < 4; d++) {
                if ((lines[now.y][now.x] & (1 << d)) == 0) continue;
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (!isIn(nx, ny, R, C)) continue;
                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.add(new Node(nx, ny));
            }
        }

//        System.out.println(bits);

        return bits == (1 << count) - 1;
    }

//    public static void connect(int R, int[][] lines) {
//        for (int i = 0; i < R; i++) {
//            for (int j = 0; j < R; j++) {
//                for (int d = 0; d < 4; d++) {
//                    if ((lines[i][j] & (1 << d)) == 0) continue;
//                    int x = j + dx[d];
//                    int y = i + dy[d];
//                    if (!isIn(x, y, R)) continue;
//
//                    lines[x][y] |= (1 << ((d + 2) & 3));
//                }
//            }
//        }
//    }

    static boolean isIn(int x, int y, int R, int C) {
        return 0 <= x && x < C && 0 <= y && y < R;
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}