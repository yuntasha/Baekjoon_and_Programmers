import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = NM[0];
        M = NM[1];

        var arr = new char[N][];

        for (int i=0; i<N; i++) {
            arr[i] = bf.readLine().toCharArray();
        }

        System.out.println(Arrays.stream(solution(arr)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    static int[] solution(char[][] arr) {
        var visited = new int[N][M][2];
        var q = new LinkedList<Node>();

        for (int[][] a: visited) {
            for (int[] b:a) {
                Arrays.fill(b, Integer.MAX_VALUE);
            }
        }

        FindLoop : for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (arr[i][j] == 'S') {
                    q.add(new Node(i, j));
                    visited[i][j][0] = 0;
                    visited[i][j][1] = 0;
                    break FindLoop;
                }
            }
        }

        int[] result = new int[0];


        while (!q.isEmpty()) {
            var now = q.remove();

            for (int d=0; d<4; d++) {
                var x = now.x + dx[d];
                var y = now.y + dy[d];

                if (!isIn(x,y)) continue;

                switch (arr[x][y]) {
                    case 'F':
                        result = visited[x][y];
                        if (isGo(x, y, now.x, now.y, visited)) {
                            visited[x][y][0] = visited[now.x][now.y][0];
                            visited[x][y][1] = visited[now.x][now.y][1];
                        }
                        break;
                    case '.':
                        if (nearG(x, y, arr)) {
                            visited[now.x][now.y][1]++;
                            if (isGo(x, y, now.x, now.y, visited)) {
                                visited[x][y][0] = visited[now.x][now.y][0];
                                visited[x][y][1] = visited[now.x][now.y][1];
                                q.add(new Node(x, y));
                            }
                            visited[now.x][now.y][1]--;
                        } else {
                            if (isGo(x, y, now.x, now.y, visited)) {
                                visited[x][y][0] = visited[now.x][now.y][0];
                                visited[x][y][1] = visited[now.x][now.y][1];
                                q.add(new Node(x, y));
                            }
                        }
                        break;
                    case 'g':
                        visited[now.x][now.y][0]++;
                        if (isGo(x, y, now.x, now.y, visited)) {
                            visited[x][y][0] = visited[now.x][now.y][0];
                            visited[x][y][1] = visited[now.x][now.y][1];
                            q.add(new Node(x, y));
                        }
                        visited[now.x][now.y][0]--;
                        break;
                }
            }
        }

        return result;
    }

    static boolean isGo(int atX, int atY, int fromX, int fromY, int[][][] visited) {
        return visited[atX][atY][0] > visited[fromX][fromY][0] ||
                (visited[atX][atY][0] == visited[fromX][fromY][0] &&
                visited[atX][atY][1] > visited[fromX][fromY][1]);
    }

    static boolean isIn(int x, int y) {
        return !(x<0 || N<=x || y<0 || M<=y);
    }

    static boolean nearG(int x, int y, char[][] arr) {
        for (int dd=0; dd<4; dd++) {
            var ddx = x + dx[dd];
            var ddy = y + dy[dd];

            if (ddx<0 || N<=ddx || ddy<0 || M<=ddy) continue;

            if (arr[ddx][ddy] == 'g') return true;
        }

        return false;
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}