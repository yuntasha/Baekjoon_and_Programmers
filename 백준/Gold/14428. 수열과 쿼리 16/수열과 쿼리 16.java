/*
음 세그트리 그대로 만드는 이거 내부 클래스까지 만들어서 처리하면 깔끔할듯?
 */

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        Seg seg = new Seg(N);

        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++) {
            seg.insert(i, Integer.parseInt(input.nextToken()));
        }

        int M = Integer.parseInt(bf.readLine());

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            int type = Integer.parseInt(input.nextToken());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            if (type == 1) {
                seg.insert(a, b);
            } else {
                output.append(seg.search(a, b)).append("\n");
            }
        }

        System.out.print(output);
    }

    public static class Seg {

        int N;
        Node[] nodes;

        public Seg(int N) {
            this.N = N;
            nodes = new Node[N << 2];
        }

        public void insert(int idx, int value) {
            insertQuery(1, 1, N, idx, value);
        }

        private void insertQuery(int n, int s, int e, int idx, int value) {
            if (s == e) {
                nodes[n] = new Node(idx, value);
                return;
            }

            int m = (s + e) >> 1;
            if (idx <= m) insertQuery(n << 1, s, m, idx, value);
            else  insertQuery((n << 1) + 1, m + 1, e, idx, value);

            nodes[n] = Node.getSmaller(nodes[n << 1], nodes[(n << 1) + 1]);
        }

        public int search(int s, int e) {
            return searchQuery(1, 1, N, s, e).idx;
        }

        private Node searchQuery(int n, int s, int e, int qs, int qe) {
            if (isOut(s, e, qs, qe)) return null;
            if (isIn(s, e, qs, qe)) return nodes[n];

            int m = (s + e) >> 1;

            return Node.getSmaller(searchQuery(n << 1, s, m, qs, qe), searchQuery((n << 1) + 1, m + 1, e, qs, qe));
        }

        private boolean isIn(int s, int e, int qs, int qe) {
            return qs <= s && e <= qe;
        }

        private boolean isOut(int s, int e, int qs, int qe) {
            return e < qs || qe < s;
        }

        public static class Node {
            int idx;
            int v;

            public Node(int idx, int v) {
                this.idx = idx;
                this.v = v;
            }

            static Node getSmaller(Node n1, Node n2) {
                if (n2 == null) return n1;
                if (n1 == null) return n2;
                if (n1.v < n2.v) return n1;
                if (n2.v < n1.v) return n2;
                if (n1.idx < n2.idx) return n1;
                return n2;
            }
        }
    }
}