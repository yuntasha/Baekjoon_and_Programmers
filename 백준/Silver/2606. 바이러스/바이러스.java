import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());
        var M = Integer.parseInt(bf.readLine());

        var line = new ArrayList<List<Integer>>();

        for (int i=0; i<N+1; i++) {
            line.add(new ArrayList<>());
        }

        visited = new boolean[N+1];

        for (int i=0; i<M; i++) {
            var st = new StringTokenizer(bf.readLine());
            var a = Integer.parseInt(st.nextToken());
            var b = Integer.parseInt(st.nextToken());

            line.get(a).add(b);
            line.get(b).add(a);
        }

        System.out.println(solution(N, M, line));
    }

    static int solution(int N, int M, List<List<Integer>> line) {
        return dfs(1, line)-1;
    }

    static int dfs(int i, List<List<Integer>> line) {
        var result = 1;
        visited[i] = true;
        for (int d: line.get(i)) {
            if (visited[d]) continue;
            result += dfs(d, line);
        }

        return result;
    }
}