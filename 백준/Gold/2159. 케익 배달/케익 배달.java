/*
상하좌우 dp
long 해야겠다
 */

import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {0, 0, -1, 0, 1};
    static int[] dy = {0, -1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Node> rests = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            rests.add(new Node(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken())));
        }

        System.out.println(solution(N, rests));
    }

    static long solution(int N, List<Node> rests) {
        long[] togle = new long[5];

        if (N == 1) return 0;

        for (int i = 0; i < 5; i++) {
            togle[i] = getDist(rests.get(0).x, rests.get(0).y, rests.get(1).x + dx[i], rests.get(1).y + dy[i]);
        }

        for (int i = 1; i < N; i++) {
            long[] next = getInit();
            for (int s = 0; s < 5; s++) {
                int sx = rests.get(i).x + dx[s];
                int sy = rests.get(i).y + dy[s];
                for (int e = 0; e < 5; e++) {
                    int ex = rests.get(i + 1).x + dx[e];
                    int ey = rests.get(i + 1).y + dy[e];
                    next[e] = Math.min(next[e], togle[s] + getDist(sx, sy, ex, ey));
                }
            }
            togle = next;
        }

        long result = Long.MAX_VALUE;

        for (int i = 0; i < 5; i++) {
            result = Math.min(result, togle[i]);
        }

        return result;
    }

    static long getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static long[] getInit() {
        return new long[] {Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE};
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}