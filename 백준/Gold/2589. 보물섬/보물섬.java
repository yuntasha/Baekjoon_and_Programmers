import java.io.*;
import java.util.*;


public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int r;
    static int c;
    static boolean[][] visited;
    static int[][] mapp;
    static int maxTime = 0;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        mapp = new int[r][c];
        for (int i=0; i<r; i++) {

            String[] row = br.readLine().split("");

            for (int j=0; j<c; j++) {

                String s = row[j];
                mapp[i][j] = s.equals("W") ? 0 : 1;

            }

        }

        // bfs
        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                if (mapp[i][j] == 0) continue;
                visited = new boolean[r][c];
                bfs(new Node(i, j, 0));

            }
        }

        // result
        System.out.print(maxTime);

    }



    static void bfs(Node stt) {

        Deque<Node> q = new ArrayDeque<>();
        q.add(stt);
        visited[stt.r][stt.c] = true;

        while (!q.isEmpty()) {

            Node cur = q.pop();

            // 상하좌우 탐색
            for (int i=0; i<4; i++) {

                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (!(0<=nr && nr<r) || !(0<=nc && nc<c)) continue;
                if (visited[nr][nc]) continue;
                if (mapp[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                q.add(new Node(nr, nc, cur.t+1));
                maxTime = Math.max(maxTime, cur.t+1);

            }
        }
    }



    static class Node {

        int r;
        int c;
        int t;

        Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }

    }



}