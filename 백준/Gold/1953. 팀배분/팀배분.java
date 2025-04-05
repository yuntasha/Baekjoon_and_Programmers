import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
일단 싫어하는 사람들끼린 서로 다른 팀이 되도록 만들어야함
일단 싫어하는 사람은 어떻게되든 한명은 존재함
그렇다고 하나의 그래프로 나오진 않음
일단 기본 0으로 만들고 DFS로 그냥 다른 팀이 되도록 설정
서로 싫어한다는 규칙으로 양방향 간선임

4
1 3
1 4
1 1 2 4
0
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<List<Integer>> links = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            links.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int n = Integer.parseInt(input.nextToken());

            for (int j = 0; j < n; j++) {
                int now = Integer.parseInt(input.nextToken());
                links.get(i).add(now);
            }
        }

        System.out.print(solution(N, links));
    }

    static String solution(int N, List<List<Integer>> links) {
        int[] result = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(N, links, i, visited, result);
        }

        List<List<Integer>> team = new ArrayList<>();
        team.add(new ArrayList<>());
        team.add(new ArrayList<>());

        for (int i = 1; i < N; i++) {
            team.get(result[i]).add(i);
        }

        if (team.get(1).isEmpty()) {
            team.get(1).add(N);
        } else if (team.get(0).isEmpty()) {
            team.get(0).add(N);
        } else {
            team.get(result[N]).add(N);
        }

        return team.get(0).size() + "\n" + team.get(0).stream().map(String::valueOf).collect(Collectors.joining(" "))
                + "\n" +
                team.get(1).size() + "\n" + team.get(1).stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    static void dfs(int N, List<List<Integer>> links, int now, boolean[] visited, int[] result) {
        for (int next : links.get(now)) {
            if (visited[next]) continue;
            result[next] = result[now] ^ 1;
            visited[next] = true;
            dfs(N, links, next, visited, result);
        }
    }
}