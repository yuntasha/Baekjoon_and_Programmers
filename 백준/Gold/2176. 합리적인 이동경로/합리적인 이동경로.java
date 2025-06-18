/*
다익스트라로 2에서의 거리를 구함
그 다음에 1에서 dfs를 통해 더 짧아지는 곳으로만 이동
그렇게 갈 수 있는 개수를 구함

10만 * log10만
그 다음에 dfs에 dp까지 섞자
각 위치에서 갈 수 있는 가짓수를 메모라이징
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        int N = read();
        int M = read();

        List<List<Line>> lines = new ArrayList<>();

        for (int i = 0; i <= N; i++) lines.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            int a = read();
            int b = read();
            int c = read();

            lines.get(a).add(new Line(b, c));
            lines.get(b).add(new Line(a, c));
        }

        System.out.println(solution(N, M, lines));
    }

    static int solution(int N, int M, List<List<Line>> lines) {
        int[] dist = getDist(N, M, lines);

        return find(1, dist, lines, new int[N + 1], new boolean[N + 1]);
    }

    static int find(int n, int[] dist, List<List<Line>> lines, int[] dp, boolean[] visited) {
        if (n == 2) return 1;
        if (visited[n]) return dp[n];

        visited[n] = true;

        for (Line line : lines.get(n)) {
            if (dist[n] <= dist[line.dest]) continue;
            dp[n] += find(line.dest, dist, lines, dp, visited);
        }

        return dp[n];
    }

    static int[] getDist(int N, int M, List<List<Line>> lines) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getV));
        pq.add(new Node(2, 0));

        while (!pq.isEmpty()) {
            Node now = pq.remove();
            if (dist[now.n] <= now.v) continue;
            dist[now.n] = now.v;

            for (Line line : lines.get(now.n)) {
                if (dist[line.dest] <= dist[now.n] + line.len) continue;
                pq.add(new Node(line.dest, dist[now.n] + line.len));
            }
        }

        return dist;
    }

    static class Node {
        int n;
        int v;

        public Node(int n, int v) {
            this.n = n;
            this.v = v;
        }

        public int getV() {
            return v;
        }
    }

    static class Line {
        int dest;
        int len;

        public Line(int dest, int len) {
            this.dest = dest;
            this.len = len;
        }
    }

    static int read() throws IOException {
        int n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}