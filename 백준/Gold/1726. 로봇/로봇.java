import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
왼쪽위가 1, 1임
밑으로 내려가는게 2, 1
오른쪽이 1, 2

걍 맞춰주자

0, 1 - 1
0, -1 - 2
-1, 0 - 3
1, 0 - 4

visited[세로][가로][방향] [N+1][M+1][5]로 만듦
탐색 방법
그냥 할수있는거로 촤자작 BFS
시작을 1로 설정하고 마지막에 1빼주자
오, 왼, 현재방향 쭉 달리기
 */

public class Main {

    static int[] dx = {0, 0, 0, 1, -1};
    static int[] dy = {0, 1, -1, 0, 0};
    static final int RIGHT = 1;
    static final int LEFT = 2;
    static final int DOWN = 3;
    static final int UP = 4;
    static int N;
    static int M;
    static int[][][] result;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][];

        for (int n = 0; n < N; n++) {
            map[n] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Node start = new Node(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        Node end = new Node(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());

        System.out.println(solution(start, end));
    }

    public static int solution(Node start, Node end) {
        result = new int[N][M][5];
        result[start.x][start.y][start.d] = 1;

        ArrayDeque<Node> q = new ArrayDeque<>();

        q.add(start);

        while (!q.isEmpty()) {
            Node now = q.remove();
            int nextCount = getResult(now) + 1;

            Node next = turnRight(now);
            if (getResult(next) == 0) {
                setResult(next, nextCount);
                q.add(next);
            }

            next = turnLeft(now);
            if (getResult(next) == 0) {
                setResult(next, nextCount);
                q.add(next);
            }

            next = now;
            int i = 0;
            while ((next = go(next)).isIn() && !next.isBlock() && i < 3) {
                i++;
                if (getResult(next) > 0) continue;
                setResult(next, nextCount);
                q.add(next);
            }
        }

        return getResult(end) - 1;
    }

    static int getResult(Node node) {
        return result[node.x][node.y][node.d];
    }

    static void setResult(Node node, int n) {
        result[node.x][node.y][node.d] = n;
    }

    static Node turnRight(Node node) {
        int d;
        switch (node.d) {
            case RIGHT:
                d = DOWN;
                break;
            case LEFT:
                d = UP;
                break;
            case DOWN:
                d = LEFT;
                break;
            case UP:
                d = RIGHT;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return new Node(node.x, node.y, d);
    }

    static Node turnLeft(Node node) {
        int d;
        switch (node.d) {
            case RIGHT:
                d = UP;
                break;
            case LEFT:
                d = DOWN;
                break;
            case DOWN:
                d = RIGHT;
                break;
            case UP:
                d = LEFT;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return new Node(node.x, node.y, d);
    }

    static Node go(Node node) {
        return new Node(node.x + dx[node.d], node.y + dy[node.d], node.d);
    }

    public static class Node {
        int x;
        int y;
        int d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        Node(int[] input) {
            this.x = input[0] - 1;
            this.y = input[1] - 1;
            this.d = input[2];
        }


        boolean isIn() {
            return 0 <= x && x < N && 0 <= y && y < M;
        }

        boolean isBlock() {
            return map[x][y] == 1;
        }
    }
}