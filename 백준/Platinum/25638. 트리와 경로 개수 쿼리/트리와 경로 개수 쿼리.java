/*
좌우로 각각의 색깔이 몇개인지 찾으면 된다
한 부분을 잡아서 dfs로 탐색하고 그 결과 가져오자

하나인 애를 가져와서 걔부터 시작하면 되나
합해서 만들지말고 각각 저장하자
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] colors = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<List<Integer>> lines = new ArrayList<>();

        for (int i = 0; i < N; i++) lines.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken()) - 1;
            int b = Integer.parseInt(input.nextToken()) - 1;

            lines.get(a).add(b);
            lines.get(b).add(a);
        }

        int[] counts = getColorCount(colors);

        long[] dp = solution(N, colors, lines, counts);

        int M = Integer.parseInt(bf.readLine());

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(bf.readLine()) - 1;
            output.append("\n").append(dp[n]);
        }

        System.out.println(output.substring(1));
    }

    static long[] solution(int N, int[] colors, List<List<Integer>> lines, int[] counts) {
        long[] dp = new long[N];
        boolean[] visited = new boolean[N];
        visited[0] = true;

        dfs(0, colors, lines, dp, visited, counts);

        return dp;
    }

    static int[] getColorCount(int[] colors) {
        int[] result = new int[2];

        for (int i : colors) {
            result[i]++;
        }

        return result;
    }

    static Tree dfs(int n, int[] colors, List<List<Integer>> lines, long[] dp, boolean[] visited, int[] counts) {
        Tree now = new Tree(0, 0);

        List<Tree> subTrees = new ArrayList<>();

        for (int next : lines.get(n)) {
            if (visited[next]) continue;
            visited[next] = true;
            Tree tree = dfs(next, colors, lines, dp, visited, counts);
            subTrees.add(tree);
            dp[n] += tree.b * now.r + tree.r * now.b;
            now.add(tree);
        }

        if (colors[n] == 0) {
            dp[n] += now.r * (counts[0] - now.b - 1) + now.b * (counts[1] - now.r);
            now.b++;
        } else {
            dp[n] += now.r * (counts[0] - now.b) + now.b * (counts[1] - now.r - 1);
            now.r++;
        }

        return now;
    }

    static class Tree {
        long r;
        long b;

        public Tree(int b, int r) {
            this.b = b;
            this.r = r;
        }

        public void add(Tree t) {
            r += t.r;
            b += t.b;
        }
    }
}