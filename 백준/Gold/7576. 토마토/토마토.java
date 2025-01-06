import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1번 노드와 연결된 모든 노드의 개수
BFS, DFS 둘 다 가능
DFS로 풀자
재귀로 풀게요
*/

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(bf.readLine());
            for (int n = 0; n < N; n++) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(N, M, map));
    }

    static int solution(int N, int M, int[][] map) {
        LinkedList<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int result = 0;
        int count = 0;

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (map[n][m] == 1) {
                    q.add(new Node(n, m, 0));
                    visited[n][m] = true;
                }
                if (map[n][m] == -1) {
                    count++;
                }
            }
        }

        while (!q.isEmpty()) {
            Node now = q.remove();
            result = now.v;
            count++;

            for (int d = 0; d < 4; d++) {
                int n = now.n + dx[d];
                int m = now.m + dy[d];

                if (!isIn(n, m, N, M)) continue;
                if (visited[n][m]) continue;
                if (map[n][m] == -1) continue;

                visited[n][m] = true;
                q.add(new Node(n, m, now.v + 1));
            }
        }

        if (count == N*M) {
            return result;
        } else {
            return -1;
        }
    }

    static boolean isIn(int n, int m, int N, int M) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }

    static class Node {
        int n;
        int m;
        int v;

        public Node(int n, int m, int v) {
            this.n = n;
            this.m = m;
            this.v = v;
        }
    }
}