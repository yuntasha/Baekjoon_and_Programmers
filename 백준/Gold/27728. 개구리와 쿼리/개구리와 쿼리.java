/*
음 초반에 미리미리 다 세팅을 해두는게 좋을 것 같다
음음
일단 각 위치에서 오른쪽으로 갈때의 누적합을 구해두자
특정 위치 이상의 상태에서 가장 최소값을 넣어줘야할 것 같다
어차피 끝으로 갈 것이니까
누적합을 미리 구해둘 이유가 있나?
없음
그냥 오른쪽부터 더해주면서 각각의 값을 넣어주면 될 것 같다
뭔가 누적합보단 DP에 가까운 느낌인데

누적합도 필요하긴 하네... 같이 만들어주자
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int Q = Integer.parseInt(input.nextToken());

        int[][] map = new int[N][N];
        int[][] suffixSum = new int[N][N];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(input.nextToken());
        }

        int[][] dp = getDP(N, map, suffixSum);

        StringBuilder output = new StringBuilder();

        for (int q = 0; q < Q; q++) {
            input = new StringTokenizer(bf.readLine());
            output.append(query(Integer.parseInt(input.nextToken()) - 1, Integer.parseInt(input.nextToken()) - 1, Integer.parseInt(input.nextToken()), N, dp, suffixSum)).append("\n");
        }

        System.out.print(output);
    }

    public static int[][] getDP(int N, int[][] map, int[][] suffixSum) {
        int[][] result = new int[N][N];

        int now = 0;
        for (int j = N - 1; j >= 0; j--) {
            now += map[0][j];
            result[0][j] = now;
            suffixSum[0][j] = now;
        }

        for (int i = 1; i < N; i++) {
            now = 0;
            for (int j = N - 1; j >= 0; j--) {
                now += map[i][j];
                suffixSum[i][j] = now;
                result[i][j] = Math.min(result[i - 1][j], now);
            }
        }


        return result;
    }

    public static int query(int x, int y, int l, int N, int[][] dp, int[][] suffixSum) {
        int result = suffixSum[x][y];

        if (x - l < 0) return result;

        for (int j = y; j < N; j++) {
            result = Math.min(result, suffixSum[x][y] - suffixSum[x][j] + dp[x - l][j]);
        }

        return result;
    }
}