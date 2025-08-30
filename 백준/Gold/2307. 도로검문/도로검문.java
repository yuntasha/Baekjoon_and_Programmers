/*
다익스트라 돌리면 어느정도 걸리지?
1000만이 최대
다익스트라로 전부 찾고
뺄 도로 완탐하자
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        List<List<Road>> connects = new ArrayList<>(N + 1);

        connects.add(null);

        for (int i = 0; i < N; i++) connects.add(new ArrayList<>());
        List<Road> roads = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            int t = Integer.parseInt(input.nextToken());

            Road now = new Road(i, a, b, t);

            roads.add(now);

            connects.get(a).add(now);
            connects.get(b).add(now);
        }

        System.out.println(solution(N, M, connects, roads));
    }

    public static int solution(int N, int M, List<List<Road>> connects, List<Road> roads) {
        int[] time = new int[N + 1];
        int[] p = new int[N + 1];
        int ori = getDist(-1, N, M, connects, time, p);
        int result = 0;

        List<Integer> path = findRoute(N, p, roads);

//        System.out.println(path);

        for (int i : path) {
            int now = getDist(i, N, M, connects, time);
            if (now == -1) return -1;
            result = Math.max(result, now);
        }

        return result - ori;
    }

    public static List<Integer> findRoute(int N, int[] p, List<Road> roads) {
        List<Integer> path = new ArrayList<>();
        int now = N;

        while (now > 1) {
            path.add(p[now]);
            now = roads.get(p[now]).getDest(now);
        }

        return path;
    }

    public static int getDist(int id, int N, int M, List<List<Road>> connects, int[] time, int[] p) {
        Arrays.fill(time, 0);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getTime));

        pq.add(new Node(-1, 1, 1));

        while (!pq.isEmpty()) {
            Node now = pq.remove();

            if (time[now.now] > 0) continue;
            time[now.now] = now.time;
            p[now.now] = now.pid;


            for (Road road : connects.get(now.now)) {
                if (road.id == id) continue;
                int next = road.getDest(now.now);

                if (time[next] > 0) continue;
                pq.add(new Node(road.id, next, now.time + road.t));
            }
        }

        return time[N] - 1;
    }

    public static int getDist(int id, int N, int M, List<List<Road>> connects, int[] time) {
        Arrays.fill(time, 0);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getTime));

        pq.add(new Node(-1, 1, 1));

        while (!pq.isEmpty()) {
            Node now = pq.remove();

            if (time[now.now] > 0) continue;
            time[now.now] = now.time;


            for (Road road : connects.get(now.now)) {
                if (road.id == id) continue;
                int next = road.getDest(now.now);

                if (time[next] > 0) continue;
                pq.add(new Node(road.id, next, now.time + road.t));
            }
        }

        return time[N] - 1;
    }

    public static class Node {
        int pid;
        int now;
        int time;

        public Node(int pid, int now, int time) {
            this.pid = pid;
            this.now = now;
            this.time = time;
        }

        public int getTime() {
            return time;
        }
    }

    public static class Road {
        int id;
        int a;
        int b;
        int t;

        public Road(int id, int a, int b, int t) {
            this.id = id;
            this.a = a;
            this.b = b;
            this.t = t;
        }

        public int getDest(int now) {
            return  a + b - now;
        }
    }
}
