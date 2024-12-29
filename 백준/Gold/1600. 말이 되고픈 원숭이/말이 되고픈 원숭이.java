import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
그냥 BFS인데 인접한 칸 말고도 말처럼 움직이는 것도 넣음
 */

public class Main {

    static int[] moveDx = {0, 1, 0, -1};
    static int[] moveDy = {1, 0, -1, 0};
    static int[] jumpDx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] jumpDy = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] map = new int[H][];

        for (int i=0; i<H; i++) {
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(K, W, H, map));
    }

    public static int solution(int K, int W, int H, int[][] map) {
        int[][][] dp = new int[H][W][K+1];
        LinkedList<Node> q = new LinkedList<>();

        dp[0][0][0] = 1;
        q.add(new Node(0, 0, 0));

        while(!q.isEmpty()) {
            Node now = q.remove();

            for (int d=0; d<4; d++) {
                int w = now.w + moveDx[d];
                int h = now.h + moveDy[d];
                int k = now.k;

                if (!isIn(k, w, h, K, W, H)) continue;
                if (dp[h][w][k] > 0) continue;
                if (map[h][w] == 1) continue;

                dp[h][w][k] = dp[now.h][now.w][now.k] + 1;
                q.add(new Node(h, w, k));
            }

            for (int d=0; d<8; d++) {
                int w = now.w + jumpDx[d];
                int h = now.h + jumpDy[d];
                int k = now.k + 1;

                if (!isIn(k, w, h, K, W, H)) continue;
                if (dp[h][w][k] > 0) continue;
                if (map[h][w] == 1) continue;

                dp[h][w][k] = dp[now.h][now.w][now.k] + 1;
                q.add(new Node(h, w, k));
            }
        }

        int result = 0;

        for (int i : dp[H-1][W-1]) {
            if (i==0) continue;
            if (result == 0) result = i;
            result = Math.min(result, i);
        }

        return result - 1;
    }

    public static boolean isIn(int k, int w, int h, int K, int W, int H) {
        return 0<=k && k<=K && 0<=w && w<W && 0<=h && h<H;
    }
    
    static class Node {
        int h;
        int w;
        int k;
        
        public Node(int h, int w, int k) {
            this.h = h;
            this.w = w;
            this.k = k;
        }
    }
}