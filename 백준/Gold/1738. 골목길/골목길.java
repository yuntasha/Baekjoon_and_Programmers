import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
벨만 포드
돈을 다시 주을 수 있음
그러니까 싸이클을 찾아야함
근데 싸이클은 양의 싸이클만 찾고 음의 싸이클은 찾을 필요가 없음
근데 싸이클이 돌아졌을때
역추적을 해서 찾음
근데 만약에 최적 경로가 아닌 곳에서 갑자기 양의 싸이클이 도는 것이 있다면?

 */

public class Main {

    static int INF = 100_000_000;
    static String FAIL = "-1";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        List<Line>[] lines = new List[N+1];

        for (int i = 1; i <= N; i++) {
            lines[i] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            input = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(input.nextToken());
            int end = Integer.parseInt(input.nextToken());
            int value = Integer.parseInt(input.nextToken());
            lines[start].add(new Line(end, value));
        }

        System.out.println(solution(N, M, lines));
    }

    static String solution(int N, int M, List<Line>[] lines) {
        int[] table = new int[N+1];
        Arrays.fill(table, -INF);
        table[1] = 0;

        for (int i = 1; i < N; i++) {
            for (int s = 1; s <= N; s++) {
                for (Line l : lines[s]) {
                    table[l.end] = Math.max(table[l.end], table[s] + l.value);
                }
            }
        }

        if (table[N] == -INF) {
            return FAIL;
        }

        for (int s = 1; s <= N; s++) {
            for (Line l : lines[s]) {
                if (table[l.end] < table[s] + l.value) {
                    if (canGo(N, l.end, lines)) return FAIL;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        findResult(N, 1, table, lines, result, new boolean[N+1]);

        StringJoiner sj = new StringJoiner(" ");

        for (int i = result.size() - 1; 0 <= i; i--) {
            sj.add(String.valueOf(result.get(i)));
        }

        return sj.toString();
    }

    static boolean findResult(int N, int now, int[] table, List<Line>[] lines, List<Integer> result, boolean[] visited) {
        if (now == N) {
            result.add(N);
            return true;
        }

        for (Line l : lines[now]) {
            if (visited[l.end]) continue;
            if (table[l.end] != table[now] + l.value) continue;
            visited[l.end] = true;
            if (findResult(N, l.end, table, lines, result, visited)) {
                result.add(now);
                return true;
            }
            visited[l.end] = false;
        }

        return false;
    }

    static boolean canGo(int N, int n, List<Line>[] lines) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];

        q.add(n);
        visited[n] = true;

        while(!q.isEmpty()) {
            int now = q.remove();

            for (Line l : lines[now]) {
                if (visited[l.end]) continue;
                visited[l.end] = true;
                q.add(l.end);
            }
        }

        return visited[N];
    }

    static class Line {
        int end;
        int value;

        Line(int end, int value) {
            this.end = end;
            this.value = value;
        }
    }
}