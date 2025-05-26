import java.io.*;
import java.util.*;

/*
트리 구조
가장 많은 열매를 먹을 수 있는 구조 가장 작은 번호의 노드에서 시작
아무 위치 잡고 거기서 가장 가는길에 열매가 많은 곳을 찾아

 */

public class Main {

    static int start = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] count = new int[N + 1];

        StringTokenizer input = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            count[i] = Integer.parseInt(input.nextToken());
        }

        List<List<Integer>> connects = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            connects.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            connects.get(a).add(b);
            connects.get(b).add(a);
        }

        System.out.println(solution(N, count, connects));
    }

    static String solution(int N, int[] count, List<List<Integer>> connects) {
        int[] start = dfs(1, N, count, connects, new boolean[N + 1]);
        int[] result = dfs(start[0], N, count, connects, new boolean[N + 1]);

        return result[1] + " " + Math.min(start[0], result[0]);
    }

    static int[] dfs(int n, int N, int[] count, List<List<Integer>> connects, boolean[] visited) {
        int[] result = {n, 0};

        visited[n] = true;

        for (int next : connects.get(n)) {
            if (visited[next]) continue;
            int[] now = dfs(next, N, count, connects, visited);
            if (now[1] > result[1] || (now[1] == result[1] && now[0] < result[0])) {
                result = now;
            }
        }

        result[1] += count[n];

        return result;
    }
}