import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {
        boolean[][] visited = new boolean[N][N];

        Arrays.sort(arr);

        LinkedList<Node> q = new LinkedList<>();

        q.add(new Node(0, N-1));

        while (!q.isEmpty()) {
            Node now = q.remove();

            if (visited[now.s][now.e]) continue;
            visited[now.s][now.e] = true;
            if (now.e - now.s + 1 < 3) return now.e - now.s + 1;
            if (arr[now.s] + arr[now.s+1] > arr[now.e]) return now.e - now.s + 1;

            q.add(new Node(now.s, now.e-1));
            q.add(new Node(now.s+1, now.e));
        }

        return 0;
    }

    static class Node implements Comparable<Node> {
        int s;
        int e;

        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Node o) {
            return (o.e - o.s) - (this.e - this.s);
        }
    }
}