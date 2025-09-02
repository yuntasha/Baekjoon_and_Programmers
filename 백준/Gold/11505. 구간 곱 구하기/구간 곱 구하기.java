import java.io.*;
import java.util.*;

public class Main {

    static long MAX = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        M += Integer.parseInt(input.nextToken());

        Seg seg = new Seg(N);

        for (int i = 1; i <= N; i++) {
            seg.insert(Long.parseLong(bf.readLine()), i);
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            int c = Integer.parseInt(input.nextToken());

            if (a == 1) seg.insert(c, b);
            else output.append(seg.query(b, c)).append("\n");
        }

        System.out.print(output);
    }

    static class Seg {
        int N;
        long[] nodes; // 누적곱

        public Seg(int n) {
            this.N = n;
            this.nodes = new long[n << 2];
        }

        public void insert(long n, int idx) {
            find(1, 1, N, n, idx);
        }

        public void find(int now, int s, int e, long n, int idx) {
            if (s == e) {
                nodes[now] = n;
                return;
            }

            int m = (s + e) >> 1;

            if (s <= idx && idx <= m) {
                find(now << 1, s, m, n, idx);
            } else {
                find((now << 1 )+ 1, m + 1, e, n, idx);
            }

            nodes[now] = nodes[now << 1] * nodes[(now << 1) + 1] % MAX;
        }

        public long query(int s, int e) {
            return qFind(1, 1, N, s, e);
        }

        public long qFind(int now, int s, int e, int qs, int qe) {
            if (isOut(s, e, qs, qe)) return -1;
            if (isIn(s, e, qs, qe)) {
                return nodes[now];
            }

            int m = (s + e) >> 1;
            long left = qFind(now << 1, s, m, qs, qe);
            long right = qFind((now << 1) + 1, m + 1, e, qs, qe);

            if (left == -1) return right;
            if (right == -1) return left;
            return left * right % MAX;
        }

        public boolean isIn(int s, int e, int qs, int qe) {
            return qs <= s && e <= qe;
        }

        public boolean isOut(int s, int e, int qs, int qe) {
            return e < qs || qe < s;
        }
    }
}
