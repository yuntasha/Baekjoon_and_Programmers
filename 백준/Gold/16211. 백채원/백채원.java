import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
그러니까 각 추종자 집에서 특정 위치까지의 거리가 1번에서 출발한 거리보다 짧은 애들을 모두 찾는 것
일단 1번에서 전체를 구하고
각 위치를 1로 두고 다익스트라로 뻗어나가면 되겠네
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        List<List<Road>> roads = new ArrayList<>();

        for (int i = 0; i <= N; i++) roads.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            int t = Integer.parseInt(input.nextToken());

            roads.get(a).add(new Road(b, t));
            roads.get(b).add(new Road(a, t));
        }

        int[] P = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, M, K, roads, P));
    }

    public static String solution(int N, int M, int K, List<List<Road>> roads, int[] P) {
        List<Integer> result = new ArrayList<>();

        int[] dist = getDist(N, roads);
        int[] time = getTime(N, K, roads, P);

        for (int i = 2; i <= N; i++) {
            if (dist[i] < time[i]) result.add(i);
        }

        return result.isEmpty() ? "0" : result.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    static int[] getTime(int N, int K, List<List<Road>> roads, int[] P) {
        int[] result = new int[N + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getT));

        for (int k = 0; k < K; k++) {
            pq.add(new Node(P[k], 1));
        }

        while (!pq.isEmpty()) {
            Node now = pq.remove();

            if (result[now.n] > 0) continue;
            result[now.n] = now.t;

            for (Road r : roads.get(now.n)) {
                if (result[r.d] > 0) continue;
                pq.add(new Node(r.d, r.t + now.t));
            }
        }

        return result;
    }

    static int[] getDist(int N, List<List<Road>> roads) {
        int[] dist = new int[N + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getT));

        pq.add(new Node(1, 1));

        while (!pq.isEmpty()) {
            Node now = pq.remove();

            if (dist[now.n] > 0) continue;
            dist[now.n] = now.t;

            for (Road r : roads.get(now.n)) {
                if (dist[r.d] > 0) continue;
                pq.add(new Node(r.d, r.t + now.t));
            }
        }

        return dist;
    }

    static class Road {
        int d;
        int t;

        public Road(int d, int t) {
            this.d = d;
            this.t = t;
        }
    }

    static class Node {
        int n;
        int t;

        public Node(int n, int t) {
            this.n = n;
            this.t = t;
        }

        public int getT() {
            return t;
        }
    }
}