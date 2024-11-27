import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(bf.readLine());

        var N = Integer.parseInt(st.nextToken());
        var M = Integer.parseInt(st.nextToken());
        var T = Integer.parseInt(st.nextToken());
        var D = Integer.parseInt(st.nextToken());

        var map = new int[N][];

        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(bf.readLine().split("")).mapToInt(Main::inputHelper).toArray();
        }

        System.out.println(solution(N, M, T, D, map));
    }

    static int solution(int N, int M, int T, int D, int[][] map) {
        var result = map[0][0];
        var up = new int[N][M];
        var down = new int[N][M];

        var pq = new PriorityQueue<Node>(Comparator.comparingInt(Node::getT));

        pq.add(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            var now = pq.remove();

            if (now.t>D) break;
            if (up[now.x][now.y]>0) continue;
            up[now.x][now.y] = now.t;

            for (int d=0; d<4; d++) {
                var x = now.x + dx[d];
                var y = now.y + dy[d];

                if (!isIn(x, y, N, M)) continue;
                if (up[x][y] > 0 || (x==0 && y==0)) continue;
                if (Math.abs(map[x][y] - map[now.x][now.y])>T) continue;
                var t = now.t;
                if (map[x][y] >= map[now.x][now.y]) t++;
                else t += (map[x][y] - map[now.x][now.y]) * (map[x][y] - map[now.x][now.y]);

                pq.add(new Node(x, y, t));
            }
        }

        pq.clear();
        pq.add(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            var now = pq.remove();

            if (now.t>D) break;
            if (down[now.x][now.y]>0) continue;
            down[now.x][now.y] = now.t;

            for (int d=0; d<4; d++) {
                var x = now.x + dx[d];
                var y = now.y + dy[d];

                if (!isIn(x, y, N, M)) continue;
                if (down[x][y] > 0 || (x==0 && y==0)) continue;
                if (Math.abs(map[x][y] - map[now.x][now.y])>T) continue;
                var t = now.t;
                if (map[x][y] <= map[now.x][now.y]) t++;
                else t += (map[x][y] - map[now.x][now.y]) * (map[x][y] - map[now.x][now.y]);

                pq.add(new Node(x, y, t));
            }
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (up[i][j] == 0 || down[i][j] == 0) continue;
                if (up[i][j] + down[i][j] <= D) {
                    result = Math.max(result, map[i][j]);
                }
            }
        }

        return result;
    }

    static int inputHelper(String s) {
        var c = s.charAt(0);

        if  ('a'<=c && c<='z') {
            return c-'a' + 26;
        }
        return c-'A';
    }

    static boolean isIn(int n, int m, int N, int M) {
        return 0<=n && n<N && 0<=m && m<M;
    }

    static class Node {
        int x;
        int y;
        int t;

        public Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }

        public int getT() {
            return t;
        }
    }
}