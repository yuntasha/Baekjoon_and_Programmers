/*
log2(N)
10만
100_000;
17
170만
170만
2번
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        Seg seg = new Seg(N);

        for (int i = 1; i <= N; i++) {
            seg.insert(Long.parseLong(bf.readLine()), i);
        }

        StringBuilder output = new StringBuilder();

        long[] ans = new long[2];

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(input.nextToken());
            int e = Integer.parseInt(input.nextToken());

            seg.query(s, e, ans);

            output.append(ans[0]).append(" ").append(ans[1]).append("\n");
        }

        System.out.print(output);
    }

    static class Seg {
        int N;
        long[][] nodes; // min max

        public Seg(int n) {
            this.N = n;
            this.nodes = new long[2][n << 2];
        }

        public void insert(long n, int idx) {
            find(1, 1, N, n, idx);
        }

        public void find(int now, int s, int e, long n, int idx) {
            if (s == e) {
                nodes[0][now] = nodes[1][now] = n;
                return;
            }

            int m = (s + e) >> 1;

            if (s <= idx && idx <= m) {
                find(now << 1, s, m, n, idx);
                if (nodes[0][now] == 0) nodes[0][now] = nodes[0][now << 1];
                else nodes[0][now] = Math.min(nodes[0][now], nodes[0][now << 1]);
                nodes[1][now] = Math.max(nodes[1][now], nodes[1][now << 1]);
            } else {
                find((now << 1 )+ 1, m + 1, e, n, idx);
                if (nodes[0][now] == 0) nodes[0][now] = nodes[0][(now << 1) + 1];
                else nodes[0][now] = Math.min(nodes[0][now], nodes[0][(now << 1) + 1]);
                nodes[1][now] = Math.max(nodes[1][now], nodes[1][(now << 1) + 1]);
            }
        }

        public void query(int s, int e, long[] ans) {
            ans[0] = 1_000_000_000;
            ans[1] = 0;

            qFind(1, 1, N, s, e, ans);
        }

        public void qFind(int now, int s, int e, int qs, int qe, long[] ans) {
            if (isOut(s, e, qs, qe)) return;
            if (isIn(s, e, qs, qe)) {
                ans[0] = Math.min(ans[0], nodes[0][now]);
                ans[1] = Math.max(ans[1], nodes[1][now]);
                return;
            }
            int m = (s + e) >> 1;
            qFind(now << 1, s, m, qs, qe, ans);
            qFind((now << 1) + 1, m + 1, e, qs, qe, ans);
        }

        public boolean isIn(int s, int e, int qs, int qe) {
            return qs <= s && e <= qe;
        }

        public boolean isOut(int s, int e, int qs, int qe) {
            return e < qs || qe < s;
        }
    }
}
