/*
해당 역이 어떤 노선을 가지는지
노선에 어떤 역들이 있는지
BFS
현 위치를 넣고
노선 들어가있는 애들을 전부 조회해서 다음 큐에 넣어주자
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int L = Integer.parseInt(input.nextToken());

        List<List<Integer>> lineToNode = new ArrayList<>(); //
        List<List<Integer>> nodeToLine = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            nodeToLine.add(new ArrayList<>());
        }

        for (int i = 0; i < L; i++) {
            lineToNode.add(new ArrayList<>());

            input = new StringTokenizer(bf.readLine());

            int n;
            while ((n = Integer.parseInt(input.nextToken())) > 0) {
                lineToNode.get(i).add(n);
                nodeToLine.get(n).add(i);
            }
        }

        input = new StringTokenizer(bf.readLine());

        int start = Integer.parseInt(input.nextToken());
        int end = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, L, lineToNode, nodeToLine, start, end));
    }

    static int solution(int N, int L, List<List<Integer>> lineToNode, List<List<Integer>> nodeToLine, int start, int end) {
        int[] visited = new int[N + 1];
        boolean[] used = new boolean[L];

        ArrayDeque<Integer> q = new ArrayDeque<>();

        visited[start] = 1;
        q.add(start);

        while (!q.isEmpty()) {
            int now = q.remove();

            for (int line : nodeToLine.get(now)) {
                if (used[line]) continue;
                used[line] = true;
                for (int next : lineToNode.get(line)) {
                    if (visited[next] > 0) continue;
                    visited[next] = visited[now] + 1;
                    q.add(next);
                }
            }
        }
        
        if (visited[end] == 0) return -1;

        return Math.max(visited[end] - 2, 0);
    }
}