import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
가장 먼것을 찾고 그거랑 가장 먼 것을 또 찾음
DFS할 Node 만들기
Line 만들기
 */

public class Main {

    static City[] cities = new City[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String input;

        for (int i = 0; i < 10001; i++) cities[i] = new City(i);
        try {
            while (!(input = bf.readLine()).isBlank()) {
                StringTokenizer st = new StringTokenizer(input);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                cities[a].add(b, v);
                cities[b].add(a, v);
            }
        } catch (Exception e) {};
        System.out.println(solution());
    }

    static int solution() {
        return findFar(findFar(1).now).v;
    }

    static Node findFar(int n) {
        Node result = new Node(n, 0);

        boolean[] visited = new boolean[10001];

        LinkedList<Node> q = new LinkedList<>();

        q.add(new Node(n, 0));
        visited[n] = true;

        while (!q.isEmpty()) {
            Node now = q.remove();

            for (Node node : cities[now.now].list) {
                if (visited[node.now]) continue;

                visited[node.now] = true;

                Node next = new Node(node.now, now.v + node.v);

                if (next.v > result.v) result = next;
                q.add(next);
            }
        }

        return result;
    }

    static class City {
        int num;
        List<Node> list = new ArrayList<>();

        public City(int num) {
            this.num = num;
        }

        public void add(int num, int v) {
            list.add(new Node(num, v));
        }
    }

    static class Node {
        int now;
        int v;

        public Node(int now, int v) {
            this.now = now;
            this.v = v;
        }
    }
}