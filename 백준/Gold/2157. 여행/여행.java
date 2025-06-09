/*
상하좌우로 가는거 만들고 !가 있으면 그냥 직진도 하고 좌우로도 움직이게 만들자
dxdy를 상좌하우로 만들어서 알맞게 만들고 암튼 그렇게 ㄱㄱ
한쪽 방향으로 끝까지 진입하고 거기서 !만나면 다음 방향과 위치 지정하고 출발
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        List<List<Road>> roads = new ArrayList<>();

        for (int i = 0; i <= N; i++) roads.add(new ArrayList<>());

        for (int i = 0; i < K; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            int c = Integer.parseInt(input.nextToken());

            if (a < b) {
                roads.get(a).add(new Road(b, c));
            }
        }

        System.out.println(solution(N, M, K, roads));
    }

    static int solution(int N, int M, int K, List<List<Road>> roads) {
        int[][] dp = new int[N + 1][M + 1];

        for (Road road : roads.get(1)) {
            dp[road.dest][2] = Math.max(dp[road.dest][2], road.cost);
        }

        for (int i = 2; i < N; i++) {
            for (int m = 0; m < M; m++) {
                if (dp[i][m] == 0) continue;
                for (Road road : roads.get(i)) {
                    dp[road.dest][m + 1] = Math.max(dp[road.dest][m + 1], dp[i][m] + road.cost);
                }
            }
        }

        int result = 0;

        for (int i = 0; i <= M; i++) {
            result = Math.max(result, dp[N][i]);
        }

        return result;
    }

    static class Road {
        int dest;
        int cost;

        public Road(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}