/*
판다는 점점 더 높은 위치로 옮겨감
무지성 조회는 n * n * n * n = 500 ^ 4 = 62_500_000_000
근데 잘 생각해보면 각 점에서 움직일 수 있는 위치가 한정되어 있고 같은 위치 계속 움직임
따라서 가장 큰 위치부터 정렬해서 더 작은 애한테 자기 자신 + 1 해주면 옮긴 위치 업데이트 된다..!

대나무 양 최대 100만
최대 움직일 수 있는 수 250_000

전체 탐색 n * n + 정렬 n * n log n * n + 탐색 n * n = 1_849_485
*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int[] dx = new int[] {0, 1, 0, -1};
    static int[] dy = new int[] {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] map = new int[N][];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, map));
    }

    public static int solution(int N, int[][] map) {
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nodes.add(new Node(i, j, map[i][j]));
            }
        }

        nodes.sort(Comparator.comparingInt(Node::getV).reversed());

        int[][] dp = new int[N][N];

        for (Node now : nodes) {
            for (int d = 0; d < 4; d++) {
                int x = now.x + dx[d];
                int y = now.y + dy[d];

                if (!isIn(x, y, N)) continue;
//                System.out.println(Arrays.stream(dp).map(Arrays::toString).collect(Collectors.joining("\n")));
                if (map[now.x][now.y] <= map[x][y]) continue;

                dp[x][y] = Math.max(dp[x][y], dp[now.x][now.y] + 1);
            }
        }

        int result = 0;

        for (int  i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }

        return result + 1;
    }

    static boolean isIn(int x, int y, int N) {
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