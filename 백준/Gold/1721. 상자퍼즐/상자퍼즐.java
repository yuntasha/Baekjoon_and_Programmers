import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
일단 조건을 생각해보자 이건 백트래킹이라고 생각해보자
탐색방법
순서대로 넣으며 해당 박스를 돌려가며 가능한 부분으로 고정하고 다음 것으로 넘긴다.
조건
0이 있는 위치로 꼭지점, 평면이 들어갈 수 있는 조건이 성립된다
꼭지점이 가능한 애로 찾아졌다면 그것과 연결할 수 있는 평면만 다음꺼로 찾아간다

기본은 DFS로 시작하고 위, 우, 아래, 좌 순서라고 생각한다.
0이 반드시 있어야할 위치를 미리 저장해서 찾아둔다.
돌리는 값을 0~3까지 탐색하여 돌리고 해당 인덱스는 그냥 (n+i)%4로 탐색한다.
 */

public class Main {

    static int[][] CHK = {{-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] boxes = new int[N*N+1][];

        for (int i=0; i<N*N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int top = Integer.parseInt(st.nextToken());
            int up = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int down = Integer.parseInt(st.nextToken());

            boxes[top] = new int[]{up, right, left, down};
        }

        System.out.println(solution(N, boxes));
    }


    static String solution(int N, int[][] boxes) {
        int[][] boxNum = new int[N][N];
        int[][] r = new int[N][N];
        if (!dfs(N, boxes, 0, boxNum, r, new boolean[N*N+1])) throw new NoSuchElementException();

        StringBuilder box = new StringBuilder();
        StringBuilder rot = new StringBuilder();

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                box.append(boxNum[i][j]).append(" ");
                rot.append(r[i][j]).append(" ");
            }
            box.append("\n");
            rot.append("\n");
        }

        return box.toString() + rot.toString();
    }

    static boolean dfs(int N, int[][] boxes, int now, int[][] boxNum, int[][] r, boolean[] visited) {
        if (N * N == now) return true;

        for (int i=1; i<=N * N; i++) {
            int x = now/N;
            int y = now%N;

            if (visited[i]) continue;
            visited[i] = true;

            Loop : for (int ro = 0; ro < 4; ro++) {
                for (int p = 0; p < 4; p++) {
                    if (getIdx(p, ro) == 0) {
                        if (isIn(x-1, y, N) &&
                                boxes[boxNum[x-1][y]][(6 - r[x-1][y]) & 3] != boxes[i][p])
                            continue Loop; // 2의 위치의 원래 위치 2, 1, 0, 3
                        if (x == 0 && boxes[i][p] > 0) continue Loop;
                    }
                    if (getIdx(p, ro) == 1) {
                        if (y == N - 1 && boxes[i][p] > 0) continue Loop;
                    }
                    if (getIdx(p, ro) == 2) {
                        if (x == N - 1 && boxes[i][p] > 0) continue Loop;
                    }
                    if (getIdx(p, ro) == 3) {
                        if (isIn(x, y-1, N) &&
                                boxes[boxNum[x][y-1]][(5 - r[x][y-1]) & 3] != boxes[i][p]) continue Loop; // 1의 위치의 원래 위치 1, 0, 3, 2)
                        if (y == 0 && boxes[i][p] > 0) continue Loop;
                    }
                }

                r[x][y] = ro;
                boxNum[x][y] = i;
                if (dfs(N, boxes, now + 1, boxNum, r, visited)) {
                    return true;
                }
            }

            visited[i] = false;
        }

        return false;
    }

    static boolean isIn(int x, int y, int N) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static int getIdx(int p, int ro) {
        return (p + ro + 4) & 3;
    }
}