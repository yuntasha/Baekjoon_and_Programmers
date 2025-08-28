/*
음
앞에서부터 채워나가면 될 것 같은데
1만 * 1만하면 될듯?

이때까지 나온 Y의 최댓값, 현재 X + 1의 반

뒤에서부터 탐색해서 가장 높은 Y값도 찾아야한다
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Node> builds = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            builds.add(new Node(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken())));
        }

        System.out.println(solution(N, builds));
    }

    public static int solution(int N, List<Node> builds) {
        int[] dp = new int[N];

        builds.sort(Comparator.comparingInt(Node::getX));

        int maxY = 0;

        for (int i = 0; i < N; i++) {
            maxY = Math.max(maxY, builds.get(i).getY());
            dp[i] = Math.max(builds.get(i).x - builds.get(0).x, maxY << 1);

            int mY = builds.get(i).getY();
            for (int j = i - 1; j >= 0; j--) {
                int now = Math.max(builds.get(i).x - builds.get(j + 1).x, mY << 1);
                dp[i] = Math.min(dp[i], now + dp[j]);
                mY = Math.max(mY, builds.get(j).getY());
            }
        }

//        System.out.println(Arrays.toString(dp));

        return dp[N - 1];
    }

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return Math.abs(y);
        }
    }
}
