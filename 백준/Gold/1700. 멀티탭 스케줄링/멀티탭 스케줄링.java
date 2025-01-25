import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/*
다음에 나올 때가 가장 가까운 것을 확인하면 될듯
아마도...?
TreeMap을 사용해서 앞뒤로 꺼내게하자
TreeMap을 사용해서 넣어주고 만약 다음 부분으로 넘어가면
이미 있는 경우 가장 뒤에 있는 것을 뺐다가 다시 넣어주고
없는 경우 가장 앞에서 빼주고
 */

public class Main {

    static int MAX_VALUE = 100_000;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        TreeSet<Node> c = new TreeSet<>(Comparator.comparingInt(Node::getNext));
        int result = 0;

        st = new StringTokenizer(bf.readLine());
        Node[] nodes = new Node[K+1];
        int[] seq = new int[K];

        for (int i=0; i<K; i++) {
            int now = Integer.parseInt(st.nextToken());
            if (nodes[now] == null) {
                nodes[now] = new Node(now);
            }
            nodes[now].add(i);
            seq[i] = now;
        }


        for (int i=0; i<K; i++) {
            nodes[seq[i]].use();

            if (c.size() < N) {
                if (!c.isEmpty() && c.first().n == seq[i]) {
                    c.pollFirst();
                    c.add(nodes[seq[i]]);
                } else {
                    c.add(nodes[seq[i]]);
                }
                continue;
            }

            if (c.first().n == seq[i]) {
                c.pollFirst();
                c.add(nodes[seq[i]]);
            } else {
                c.pollLast();
                c.add(nodes[seq[i]]);
                result++;
            }
        }

        System.out.println(result);
    }

    public static class Node {
        int n;
        ArrayDeque<Integer> idx;

        public Node(int n) {
            this.n = n;
            this.idx = new ArrayDeque<>();
        }

        public void add(int n) {
            this.idx.add(n);
        }

        public int getNext() {
            if (idx.isEmpty()) return MAX_VALUE+n;
            return this.idx.peek();
        }

        public int use() {
            return this.idx.remove();
        }
    }
}