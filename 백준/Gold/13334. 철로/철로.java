/*
pq를 사용해서 겹치는 최대 수를 그냥...
 */

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        PriorityQueue<Node> nodes = new PriorityQueue<>(Comparator.comparingLong(Node::getE));

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            nodes.add(new Node(Long.parseLong(input.nextToken()), Long.parseLong(input.nextToken())));
        }

        int D = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, nodes, D));
    }

    public static int solution(int N, PriorityQueue<Node> nodes, int D) {
        PriorityQueue<Long> sPq= new PriorityQueue<>();

        int result = 0;

        while (!nodes.isEmpty()) {
            Node node = nodes.remove();

            sPq.add(node.s);

            while (!sPq.isEmpty() && sPq.peek() < node.e - D) sPq.remove();
            result = Math.max(result, sPq.size());
        }

        return result;
    }

    public static class Node {
        long s;
        long e;

        public Node(long s, long e) {
            this.s = Math.min(s, e);
            this.e = Math.max(s, e);
        }

        public long getE() {
            return e;
        }
    }
}