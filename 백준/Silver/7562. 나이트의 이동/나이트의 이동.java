import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
그냥 전체 위치에서 갈 수 있는 최장거리 뽑아서 가져오자
 */

public class Main {

    static int[] dx = {1, 1, 2, 2, -1, -1, -2, -2};
    static int[] dy = {2, -2, 1, -1, 2, -2, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        StringBuilder output = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int L = Integer.parseInt(bf.readLine());

            StringTokenizer input = new StringTokenizer(bf.readLine());
            Node start = new Node(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()));

            input = new StringTokenizer(bf.readLine());
            Node end = new Node(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()));

            output.append("\n").append(solution(L, start, end));
        }

        System.out.println(output.substring(1));
    }

    public static int solution(int L, Node start, Node end) {
        int[][] result = new int[L][L];

        result[start.x][start.y] = 1;

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(start);

        while (!q.isEmpty()) {
            Node now = q.remove();

            for (int d = 0; d < 8; d++) {
                int x = now.x + dx[d];
                int y = now.y + dy[d];

                if (!isIn(x, y, L)) continue;
                if (result[x][y] > 0) continue;
                result[x][y] = result[now.x][now.y] + 1;
                q.add(new Node(x, y));
            }
        }

        return result[end.x][end.y] - 1;
    }

    public static boolean isIn(int x, int y, int L) {
        return 0 <= x && x < L && 0 <= y && y < L;
    }

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}