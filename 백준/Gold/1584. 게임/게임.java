import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
1. 죽음의 구역 - 못들어감
2. 위험한 구역 - 생명력 1 깎임
3. 안전 구역 - 그냥 들어갈 수 있음

기본 0, 0에서 500, 500으로 가야함
0, 0이 어떤 구역이든 신경 안씀


입력 :
구역으로 나타남
구역이 겹치면 더 위험한 지역으로 정해짐
중첩은 안됨

출력 :
필요한 생명력
갈 수 없으면 -1

로직
pq로 완탐
시작을 1로 두고 완탐

 */

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        byte[][] map = new byte[501][501];

        int N = Integer.parseInt(bf.readLine());

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int i = Math.min(x1, x2); i <= Math.max(x1, x2) && i <= 500; i++) {
                for (int j = Math.min(y1, y2); j <= Math.max(y1, y2) && j <= 500; j++) {
                    map[i][j] = 1;
                }
            }
        }

        N = Integer.parseInt(bf.readLine());

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int i = Math.min(x1, x2); i <= Math.max(x1, x2) && i <= 500; i++) {
                for (int j = Math.min(y1, y2); j <= Math.max(y1, y2) && j <= 500; j++) {
                    map[i][j] = 2;
                }
            }
        }

        System.out.println(solution(map));
    }

    static int solution(byte[][] map) {
        boolean[][] visited = new boolean[501][501];

        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0));

        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.remove();

            for (int d = 0; d < 4; d++) {
                int x = now.x + dx[d];
                int y = now.y + dy[d];

                if (!isIn(x, y)) continue;
                if (visited[x][y]) continue;
                if (map[x][y] == 2) continue;
                visited[x][y] = true;

                if (x==500 && y==500) return now.v+map[x][y];

                if (map[x][y] == 1) {
                    q.add(new Node(x, y, now.v+1));
                } else {
                    q.addFirst(new Node(x, y, now.v));
                }
            }
        }
        
        return -1;
    }

    static boolean isIn(int x, int y) {
        return 0<=x && x<=500 && 0<=y && y<=500;
    }

    static class Node {
        int x;
        int y;
        int v;

        Node(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }

        int getV() {
            return this.v;
        }
    }
}