import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
인접한 애들은 각각 다르다는 소리
그러니까 BFS로 넘겨보자
visited과 group으로 만듦
그러면 visited로 해당 위치를 탐색하지 않았다면 group과 큐에 넣기
group과 번호가 잘 되어있는지 확인
 */

public class Main {

    static String YES = "YES";
    static String NO = "NO";

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(bf.readLine());

        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            List<List<Integer>> line = new ArrayList<>();

            for (int i=0; i<=V; i++) line.add(new ArrayList<>());

            for (int e = 0; e < E; e++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                line.get(a).add(b);
                line.get(b).add(a);
            }

            System.out.println(solution(V, E, line));
        }
    }

    public static String solution(int V, int E, List<List<Integer>> line) {
        boolean[] visited = new boolean[V + 1];
        boolean[] group = new boolean[V + 1];

        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int start = 1; start <= V; start++) {
            if (visited[start]) continue;

            q.add(start);
            visited[start] = true;

            while (!q.isEmpty()) {
                int now = q.remove();

                for (int next : line.get(now)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        group[next] = !group[now];
                        q.add(next);
                    }

                    if (group[next] == group[now]) return NO;
                }
            }
        }

        return YES;
    }
}