import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
앞부터 가져오면서 앞뒤로 비교
스택사용하면 될듯
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        ArrayDeque<Node> l = new ArrayDeque<>();

        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            l.add(new Node(i + 1, Integer.parseInt(input.nextToken())));
        }

        System.out.println(Arrays.stream(solution(N, l)).mapToObj(String::valueOf).collect(Collectors.joining("\n")));
    }

    static int[] solution(int N, ArrayDeque<Node> nodes) {
        ArrayDeque<Node> s = new ArrayDeque<>();
        int idx = 0;
        int[] result = new int[N - 1];

        while (!nodes.isEmpty()) {
            Node now = nodes.remove();

            while (!s.isEmpty()) {
                Node prev = s.remove();
                if (isSeq(prev, now)) {
                    result[idx++] = now.add(prev);
                } else {
                    s.addFirst(prev);
                    break;
                }
            }

            s.addFirst(now);
        }

        return result;
    }

    static boolean isSeq(Node n1, Node n2) {
        return n1.start - n2.end == 1 || n2.start - n1.end == 1;
    }

    static class Node {
        int idx;
        int start;
        int end;

        public Node(int idx, int n) {
            this.idx = idx;
            this.start = n;
            this.end = n;
        }

        int add(Node n) {
            int result = Math.min(this.idx, n.idx);
            this.idx = Math.max(this.idx, n.idx);
            this.start = Math.min(this.start, n.start);
            this.end = Math.max(this.end, n.end);

            return result;
        }
    }
}