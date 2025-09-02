/*
DFS 문제집 ㅇㄴㄲ...
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int a = Integer.parseInt(input.nextToken());
        int b = Integer.parseInt(input.nextToken());

        List<List<Integer>> connects = new ArrayList<>();

        for (int i = 0; i <= N; i++) connects.add(new ArrayList<>());

        int M = Integer.parseInt(bf.readLine());

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            int aa = Integer.parseInt(input.nextToken());
            int bb = Integer.parseInt(input.nextToken());

            connects.get(aa).add(bb);
            connects.get(bb).add(aa);
        }

        System.out.println(solution(N, a, b, connects));
    }

    public static int solution(int N, int a, int b, List<List<Integer>> connects) {
        int[] visited = new int[N + 1];

        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.add(a);
        visited[a] = 1;

        while (!q.isEmpty()) {
            int now = q.remove();

            for (int next : connects.get(now)) {
                if (visited[next] > 0) continue;
                q.add(next);
                visited[next] = visited[now] + 1;
            }
        }

        return visited[b] - 1;
    }
}
