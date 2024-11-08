import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        while (NM[0] != 0 || NM[1] != 0) {
            var N = NM[0];
            var M = NM[1];

            var arr = new char[N][];

            for (int n = 0; n < N; n++) {
                arr[n] = bf.readLine().toCharArray();
            }

            var blinkers = new ArrayList<Blinker>();

            var now = bf.readLine();

            while (!now.isBlank()) {
                var nodes = now.split(" ");
                blinkers.add(new Blinker(Integer.parseInt(nodes[0]), nodes[1].equals("-") ? 0 : 1, new int[]{Integer.parseInt(nodes[2]), Integer.parseInt(nodes[3])}));
                now = bf.readLine();
            }

            System.out.println(solution(N, M, arr, blinkers));


            NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }

    static String solution(int N, int M, char[][] arr, List<Blinker> blinkers) {
        var q = new LinkedList<Node>();

        var visited = new boolean[N][M];

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (arr[n][m] == 'A') {
                    q.add(new Node(n, m, 0));
                    visited[n][m] = true;
                }
            }
        }

        var t = 0;

        while (!q.isEmpty()) {
            var now = q.remove();

            if (t<now.t) {
                for (Blinker b: blinkers) {
                    b.timeGo();
                }
                t++;
            }

            for (int d=0; d<4; d++) {
                var x = now.x + dx[d];
                var y = now.y + dy[d];


                if (!(0<=x && x<N && 0<=y && y<M)) continue;
                if (visited[x][y]) continue;

                if (arr[x][y]=='#') {
                    q.add(new Node(x, y, now.t+1));
                    visited[x][y] = true;
                }
                if ('0'<=arr[x][y] && arr[x][y]<='9') {
                    var b = blinkers.get(arr[x][y]-'0');
                    if (b.go(d)) {
                        q.add(new Node(x, y, now.t+1));
                        visited[x][y] = true;
                    } else {
                        q.add(new Node(now.x, now.y, now.t+1));
                    }
                }
                if (arr[x][y]=='B') {
                    return String.valueOf(t+1);
                }
            }
        }

        return "impossible";
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
    }

    static class Blinker {
        int n;
        int d;
        int[] t;
        int now;

        Blinker(int n, int d, int[] t) {
            this.n = n;
            this.d = d;
            this.t = t;
            now = 1;
        }

        void timeGo() {
            now++;
            if (now > t[d]) {
                now = 1;
                d++;
                d %= 2;
            }
        }

        boolean go(int d) {
            return d%2==this.d;
        }
    }
}