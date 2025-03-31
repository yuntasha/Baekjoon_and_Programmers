/*
중량 제한 1,000,000,000 = 10억
최대 10억
가장 크게 도착할 수 있는 위치부터 가라
*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        List<List<Bridge>> bridges = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            bridges.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            int v = Integer.parseInt(input.nextToken());

            bridges.get(a).add(new Bridge(b, v));
            bridges.get(b).add(new Bridge(a, v));
        }

        input = new StringTokenizer(bf.readLine());

        int start = Integer.parseInt(input.nextToken());
        int end = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, M, bridges, start, end));
    }

    public static int solution(int N, int M, List<List<Bridge>> bridges, int start, int end) {
        boolean[] visited = new boolean[N + 1];
        int[] result = new int[N + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getV).reversed());

        for (Bridge b : bridges.get(start)) {
            pq.add(new Node(start, b.e, b.v));
        }
        visited[start] = true;
        result[start] = 1_000_000_000;

        while (!pq.isEmpty()) {
            Node now = pq.remove();

            if (visited[now.d]) continue;
            visited[now.d] = true;
            result[now.d] = Math.min(result[now.p], now.v);
            if (result[end] != 0) break;

            for (Bridge b : bridges.get(now.d)) {
                if (visited[b.e]) continue;
                pq.add(new Node(now.d, b.e, b.v));
            }
        }

        return result[end];
    }

    public static class Bridge {
        int e;
        int v;

        public Bridge(int e, int v) {
            this.e = e;
            this.v = v;
        }
    }

    public static class Node {
        int p;
        int d;
        int v;

        public Node(int p, int d, int v) {
            this.p = p;
            this.d = d;
            this.v = v;
        }

        public int getV() {
            return v;
        }
    }
}