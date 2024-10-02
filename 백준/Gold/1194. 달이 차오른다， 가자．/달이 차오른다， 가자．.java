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

        var map = new char[N][M];

        for (int i=0; i<N; i++) {
            map[i] = bf.readLine().toCharArray();
        }

        System.out.println(solution(N, M, map));
    }

    static int solution(int N, int M, char[][] map) {
        var q = new LinkedList<Node>();
        var visited = new int[N][M][1<<6];
        var dx = new int[]{0, 1, 0, -1};
        var dy = new int[]{1, 0, -1, 0};
        var result = 0;

        Loop: for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] == '0') {
                    q.add(new Node(i, j, 0));
                    visited[i][j][0] = 1;
                    break Loop;
                }
            }
        }

        while (!q.isEmpty()) {
            var now = q.remove();
            for (int d=0; d<4; d++) {
                var x = now.x + dx[d];
                var y = now.y + dy[d];
                var key = now.key;

                if (x<0 || x>=N || y<0 || y>=M) continue;

                if (map[x][y] >= 'a' && map[x][y] <= 'f') {
                    key = key | (1<<(map[x][y]-'a'));
                    if (visited[x][y][key] > 0) continue;
                } else if (map[x][y] >= 'A' && map[x][y] <= 'F') {
                    if ((now.key & (1<<map[x][y]-'A'))==0) continue;
                } else if (map[x][y]=='1') {
                    return visited[now.x][now.y][now.key];
                } else if (map[x][y]=='#') continue;

                if (visited[x][y][key]>0) continue;

                visited[x][y][key] = visited[now.x][now.y][now.key] + 1;
                q.add(new Node(x, y, key));
            }
        }

        return -1;
    }

    static class Node {
        int x;
        int y;
        int key;

        Node(int x, int y, int key) {
            this.x = x;
            this.y = y;
            this.key = key;
        }
    }
}