import java.io.*;
import java.util.*;

/*
이거 징검다리 해서
다익스트라 사용하면 될듯?
1. 각 돌맹이마다 가능한 애들끼리 확인
근데 이때 해시써서 드가자
2. 다익스트라로 조지기
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int F = Integer.parseInt(input.nextToken());

        HashMap<Integer, List<Bridge>> bridge = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            if (!bridge.containsKey(a)) {
                bridge.put(a, new ArrayList<>());
            }
            Bridge bi = new Bridge(i, a, b);
            bridge.get(a).add(bi);
        }

        System.out.println(solution(N, F, bridge));
    }

    static long solution(int N, int F, HashMap<Integer, List<Bridge>> bridge) {
        for (int i : bridge.keySet()) {
            bridge.get(i).sort(Comparator.comparingInt(Bridge::getY));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(Node::getV));
        double[] visited = new double[N + 1];
        Arrays.fill(visited, -1);


        pq.add(new Node(new Bridge(0, 0, 0), 0L));

        while (!pq.isEmpty()) {
            Node now = pq.remove();

            if (now.b.y == F) {
                return Math.round(now.v);
            }

            visited[now.b.num] = now.v;

            int min = Math.max(0, now.b.x - 2);
            int max = now.b.x + 2;

            for (int x = min; x <= max; x++) {
                if (!bridge.containsKey(x)) continue;
                for (Bridge bg : bridge.get(x)) {
                    if (now.b.y - 2 > bg.y) continue;
                    if (now.b.y + 2 < bg.y) break;
                    double dis = Math.sqrt(Math.pow(Math.abs(now.b.x - bg.x), 2) + Math.pow(Math.abs(now.b.y - bg.y), 2));

                    if (visited[bg.num] != -1 && visited[bg.num] <= (now.v + dis)) continue;

                    visited[bg.num] = now.v + dis;
                    pq.add(new Node(bg, visited[bg.num]));
                }
            }
        }

        return -1;
    }

    static class Bridge {
        int num;
        int x;
        int y;

        public Bridge(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    static class Node {
        Bridge b;
        double v;

        public Node(Bridge b, double v) {
            this.b = b;
            this.v = v;
        }

        public double getV() {
            return v;
        }
    }
}