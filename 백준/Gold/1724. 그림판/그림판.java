import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
map을 만들어서 map에서 각각 오른쪽 왼쪽 위 아래를 갈 수 있는지 없는지를 확인하는 것
그 다음에 전체 점 탐색하고 각 점이 가질 수 있는 최대 넓이를 구해보자
 */

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int RIGHT = 0;
    static int DOWN = 1;
    static int LEFT = 2;
    static int UP = 3;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int T = Integer.parseInt(bf.readLine());

        int[][] map = new int[N][M];

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(bf.readLine());

            int Sx = Integer.parseInt(st.nextToken());
            int Sy = Integer.parseInt(st.nextToken());
            int Ex = Integer.parseInt(st.nextToken());
            int Ey = Integer.parseInt(st.nextToken());

            if (Sx == Ex) {
                if (Sx == 0 || Sx == N) continue;

                if (Sy > Ey) {
                    int temp = Sy;
                    Sy = Ey;
                    Ey = temp;
                }

                for (int y = Sy; y < Ey; y++) {
                    map[Sx - 1][y] |= 1<<DOWN;
                    map[Sx][y] |= 1<<UP;
                }
            } else {
                if (Sy == 0 || Sy == M) continue;

                if (Sx > Ex) {
                    int temp = Sx;
                    Sx = Ex;
                    Ex = temp;
                }

                for (int x = Sx; x < Ex; x++) {
                    map[x][Sy - 1] |= 1<<RIGHT;
                    map[x][Sy] |= 1<<LEFT;
                }
            }
        }

        System.out.println(Arrays.stream(solution(map)).mapToObj(String::valueOf).collect(Collectors.joining("\n")));
    }

    public static int[] solution(int[][] map) {
        boolean[][] visited = new boolean[N][M];
        int[] result = {Integer.MIN_VALUE, Integer.MAX_VALUE};

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (visited[x][y]) continue;
                update(getExtent(x, y, map, visited), result);
            }
        }

        return result;
    }

    public static void update(int now, int[] result) {
        result[0] = Math.max(result[0], now);
        result[1] = Math.min(result[1], now);
    }

    public static int getExtent(int x, int y, int[][] map, boolean[][] visited) {
        ArrayDeque<Node> q = new ArrayDeque<>();

        q.add(new Node(x, y));
        visited[x][y] = true;
        int result = 1;

        while (!q.isEmpty()) {
            Node now = q.remove();

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (!isIn(nx, ny)) continue;
                if ((map[now.x][now.y] & (1 << d)) > 0) continue;
                if (visited[nx][ny]) continue;

                result++;
                visited[nx][ny] = true;
                q.add(new Node(nx, ny));
            }
        }

        return result;
    }

    static boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
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