import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/*
DP로 풀자
2또는 3개로 구성
2 또는 3개로 밀고나가기

점화식
DP[i] = Math.max(DP[i-2] + chk(i-2,2), DP[i-3] + chk(i-3,3))
Node 저거로 하자
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(solution(bf.readLine()));
    }

    static String solution(String S) {
        Node[] dp = new Node[S.length()+1];
        dp[0] = new Node(0, "");

        for (int i=0; i<S.length()-1; i++) {
            if (Objects.isNull(dp[i])) continue;

            if (i+2<=S.length()) {
                String now = S.substring(i, i+2);

                int p = getPoint(now);

                if (Objects.isNull(dp[i+2])) dp[i+2] = new Node(dp[i].v + p, dp[i].s + "-" + now);
                else dp[i+2] = dp[i+2].getBigger(new Node(dp[i].v + p, dp[i].s + "-" + now));
            }

            if (i+3<=S.length()) {
                String now = S.substring(i, i+3);

                int p = getPoint(now);

                if (Objects.isNull(dp[i+3])) dp[i+3] = new Node(dp[i].v + p, dp[i].s + "-" + now);
                else dp[i+3] = dp[i+3].getBigger(new Node(dp[i].v + p, dp[i].s + "-" + now));
            }
        }
        
        return dp[S.length()].s.substring(1);
    }

    static int getPoint(String s) {
        int[] cnt = new int[10];
        for (char c: s.toCharArray()) {
            cnt[c-'0']++;
        }
        for (int i : cnt) {
            if (i==s.length()) return 2;
            if (i>1) return 1;
        }
        return 0;
    }

    static class Node implements Comparable<Node> {
        int v;
        String s;

        public Node(int v, String s) {
            this.v = v;
            this.s = s;
        }

        public Node getBigger(Node node) {
            if (compareTo(node) > 0) return node;
            return this;
        }
        
        @Override
        public int compareTo(Node node) {
            if (this.v == node.v) return this.s.compareTo(node.s);
            return -Integer.compare(this.v, node.v);
        }
    }
}