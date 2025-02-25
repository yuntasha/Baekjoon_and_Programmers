import java.io.*;
import java.util.*;

/*
일단 K개는 무료로 해준다고 했으니까 뒤에서부터 K개를 무료로 일단 사용
도착 지점에까지 가장 비싼
만약 죄다 싼거라면?
거기까지 도달하면서 사용한 선들 전부 저장
그리고 최소 라인부터 넣으면서 위치 찾기
다익스트라?
일정 가격이하 다 넣고
K개 이하로 찾기?
log100만은 20일거고
싹넣어주기 탐색 1만
0-1 BFS로 해볼까
이하의 값이면 0
초과는 1
그렇게 일정 값 넘어가면 끝
 */

public class Main {

    static int[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int P = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        List<List<Line>> lines = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            lines.add(new ArrayList<>());
        }

        int maxValue = 0;

        for (int i = 0; i < P; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            int v = Integer.parseInt(input.nextToken());

            maxValue = Math.max(maxValue, v);

            lines.get(a).add(new Line(b, v));
            lines.get(b).add(new Line(a, v));
        }

        System.out.println(solution(N, P, K, lines, maxValue));
    }

    static int solution(int N, int P, int K, List<List<Line>> lines, int maxValue) {
        visited = new int[N + 1];
        if (!isPossible(N, K, maxValue, lines)) return -1;
        if (isPossible(N, K, 0, lines)) return 0;
        int s = 0;
        int e = maxValue;

        while (s + 1 < e) {
            int m = (s + e) >> 1;

            if (isPossible(N, K, m, lines)) {
                e = m;
            } else {
                s = m;
            }
        }

        return e;
    }

    static boolean isPossible(int N, int K, int cutLine, List<List<Line>> lines) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        Arrays.fill(visited, K +1);

        q.add(1);
        visited[1] = 0;

        while (!q.isEmpty()) {
            int now = q.remove();

            for (Line line : lines.get(now)) {
                if (visited[line.dest] <= visited[now]) continue;

                if (cutLine < line.v) {
                    if (visited[now] == K) continue;
                    if (visited[line.dest] <= visited[now] + 1) continue;
                    visited[line.dest] = visited[now] + 1;
                    q.add(line.dest);
                } else {
                    visited[line.dest] = visited[now];
                    q.addFirst(line.dest);
                }
            }
        }

        return visited[N] <= K;
    }

    static class Line {
        int dest;
        int v;

        public Line(int dest, int v) {
            this.dest = dest;
            this.v = v;
        }
    }
}