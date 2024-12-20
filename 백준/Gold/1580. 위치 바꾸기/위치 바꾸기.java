import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static char[][] map;
    static int[][][][] result;
    static int[] dx = {0, 0, 1, 0, -1, -1, -1, 1, 1};
    static int[] dy = {0, 1, 0, -1, 0, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][];

        for (int i = 0; i < N; i++) {
            map[i] = bf.readLine().toCharArray();
        }

        System.out.println(solution());
    }

    static int solution() {
        LinkedList<Node> q = new LinkedList<>();

        result = new int[N][M][N][M];

        int ax = 0;
        int ay = 0;
        int bx = 0;
        int by = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'A') {
                    ax = i;
                    ay = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {
                    bx = i;
                    by = j;
                    map[i][j] = '.';
                }
            }
        }

        int fax = ax;
        int fay = ay;
        int fbx = bx;
        int fby = by;

        q.add(new Node(ax, ay, bx, by));
        result[ax][ay][bx][by] = 1;

        while (!q.isEmpty()) {
            Node now = q.remove();
            for (int d1=0; d1<9; d1++) {
                ax = now.ax + dx[d1];
                ay = now.ay + dy[d1];

                if (!isIn(ax, ay) || map[ax][ay] == 'X') continue;

                for (int d2=0; d2<9; d2++) {
                    bx = now.bx + dx[d2];
                    by = now.by + dy[d2];

                    if (!isIn(bx, by) || map[bx][by] == 'X') continue;

                    if (now.ax == bx && now.ay == by && now.bx == ax && now.by == ay) continue;
                    if (ax == bx && ay == by) continue;

                    if (result[ax][ay][bx][by] > 0) continue;

                    result[ax][ay][bx][by] = result[now.ax][now.ay][now.bx][now.by] + 1;
                    q.add(new Node(ax, ay, bx, by));
                }
            }
        }

        return result[fbx][fby][fax][fay] - 1;
    }

    static boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static class Node {
        int ax;
        int ay;
        int bx;
        int by;

        public Node(int ax, int ay, int bx, int by) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
        }
    }
}