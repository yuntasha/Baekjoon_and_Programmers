/*
길이가 짧은 순서대로 넘어가야할 듯
다익스트라
예산 얼마에 몇번 노드까지 갈 수 있다
DP[노드 번호][예산] = 최소 거리
이렇게 한다면?
DP[현재 노드 번호][예산] = DP[이전 노드 번호][예산 - 도로 비용] + 도로 길이
비용이 적게 들어가는 애들부터 먼저 사용한다면?
그럼 DP가 비용이 적은 애들부터 탐색해서 비용이 되는만큼 탐색하자
현재 노드 번호, 현재 예산, 현재 거리 근데 이걸 저거로 막아버리면 쓸대없는 탐색을 하지 않음

거리로 깡 다익스트라를 해버림
그러다가 cost가 넘어가면 던지고
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(bf.readLine());

        int N = Integer.parseInt(bf.readLine());

        int R = Integer.parseInt(bf.readLine());

        List<List<Road>> roads = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            roads.add(new ArrayList<>());
        }

        for (int i = 0; i < R; i++) {

            StringTokenizer input = new StringTokenizer(bf.readLine());

            int s = Integer.parseInt(input.nextToken());
            int d = Integer.parseInt(input.nextToken());
            int l = Integer.parseInt(input.nextToken());
            int c = Integer.parseInt(input.nextToken());

            roads.get(s).add(new Road(d, l, c));
        }

        System.out.println(solution(K, N, R, roads));
    }

    static int solution(int K, int N, int R, List<List<Road>> roads) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getLength));

        pq.add(new Node(1, 0, 1));

        while (!pq.isEmpty()) {
            Node now = pq.remove();

            if (now.n == N) return now.length - 1;

            for (Road road : roads.get(now.n)) {
                if (now.cost + road.c > K) continue;

                pq.add(new Node(road.d, now.cost + road.c, now.length + road.l));
            }
        }

        return -1;
    }

    static class Node {
        int n;
        int cost;
        int length;

        public Node(int n, int cost, int length) {
            this.n = n;
            this.cost = cost;
            this.length = length;
        }

        public int getLength() {
            return length;
        }
    }

    static class Road {
        int d;
        int l;
        int c;

        public Road(int d, int l, int c) {
            this.d = d;
            this.l = l;
            this.c = c;
        }
    }
}