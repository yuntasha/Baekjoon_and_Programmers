/*
그리디하게 풀어나가는게 좋을 것 같다
C보다 크거나 같은 단위가 있으면 그거 한개씩 주면됨
작은 단위가 있다면 큰거부터 넣을 수 있는 만큼 계속 넣어주자
큰 것부터 넣어서 넣었을때 C보다 커지면 작은거 트라이
만들 수 있는 최소 개수 구해주고 줄여가자
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int AN = Integer.parseInt(input.nextToken());
        int BN = Integer.parseInt(input.nextToken());

        int[][] scores = new int[N][N];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) scores[i][j] = Integer.parseInt(input.nextToken());
        }

        int[] A = new int[AN];
        input = new StringTokenizer(bf.readLine());
        for (int i = 0; i < AN; i++) A[i] = Integer.parseInt(input.nextToken());

        int[] B = new int[BN];
        input = new StringTokenizer(bf.readLine());
        for (int i = 0; i < BN; i++) B[i] = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, AN, BN, scores, A, B));
    }

    public static String solution(int N, int AN, int BN, int[][] scores, int[] A, int[] B) {
        int[][] dp = new int[AN][BN];

        int score = dfs(0, 0, AN, BN, A, B, scores, dp);

        List<Integer> result = new ArrayList<>();

        find(0, 0, AN, BN, dp, result);

        return score + "\n" + result.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    public static int dfs(int a, int b, int AN, int BN, int[] A, int[] B, int[][] scores, int[][] dp) {
        if (a >= AN || b >= BN) return 0;
        if (dp[a][b] > 0) return dp[a][b];

        dp[a][b] = Math.max(dp[a][b], dfs(a + 1, b, AN, BN, A, B, scores, dp));
        dp[a][b] = Math.max(dp[a][b], dfs(a, b + 1, AN, BN, A, B, scores, dp));
        dp[a][b] = Math.max(dp[a][b], dfs(a + 1, b + 1, AN, BN, A, B, scores, dp) + scores[A[a] - 1][B[b] - 1]);

        return dp[a][b];
    }

    public static void find(int a, int b, int AN, int BN, int[][] dp, List<Integer> result) {
        if (a >= AN) {
            for (int i = b; i < BN; i++) result.add(2);
            return;
        }
        if (b >= BN) {
            for (int i = a; i < AN; i++) result.add(1);
            return;
        }

        if (dp[a][b] == 0) {
            for (int i = a; i < AN; i++) result.add(1);
            for (int i = b; i < BN; i++) result.add(2);
            return;
        }

        if (dp[a][b] == getV(a + 1, b, dp)) {
            result.add(1);
            find(a + 1, b, AN, BN, dp, result);
        } else if (dp[a][b] == getV(a, b + 1, dp)) {
            result.add(2);
            find(a, b + 1, AN, BN, dp, result);
        } else {
            result.add(3);
            find(a + 1, b + 1, AN, BN, dp, result);
        }
    }

    public static int getV(int a, int b, int[][] dp) {
        if (a == dp.length) return 0;
        if (b == dp[0].length) return 0;
        return dp[a][b];
    }
}