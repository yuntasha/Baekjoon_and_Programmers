import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var map = new int[N][M];
        var start = new int[2];

        var idx = 1;

        for (int i=0; i<N; i++) {
            var now = bf.readLine().toCharArray();
            for (int j=0; j<now.length; j++) {
                switch (now[j]) {
                    case '#':
                        map[i][j]= -1;
                        break;
                    case '.':
                        map[i][j] = 0;
                        break;
                    case 'C':
                        map[i][j] = idx++;
                        break;
                    case 'S':
                        map[i][j] = 0;
                        start = new int[]{i, j};
                        break;
                }
            }
        }



        System.out.println(solution(N, M, map, start));
    }



    static int solution(int N, int M, int[][] map, int[] start) {
        var visited = new boolean[N][M][4][2][2];
        var q = new LinkedList<Node>();
        var dx = new int[]{0, 1, 0, -1};
        var dy = new int[]{1, 0, -1, 0};

        q.add(new Node(start[0], start[1], -1, 0, 0, 0));

        while (!q.isEmpty()) {
            var now = q.remove();
            for (int i=0; i<4; i++) {
                if (i==now.d) continue;
                var x = now.n + dx[i];
                var y = now.m + dy[i];
                var f = now.f;
                var s = now.s;
                if (x<0 || x>=N || y<0 || y>=M) continue;
                if (map[x][y]<0) continue;
                if (map[x][y]==1) f = 1;
                if (map[x][y]==2) s = 1;
                if (f==1 && s==1) return now.result+1;
                if (visited[x][y][i][f][s]) continue;
                visited[x][y][i][f][s] = true;
                q.add(new Node(x, y, i, f, s, now.result+1));
            }
        }

        return -1;
    }


    static class Node {
        int n;
        int m;
        int d;
        int f;
        int s;
        int result;

        public Node(int n, int m, int d, int f, int s, int result) {
            this.n = n;
            this.m = m;
            this.d = d;
            this.f = f;
            this.s = s;
            this.result = result;
        }
    }
}