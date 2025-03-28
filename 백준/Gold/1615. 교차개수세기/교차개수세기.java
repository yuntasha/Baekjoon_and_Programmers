import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
A1 < A2 일때 B1 > B2라면 겹침
이분탐색은 배열 업데이트가 늦음
세그트리 가자
그럼 세그트리가 어떻게 구성이 되냐
누적합처럼 들어가진다
그리고 조회는 A1이 작은거부터 하면 현재의 B보다 큰 것의 개수를 구하면 된다
세그트리에 있는 게 선분 1이라하고 현재 비교할게 선분 2라고 한다면
A1 <= A2가 확실
그럼 B1 > B2가 성립해야한다
즉 B1이 큰 것을 찾으면 된다는 소리
그러면 선분 조회 순서는 깔끔하게 A가 작은거부터에 A가 같으면 B가 작은 것부터 ㄱㄱ
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = read();
        int M = read();

        boolean[][] isLine = new boolean[N+1][N+1];

        for (int i=0; i<M; i++) {
            isLine[read()][read()] = true;
        }

        System.out.println(solution(N, M, isLine));
    }

    public static long solution(int N, int M, boolean[][] isLine) {
        SegTree segTree = new SegTree(N);

        long result = 0;

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if (isLine[i][j]) {
                    result += segTree.getResult(j+1);
                    segTree.addNode(j);
                }
            }
        }

        return result;
    }

    static class SegTree {
        long[] nodes;
        int[] nodeIdx;
        int N;

        SegTree(int N) {
            this.nodes = new long[(N<<2)+1];
            this.nodeIdx = new int[N+1];
            this.N = N;
            initNode(1, 1, N);
        }

        private void initNode(int now, int start, int end) {
            if (start == end) {
                nodeIdx[start] = now;
                return;
            }

            int mid = (start + end) >> 1;

            initNode(now<<1, start, mid);
            initNode((now<<1) + 1, mid + 1, end);
        }

        public void addNode(int n) {
            int now = nodeIdx[n];

            while (now > 1) {
                nodes[now]++;
                now = now>>1;
            }
            nodes[now]++;
        }

        public long getResult(int n) {
            return getRange(1, 1, this.N, n);
        }

        private long getRange(int now, int start, int end, int n) {
            int mid = (start + end) >> 1;
            if (start >= n) return nodes[now];
            if (end < n) return 0;

            return getRange(now << 1, start, mid, n) + getRange((now << 1) + 1, mid + 1, end, n);
        }
    }

    private static int read() throws IOException {
        int n = 0;
        int i;
        while ((i = System.in.read()) >= '0') {
            n = (n<<3) + (n<<1) + (i&15);
        }
        return n;
    }
}