/*
다익스트라로 그냥 밀면서사용한 애들 넣으면 될듯?
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = readI();
        int M = readI();

        List<List<Line>> lines = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            lines.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int a = readI();
            int b = readI();
            int v = readI();

            lines.get(a).add(new Line(b, v));
            lines.get(b).add(new Line(a, v));
        }

        System.out.println(solution(N, M, lines));
    }

    static String solution(int N, int M, List<List<Line>> lines) {
        int[] dist = new int[N + 1];
        List<Result> results = new ArrayList<>();

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getTime));

        pq.add(new Node(1, 1, 0));

        while (!pq.isEmpty()) {
            Node now = pq.remove();

            if (dist[now.now] > 0) continue;

            results.add(new Result(now.before, now.now));

            dist[now.now] = now.time;

            for (Line line : lines.get(now.now)) {
                if (dist[line.dest] > 0) continue;
                pq.add(new Node(line.dest, now.time + line.dist, now.now));
            }
        }

        StringBuilder output = new StringBuilder();
        output.append(results.size() - 1);

        for (int i = 1; i < results.size(); i++) {
            output.append("\n").append(results.get(i));
        }

        return output.toString();
    }

    static long readUL() throws IOException {
        int n = System.in.read();
        return n == '-' ? -readL() : readL(n & 15);
    }

    static int readUI() throws IOException {
        int n = System.in.read();
        return n == '-' ? -readI() : readI(n & 15);
    }

    static int readI() throws IOException {
        int n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }

    static int readI(int n) throws IOException {
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }

    static long readL() throws IOException {
        long n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }

    static long readL(long n) throws IOException {
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }

    static class Line {
        int dest;
        int dist;

        public Line(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }
    }

    static class Node {
        int now;
        int time;
        int before;

        public Node(int now, int time, int before) {
            this.now = now;
            this.time = time;
            this.before = before;
        }

        public int getTime() {
            return time;
        }
    }

    static class Result {
        int s;
        int e;

        public Result(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public String toString() {
            return s + " " + e;
        }
    }
}

