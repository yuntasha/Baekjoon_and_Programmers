/*
저 문제에서 이제 dfs로 해결을 해봐요
그럼 어떻게하냐

W를 건설 걸리는 시간
W를 건설하기 위해 필요한 건물
그 건물도 시간을 구해야겠죠?
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        StringBuilder output = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(input.nextToken());
            int K = Integer.parseInt(input.nextToken());

            List<List<Integer>> before = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                before.add(new ArrayList<>());
            }

            int[] cost = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int i = 0; i < K; i++) {
                input = new StringTokenizer(bf.readLine());

                int a = Integer.parseInt(input.nextToken()) - 1;
                int b = Integer.parseInt(input.nextToken()) - 1;

                before.get(b).add(a);
            }

            int W = Integer.parseInt(bf.readLine()) - 1;

            output.append("\n").append(solution(N, K, before, cost, W));
        }

        System.out.println(output.substring(1));
    }

    static int solution(int N, int K, List<List<Integer>> before, int[] cost, int W) {
        int[] dp = new int[N];
        boolean[] visited = new boolean[N];
        return find(before, cost, W, dp, visited);
    }

    static int find(List<List<Integer>> before, int[] cost, int w, int[] dp, boolean[] visited) {
        if (visited[w]) {
            return dp[w];
        }

        visited[w] = true;
        dp[w] = 0;

        for (int i : before.get(w)) {
            dp[w] = Math.max(dp[w], find(before, cost, i, dp, visited));
        }

        // time은 현재 건물을 짓기위한 준비시간
        dp[w] = dp[w] + cost[w];
        return dp[w];
    }
}