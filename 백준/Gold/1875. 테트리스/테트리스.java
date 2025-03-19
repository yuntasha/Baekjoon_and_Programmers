/*
400 * 400 * 400 = 64,000,000 6400만
이게 한번 탐색할 때 나오는 것
가능한거 싹다 그렇게
그냥 탐색하는데 중복되는거 따로 처리하면 되나?
위에 3개 기준으로는 같은거 여러개 나올 느낌인데
위에 3개보고 어떻게 들어갈지 정하는거 그거면 될듯?

BFS라서 메모리가 터지나
그러면 DFS로 하면 되나?

[i][a][b][c] = ?
이렇게?
 */

import java.io.*;
import java.util.*;

public class Main {

    static HashMap<Node, Integer> dp;
    static int MAX_VALUE = 1000;

    public static void main(String[] args) throws IOException {

        int N = read();

        List<Integer> blocks = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            blocks.add(read());
        }

        dp = new HashMap<>();

        System.out.println(solution(N, blocks));
    }

    public static int solution(int N, List<Integer> blocks) {
        return dfs(N, blocks, new Node(0, 0, 0));
    }

    static int dfs(int N, List<Integer> blocks, Node node) {
        if (dp.containsKey(node)) return dp.get(node);
        if (node.i == N) {
            return 0;
        }

        Node next;
        int result = MAX_VALUE;
        int n = blocks.get(node.i);
        int ex;

        if (n == 1) {
            next = add1(0, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add1(1, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add1(2, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
        } else if (n == 2) {
            next = add2(0, 1, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add2(1, 1, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add2(1, 2, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add2(0, 3, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add2(1, 3, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add2(1, 4, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
        } else if (n == 3) {
            next = add3(0, 1, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add3(1, 1, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add3(1, 2, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add3(0, 3, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add3(1, 3, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add3(1, 4, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
        } else if (n == 4) {
            next = add4(0, 1, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add4(0, 2, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add4(1, 2, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add4(0, 3, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add4(0, 4, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add4(1, 4, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
        } else if (n == 5) {
            next = add5(0, 1, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add5(0, 2, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add5(1, 2, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
        } else if (n == 6) {
            next = add6(0, 1, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add6(0, 2, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add6(1, 2, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
        } else {
            next = add7(0, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
            next = add7(1, node);
            ex = next.getV();
            next.set();
            result = Math.min(result, dfs(N, blocks, next) + ex);
        }

        dp.put(node, result);

        return result;
    }

    public static Node add1(int l, Node node) {
        Node now = new Node(node);
        now.h[l] += 4;
        return now;
    }

    public static Node add2(int l, int r, Node node) {
        Node now = new Node(node);
        if (r == 1) {
            now.h[l + 1] = now.h[l] = Math.max(now.h[l] + 3, now.h[l + 1] + 1);
        } else if (r == 2) {
            now.h[1] = now.h[2] = Math.max(now.h[0], Math.max(now.h[1], now.h[2])) + 1;
            now.h[0] = now.h[1] + 1;
        } else if (r == 3) {
            now.h[l] = Math.max(now.h[l], now.h[l + 1]) + 1;
            now.h[l + 1] = now.h[l] + 2;
        } else {
            now.h[0] = now.h[1] = now.h[2] = Math.max(now.h[0], Math.max(now.h[1], now.h[2] + 1)) + 1;
        }
        return now;
    }

    public static Node add3(int l, int r, Node node) {
        Node now = new Node(node);
        if (r == 1) {
            now.h[l + 1] = now.h[l] = Math.max(now.h[l] + 1, now.h[l + 1] + 3);
        } else if (r == 2) {
            now.h[0] = now.h[1] = Math.max(now.h[0], Math.max(now.h[1], now.h[2])) + 1;
            now.h[2] = now.h[1] + 1;
        } else if (r == 3) {
            now.h[l + 1] = Math.max(now.h[l], now.h[l + 1]) + 1;
            now.h[l] = now.h[l + 1] + 2;
        } else {
            now.h[0] = now.h[1] = now.h[2] = Math.max(now.h[0] + 1, Math.max(now.h[1], now.h[2])) + 1;
        }
        return now;
    }

    public static Node add4(int l, int r, Node node) {
        Node now = new Node(node);
        if (r == 1) {
            now.h[0] = now.h[2] = Math.max(now.h[0], Math.max(now.h[1], now.h[2])) + 1;
            now.h[1] = now.h[0] + 1;
        } else if (r == 2) {
            now.h[l + 1] = Math.max(now.h[l] + 1, now.h[l + 1]) + 1;
            now.h[l] = now.h[l + 1] + 1;
        } else if (r == 3) {
            now.h[0] = now.h[1] = now.h[2] = Math.max(now.h[0], Math.max(now.h[1] + 1, now.h[2])) + 1;
        } else {
            now.h[l] = Math.max(now.h[l], now.h[l + 1] + 1) + 1;
            now.h[l + 1] = now.h[l] + 1;
        }
        return now;
    }

    public static Node add5(int l, int r, Node node) {
        Node now = new Node(node);
        if (r == 1) {
            now.h[0] = Math.max(now.h[0] + 1, Math.max(now.h[1] + 1, now.h[2]));
            now.h[1] = now.h[2] = now.h[0] + 1;
        } else if (r == 2) {
            now.h[l + 1] = Math.max(now.h[l], now.h[l + 1] + 1) + 1;
            now.h[l] = now.h[l + 1] + 1;
        }
        return now;
    }

    public static Node add6(int l, int r, Node node) {
        Node now = new Node(node);
        if (r == 1) {
            now.h[2] = Math.max(now.h[0], Math.max(now.h[1] + 1, now.h[2] + 1));
            now.h[0] = now.h[1] = now.h[2] + 1;
        } else if (r == 2) {
            now.h[l] = Math.max(now.h[l] + 1, now.h[l + 1]) + 1;
            now.h[l + 1] = now.h[l] + 1;
        }
        return now;
    }

    public static Node add7(int l, Node node) {
        Node now = new Node(node);
        now.h[l] = now.h[l + 1] = Math.max(now.h[l], now.h[l + 1]) + 2;
        return now;
    }

    public static int read() throws IOException {
        int n = 0;
        int c;
        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    public static class Node {
        int i;
        int[] h;

        Node(int a, int b, int c) {
            this.i = 0;
            h = new int[3];
            h[0] = a;
            h[1] = b;
            h[2] = c;
        }

        Node(Node n) {
            this.i = n.i + 1;
            this.h = new int[3];
            this.h[0] = n.h[0];
            this.h[1] = n.h[1];
            this.h[2] = n.h[2];
        }

        void set() {
            int n = getV();
            h[0] = h[0] - n;
            h[1] = h[1] - n;
            h[2] = h[2] - n;
        }

        int getV() {
            return Math.max(h[0], Math.max(h[1], h[2]));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return i == node.i && Arrays.equals(h, node.h);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(i);
            result = 31 * result + Arrays.hashCode(h);
            return result;
        }

        @Override
        public String toString() {
            return i + " " + h[0] + " " + h[1] + " " + h[2];
        }
    }
}