import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
그냥 큰 애들부터 차례대로 하나씩 사용해서 서로 엮어보자
24bytes * 2000
48000 bytes
이거 얼마 안하는데
 */

public class Main {

    static String FAIL = "-1";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        PriorityQueue<Node> nodes = new PriorityQueue<>(Comparator.comparingInt(Node::getCount));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            nodes.add(new Node(i, Integer.parseInt(input.nextToken())));
        }

        System.out.println(solution(N, nodes));
    }

    static String solution(int N, PriorityQueue<Node> nodes) {
        int[][] result = new int[N][N];

        ArrayDeque<Node> temp = new ArrayDeque<>();

        while (!nodes.isEmpty()) {
            Node now = nodes.remove();

            for (int i = 0; i < now.count; i++) {
                if (nodes.isEmpty()) return FAIL;
                Node n = nodes.remove();
                if (n.count == 0) return FAIL;
                n.count--;
                temp.add(n);
                result[now.n][n.n] = result[n.n][now.n] = result[n.n][now.n] + 1;
            }

            while (!temp.isEmpty()) {
                nodes.add(temp.remove());
            }
        }

        StringBuilder output = new StringBuilder();

        for (int[] arr : result) {
            StringBuilder sb = new StringBuilder();
            for (int i : arr) {
                sb.append(" ").append(i);
            }
            output.append("\n").append(sb.substring(1));
        }

        return output.substring(1);
    }

    static class Node {
        int n;
        int count;

        public Node(int n, int count) {
            this.n = n;
            this.count = count;
        }

        public int getCount() {
            return -count;
        }
    }
}