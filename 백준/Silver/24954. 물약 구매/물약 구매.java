/*
모든 순서 전부 순찰하고 끝
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] prices = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] sales = new int[N][N];

        for (int i = 0; i < N; i++) {
            int M = Integer.parseInt(bf.readLine());

            for (int j = 0; j < M; j++) {
                StringTokenizer input = new StringTokenizer(bf.readLine());

                sales[i][Integer.parseInt(input.nextToken()) - 1] += Integer.parseInt(input.nextToken());
            }
        }

        System.out.println(solution(N, prices, sales));
    }

    public static int solution(int N, int[] prices, int[][] sales) {
        return find(0, 0, new int[N], N, prices, sales);
    }

    public static int find(int n, int bits, int[] seq, int N, int[] prices, int[][] sales) {
        if (n == N) {
            return getP(seq, N, prices, sales);
        }

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            if ((bits & (1 << i)) > 0) continue;
            seq[n] = i;
            result = Math.min(result, find(n + 1, bits | (1 << i), seq, N, prices, sales));
        }

        return result;
    }

    public static int getP(int[] seq, int N, int[] prices, int[][] sales) {
        int[] nowPrices = new int[N];

        for (int i = 0; i < N; i++) {
            nowPrices[i] = prices[i];
        }

        int result = 0;

        for (int s : seq) {
            result += nowPrices[s];

            for (int i = 0; i < N; i++) {
                nowPrices[i] = Math.max(nowPrices[i] - sales[s][i], 1);
            }
        }

        return result;
    }
}