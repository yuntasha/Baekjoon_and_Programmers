/*
1번은 루트 노드
아 싸이클만 찾으면 되는구나
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        List<List<Integer>> lines = new ArrayList<>(N + 1);

        lines.add(null);

        for (int i = 0; i < N; i++) lines.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            lines.get(a).add(b);
            lines.get(b).add(a);
        }

        System.out.println(solution(N, M, lines));
    }

    public static long solution(int N, int M, List<List<Integer>> lines) {
        return find(1, 0, 1, new long[N + 1], lines);
    }

    public static long find(int n, int prev, long depth, long[] visited, List<List<Integer>> lines) {
        if (visited[n] > 0) {
            if (visited[n] < depth) return (depth - visited[n]) * (depth - visited[n]);
            return 0;
        }

        visited[n] = depth;
        long result = 0;

        for (int next : lines.get(n)) {
            if (next == prev) continue;
            result += find(next, n, depth + 1, visited, lines);
        }

        return result;
    }
}