/*
DFS로 그냥 전체 탐색하는데 돌아오면서 없애야하는 수를 축적하자
그렇게 없애야하는 수
각 엣지에서 시작해서 위로 올라가자
끝까지 올라간 다음에 다시 내려오면서 최대치로 계속 지워버려
그리고 다음꺼 반복~~~
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int result = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int H = Integer.parseInt(input.nextToken());

        int[][] nodes = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            input = new StringTokenizer(bf.readLine());

            nodes[i][0] = Integer.parseInt(input.nextToken());
            nodes[i][1] = Integer.parseInt(input.nextToken());
        }

        System.out.print(solution(N, H, nodes));
    }

    public static int solution(int N, int H, int[][] nodes) {
        for (int i = 1; i <= N; i++) {
            dfs(i, 0, H, nodes);
        }
        return result;
    }

    public static int dfs(int now, int dist, int H, int[][] nodes) {
        if (nodes[now][0] == 0) return dist;
        int n = dfs(nodes[now][0], dist + nodes[now][1], H, nodes);
        if (H < n) {
            int del = Math.min(n - H, nodes[now][1]);
            result += del;
            nodes[now][1] -= del;
            n -= del;
        }

        return n;
    }
}