/*
그냥 BFS로 각 방을 찾아주자
그럼 1, 2는 자연스럽게 해결이 될거야
그 다음 그냥 모든 노드를 탐색해서 2차원 불 배열로 처리하자
그 다음에 각 배열 탐색으로 근처인 애들 다 처리
 */

import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int M = Integer.parseInt(input.nextToken());
        int N = Integer.parseInt(input.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        System.out.print(solution(N, M, map));
    }

    public static String solution(int N, int M, int[][] map) {
        List<Integer> areas = new ArrayList<>();

        int[][] room = new int[N][M];

        for (int[] r : room) Arrays.fill(r, -1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (room[i][j] >= 0) continue;
                room[i][j] = areas.size();
                areas.add(find(i, j, N, M, map, room));
            }
        }

        boolean[][] connect = new boolean[areas.size()][areas.size()];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d = 0; d < 4; d++) {
                    int x = i + dx[d];
                    int y = j + dy[d];
                    if (!isIn(x, y, N, M)) continue;
                    connect[room[i][j]][room[x][y]] = connect[room[x][y]][room[i][j]] = true;
                }
            }
        }

        int amax = 0;
        int max = 0;

        for (int i = 0; i < areas.size(); i++) {
            amax = Math.max(amax, areas.get(i));
            for (int j = i + 1; j < areas.size(); j++) {
                if (connect[i][j]) max = Math.max(max, areas.get(i) + areas.get(j));
            }
        }

        return areas.size() + "\n" + amax + "\n" + max;
    }

    public static int find(int n, int m, int N, int M, int[][] map, int[][] room) {
        int result = 1;

        for (int d = 0; d < 4; d++) {
            int x = n + dx[d];
            int y = m + dy[d];

            if (!isIn(x, y, N, M)) continue;
            if (room[x][y] >= 0) continue;
            if ((map[n][m] & (1 << d)) > 0) continue;

            room[x][y] = room[n][m];
            result += find(x, y, N, M, map, room);
        }

        return result;
    }

    public static boolean isIn(int n, int m, int N, int M) {
        return 0 <= n && n < N && 0 <= m && m < M;
    }
}