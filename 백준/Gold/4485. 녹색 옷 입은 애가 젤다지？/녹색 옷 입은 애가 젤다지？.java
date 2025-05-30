import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
그냥 전체 위치에서 갈 수 있는 최장거리 뽑아서 가져오자
 */

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int t = 1;
        int N;

        StringBuilder output = new StringBuilder();

        while ((N = Integer.parseInt(bf.readLine())) > 0) {
            int[][] map = new int[N][];

            for (int i = 0; i < N; i++) {
                map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            // Problem 1: 20
            output.append("\nProblem ").append(t++).append(": ").append(solution(N, map));
        }

        System.out.println(output.substring(1));
    }

    public static int solution(int N, int[][] map) {
        int[][] result = new int[N][N];

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getV));

        pq.add(new Node(0, 0, 1 + map[0][0]));
        result[0][0] = 1 + map[0][0];

        while (!pq.isEmpty()) {
            Node now = pq.remove();

            for (int d = 0; d < 4; d++) {
                int x = now.x + dx[d];
                int y = now.y + dy[d];

                if (!isIn(x, y, N)) continue;
                if (result[x][y] > 0) continue;
                result[x][y] = now.v + map[x][y];
                pq.add(new Node(x, y, result[x][y]));
            }
        }

        return result[N - 1][N - 1] - 1;
    }

    public static boolean isIn(int x, int y, int N) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static class Node {
        int x;
        int y;
        int v;

        public Node(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }

        public int getV() {
            return v;
        }
    }
}