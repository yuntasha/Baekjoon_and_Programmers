/*
한 선 맞네
그럼 시작점과 끝점 이렇게 만들자
시작점 빠른애부터 처리
앞에서부터 탐색하면서
시작이 설정 끝보다 짧은 경우 더 큰 끝으로 업데이트
시작이 더 큰경우 기존 거리 구해서 결과에 더해주고 새로운 시작

30 * 30 * 30 * 30
900
810_000
int int int int int
40 12
42_120_000
최소 소스는 30 30 30 30 * -100
-12_000 * 90해도 크게 안바뀜
혹시 모르니까 long으로 받아서 저장하자
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int MIN = -1200000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        long[][] re = getSum(N, map);

        System.out.println(solution(N, M, map));
    }

    static String solution(int N, int M, int[][] map) {
        List<Ring> result = new ArrayList<>();
        boolean[][] visited = new boolean[N][N];
        long[][] sum = getSum(N, map);

        for (int i = 0; i < M; i++) {
            result.add(getRing(N, map, sum, visited));
            if (result.get(result.size() - 1).x1 == -1) return "0";
            setMap(result.get(result.size() - 1), visited);
//            System.out.println(Arrays.stream(map).map(arr -> Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" "))).collect(Collectors.joining("\n","\n", "\n")));
        }

        return result.stream().map(Ring::toString).collect(Collectors.joining("\n"));
    }

    static void setMap(Ring ring, boolean[][] map) {
        for (int i = ring.x1; i <= ring.x2; i++) {
            map[i][ring.y1] = true;
            map[i][ring.y2] = true;
        }

        for (int i = ring.y1; i <= ring.y2; i++) {
            map[ring.x1][i] = true;
            map[ring.x2][i] = true;
        }
    }

    static Ring getRing(int N, int[][] map, long[][] sum, boolean[][] visited) {
        Ring result = new Ring(-1, -1, -1, -1, MIN);

        for (int x1 = 0; x1 < N; x1++) {
            for (int y1 = 0; y1 < N; y1++) {
                for (int x2 = x1 + 2; x2 < N; x2++) {
                    for (int y2 = y1 + 2; y2 < N; y2++) {
                        if (isVisited(x1, y1, x2, y2, visited)) continue;
                        result.update(x1, y1, x2, y2, getSum(x1, y1, x2, y2, sum) - getSum(x1 + 1, y1 + 1, x2 - 1, y2 - 1, sum));
                    }
                }
            }
        }

        return result;
    }

    static boolean isVisited(int x1, int y1, int x2, int y2, boolean[][] visited) {
        for (int i = x1; i <= x2; i++) {
            if (visited[i][y1]) return true;
            if (visited[i][y2]) return true;
        }

        for (int i = y1; i <= y2; i++) {
            if (visited[x1][i]) return true;
            if (visited[x2][i]) return true;
        }
        return false;
    }

    static long getSum(int x1, int y1, int x2, int y2, long[][] sum) {
        long result = sum[x2][y2];
        if (x1 > 0) {
            result -= sum[x1 - 1][y2];
        }
        if (y1 > 0) {
            result -= sum[x2][y1 - 1];
        }
        if (x1 > 0 && y1 > 0) {
            result += sum[x1 - 1][y1 - 1];
        }

        return result;
    }

    static long[][] getSum(int N, int[][] map) {
        long[][] result = new long[N][N];

        result[0][0] = map[0][0];

        for (int i = 1; i < N; i++) {
            result[0][i] = result[0][i - 1] + map[0][i];
        }

        for (int i = 1; i < N; i++) {
            result[i][0] = map[i][0];

            for (int j = 1; j < N; j++) {
                result[i][j] = result[i][j - 1] + map[i][j];
            }

            for (int j = 0; j < N; j++) {
                result[i][j] += result[i - 1][j];
            }
        }

        return result;
    }

    static class Ring {
        int x1;
        int y1;
        int x2;
        int y2;
        long v;

        public Ring(int x1, int y1, int x2, int y2, long v) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.v = v;
        }

        void update(int x1, int y1, int x2, int y2, long v) {
            if (this.v >= v) return;

            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.v = v;
        }

        @Override
        public String toString() {
            return v + " " + (x1 + 1) + " " + (y1 + 1) + " " + (x2 + 1) + " " + (y2 + 1);
        }
    }
}