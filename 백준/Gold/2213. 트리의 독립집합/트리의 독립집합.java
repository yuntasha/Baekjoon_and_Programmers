/*
트리 dp인듯

 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] values = new int[N + 1];

        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++) {
            values[i] = Integer.parseInt(input.nextToken());
        }

        List<List<Integer>> lines = new ArrayList<>();

        for (int i = 0; i <= N; i++) lines.add(new ArrayList<>());

        for (int i = 1; i < N; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            lines.get(a).add(b);
            lines.get(b).add(a);
        }

        System.out.println(solution(N, values, lines));
    }

    public static String solution(int N, int[] values, List<List<Integer>> lines) {
        int[][] dp = new int[N + 1][2];
        boolean[] visited = new boolean[N + 1];
        int maxSum = Math.max(dfs(1, 0, values, lines, visited, dp), dfs(1, 1, values, lines, visited, dp));

        visited = new boolean[N + 1];

        List<Integer> result = new ArrayList<>();

        find(1, 0, values, lines, visited, dp, result);

        return maxSum + "\n" + result.stream().sorted().map(String::valueOf).collect(Collectors.joining(" "));
    }

    public static int dfs(int now, int canUse, int[] values, List<List<Integer>> lines, boolean[] visited, int[][] dp) {
        if (visited[now]) {
            if (canUse == 0) return dp[now][0];
            return Math.max(dp[now][0], dp[now][1]);
        }

        visited[now] = true;

        dp[now][1] = values[now];

        for (int next : lines.get(now)) {
            if (visited[next]) continue;
            dp[now][1] += dfs(next, 0, values, lines, visited, dp);
            dp[now][0] += Math.max(dfs(next, 0, values, lines, visited, dp), dfs(next, 1, values, lines, visited, dp));
        }

        if (canUse == 0) return dp[now][0];
        return Math.max(dp[now][0], dp[now][1]);
    }

    public static void find(int now, int beforeUse, int[] values, List<List<Integer>> lines, boolean[] visited, int[][] dp, List<Integer> result) {
        int isUse = 0;
        if (beforeUse == 0 && dp[now][0] < dp[now][1]) {
            isUse = 1;
            result.add(now);
        }

        visited[now] = true;

        for (int next : lines.get(now)) {
            if (visited[next]) continue;
            find(next, isUse, values, lines, visited, dp, result);
        }
    }
}