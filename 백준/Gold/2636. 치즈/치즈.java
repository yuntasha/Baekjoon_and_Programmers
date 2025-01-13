import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
0-1 BFS로 풀어보자
일단 가장자리는 항상 0인듯 함
0, 0에서 시작해서 전체 탐색으로 넣어보자
*/

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][];

        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, M, map));
    }

    static String solution(int N, int M, int[][] map) {
        ArrayDeque<Node> q = new ArrayDeque<>();

        boolean[][] visited = new boolean[N][M];
        int[][] result = new int[N][M];
        int max = 0;
        int count = 0;

        q.add(new Node(0, 0));
        visited[0][0] = false;

        while(!q.isEmpty()) {
            Node now = q.remove();
            if (max == result[now.x][now.y]) {
                if (map[now.x][now.y] == 1) {
                    count++;
                }
            } else {
                max = result[now.x][now.y];
                count = 1;
            }
            for (int d=0; d<4; d++) {
                int x = now.x + dx[d];
                int y = now.y + dy[d];

                if (!isIn(x, y, N, M)) continue;
                if (visited[x][y]) continue;

                visited[x][y] = true;

                if (map[x][y] == 1) {
                    q.add(new Node(x, y));
                    result[x][y] = result[now.x][now.y] + 1;
                } else {
                    q.addFirst(new Node(x, y));
                    result[x][y] = result[now.x][now.y];
                }
            }
        }
        if (max == 0) return "0\n0";
        return max + "\n" + count;
    }

    static boolean isIn(int x, int y, int N, int M) {
        return 0<=x && x<N && 0<=y && y<M;
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