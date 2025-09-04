/*
유파로 풀고 싶은데 DFS니까...
ver DFS
 */

import java.io.*;
import java.util.*;


public class Main {

    static int MAX = 100_000;
    static String FAIL = "Oh no";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        int[] cost = new int[N];

        input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) cost[i] = Integer.parseInt(input.nextToken());

        List<List<Integer>> connects = new ArrayList<>();

        for (int i = 0; i < N; i++) connects.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken()) - 1;
            int b = Integer.parseInt(input.nextToken()) - 1;

            if (a == b) continue;

            connects.get(a).add(b);
            connects.get(b).add(a);
        }

        System.out.println(solution(N, M, K, cost, connects));
    }

    public static String solution(int N, int M, int K, int[] cost, List<List<Integer>> connects) {
        boolean[] visited = new boolean[N];

        int result = 0;

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            result += find(i, visited, cost, connects);
        }

        return result <= K ? String.valueOf(result) : FAIL;
    }

    public static int find(int n, boolean[] visited, int[] cost, List<List<Integer>> connects) {
        if (visited[n]) return MAX;

        visited[n] = true;

        int result = cost[n];

        for (int next : connects.get(n)) {
            result = Math.min(result, find(next, visited, cost, connects));
        }

        return result;
    }
}