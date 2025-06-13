/*
1000 * 1000이잖아
그러면 1_000_000_000인데
개오바임
이거 카데인 알고리즘이랑 비슷하네
앞에부터 따라오는게 최대냐 아니면 위에서 바로 내려오는게 최대냐 이거로 dp 한번 더 엮어주는 문제
구간 합이라고 생각하면 편함
특정 위치에서 끝나는 구간합 중 최댓값 구하는 문제
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] map = new int[N][];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, M, map));
    }

    static int solution(int N, int M, int[][] map) {
        int[] result = new int[M];

        result[0] = map[0][0];

        for (int i = 1; i < M; i++) {
            result[i] = map[0][i] + result[i - 1];
        }

        for (int i = 1; i < N; i++) {
            int[] r = new int[M];
            r[0] = result[0] + map[i][0];
            for (int j = 1; j < M; j++) {
                r[j] = map[i][j] + Math.max(r[j - 1], result[j]);
            }

            int[] l = new int[M];
            l[M - 1] = result[M - 1] + map[i][M - 1];
            for (int j = M - 2; j >= 0; j--) {
                l[j] = map[i][j] + Math.max(l[j + 1], result[j]);
            }

            for (int j = 0; j < M; j++) {
                result[j] = Math.max(l[j], r[j]);
            }
        }

        return result[M - 1];
    }
}