/*
250보다 작거나 같은 음이 아닌 정수
질문 K개 줌
B * B 크기의 부분 행렬의 최댓값, 최솟값의 차이

250이고 B는 뭐 알아서
각 위치에서 최대 최소로 그냥 구해버리자


 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int B = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        int[][][] dp = solution(N, B, map);

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < K; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken()) - 1;
            int b = Integer.parseInt(input.nextToken()) - 1;

            output.append("\n").append(dp[a][b][1] - dp[a][b][0]);
        }

        System.out.println(output.substring(1));
    }

    static int[][][] solution(int N, int B, int[][] map) {
        int[][][] result = new int[N][N][2];

        for (int i = 0; i <= N - B; i++) {
            for (int j = 0; j <= N - B; j++) {
                result[i][j][0] = Integer.MAX_VALUE;
                result[i][j][1] = Integer.MIN_VALUE;
                for (int bi = 0; bi < B; bi++) {
                    for (int bj = 0; bj < B; bj++) {
                        result[i][j][0] = Math.min(result[i][j][0], map[i + bi][j + bj]);
                        result[i][j][1] = Math.max(result[i][j][1], map[i + bi][j + bj]);
                    }
                }
            }
        }

        return result;
    }
}