/*
음
일단 울타리가 평행하게 지나가거나 끝점만 닿는 경우 상관안해도됨
그럼 ccw * ccw 가 음수인 경우만 생각하면 될듯?
그 다음에 [깊이][마지막 점] 이렇게 dp
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        int N = read();
        int K = read();
        int F = read();
        long X = read();
        long Y = read();

        long[][] pos = new long[N][2];

        for (int i = 0; i < N; i++) {
            pos[i][0] = read();
            pos[i][1] = read();
        }

        List<Fence> fences = new ArrayList<>();

        for (int i = 0; i < F; i++) {
            fences.add(new Fence(read(), read(), read(), read(), read()));
        }

        System.out.println(solution(N, K, F, X, Y, pos, fences));
    }

    public static String solution(int N, int K, int F, long X, long Y, long[][] pos, List<Fence> fences) {
        double[][] percents = getH(N, pos, fences);

        double[][] dp = new double[K][N];

        for (int dest = 0; dest < N; dest++) {
            dp[0][dest] = getP(X, Y, pos[dest][0], pos[dest][1], fences);
        }

        for (int k = 1; k < K; k++) {
            for (int start = 0; start < N; start++) {
                for (int dest = 0; dest < N; dest++) {
                    if (start == dest) continue;
                    dp[k][dest] = Math.max(dp[k][dest], dp[k - 1][start] * percents[start][dest]);
                }
            }
        }

        double result = 0;

        for (int last = 0; last < N; last++) {
            result = Math.max(result, dp[K - 1][last] * dp[0][last]);
        }

        return String.format("%.4e", result);
    }

    public static double[][] getH(int N, long[][] pos, List<Fence> fences) {
        double[][] result = new double[N][N];

        for (int i = 0; i < N; i++) Arrays.fill(result[i], 1);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                result[i][j] = result[j][i] = getP(pos[i][0], pos[i][1], pos[j][0], pos[j][1], fences);
            }
        }

        return result;
    }

    public static double getP(long x1, long y1, long x2, long y2, List<Fence> fences) {
        double result = 1;
        for (Fence f : fences) {
            if (isCross(x1, y1, x2, y2, f.x1, f.y1, f.x2, f.y2)) {
                result /= (double) f.h;
            }
        }

        return result;
    }

    public static boolean isCross(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        return ccw(x1, y1, x2, y2, x3, y3) * ccw(x1, y1, x2, y2, x4, y4) < 0 && ccw(x3, y3, x4, y4, x1, y1) * ccw(x3, y3, x4, y4, x2, y2) < 0;
    }

    public static long ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        return (x1 * y2 + x2 * y3 + x3 * y1) - (x1 * y3 + x2 * y1 + x3 * y2);
    }

    public static class Fence {
        long x1;
        long y1;
        long x2;
        long y2;
        long h;

        public Fence(int x1, int y1, int x2, int y2, int h) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.h = h;
        }
    }

    public static int read() throws IOException {
        int n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}