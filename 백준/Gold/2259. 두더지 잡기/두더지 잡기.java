import java.io.*;
import java.util.*;

public class Main {

    static int X = 0;
    static int Y = 1;
    static int T = 2;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int S = Integer.parseInt(input.nextToken());

        int[][] moles = new int[N][3];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            moles[i][X] = Integer.parseInt(input.nextToken());
            moles[i][Y] = Integer.parseInt(input.nextToken());
            moles[i][T] = Integer.parseInt(input.nextToken());
        }

        System.out.print(solution(N, S, moles));
    }

    public static int solution(int N, int S, int[][] moles) {
        Arrays.sort(moles, (a, b) -> a[T] - b[T]);

        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            if (canGo(0, 0, 0, moles[i][X], moles[i][Y], moles[i][T], S)) dp[i] = 1;
        }

        for (int s = 0; s < N; s++) {
            if (dp[s] == 0) continue;
            for (int e = s + 1; e < N; e++) {
                if (canGo(moles[s][X], moles[s][Y], moles[s][T], moles[e][X], moles[e][Y], moles[e][T], S)) dp[e] = Math.max(dp[e], dp[s] + 1);
            }
        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    public static boolean canGo(int x1, int y1, int t1, int x2, int y2, int t2, int S) {
        return S * (t2 - t1) >= Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}