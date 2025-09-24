/*
걍 dfs하자
2^16
 */



import java.io.*;
import java.util.*;


public class Main {

    static Map<Character, Integer> cToI = Map.of(
            'A', 0,
            'E', 1,
            'I', 2,
            'O', 3,
            'U', 4
    );

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        String[] words = new String[N];

        for (int i = 0; i < N; i++) words[i] = bf.readLine();

        System.out.println(solution(N, words));
    }

    public static int solution(int N, String[] words) {
        int result = 0;

        int[][] dp = new int[1 << N][5];

        for (int i = 0; i < 5; i++) {
            result = Math.max(result, find(0, i, dp, N, words));
        }

        return result;
    }

    public static int find(int bits, int prev, int[][] dp, int N, String[] words) {
        if (dp[bits][prev] != 0) return Math.max(dp[bits][prev], 0);

        int result = 0;

        for (int i = 0; i < N; i++) {
            if ((bits & (1 << i)) > 0) continue;
            if (cToI.get(words[i].charAt(0)) != prev) continue;
            result = Math.max(result, find(bits | (1 << i), cToI.get(words[i].charAt(words[i].length() - 1)), dp, N, words) + words[i].length());
        }

        dp[bits][prev] = result;
        if (result == 0) dp[bits][prev] = -1;

        return result;
    }
}