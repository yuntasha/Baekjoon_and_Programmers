import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*

 */

public class Main {

    static final String FAIL = "-1";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(Arrays.stream(solution(N)).mapToObj(i -> String.valueOf(i + 1)).collect(Collectors.joining("\n")));
    }

    static int[] solution(int N) {
        int[] result = new int[N];

        if (dfs(N, 0, new boolean[N], result)) {
            return result;
        }

        return new int[]{-2};
    }

    static boolean dfs(int N, int depth, boolean[] visited, int[] result) {
        if (depth == N) return true;

        for (int i = 0; i < N; i++) {
            if (visited[i] || i == depth || N - 1 - i == depth) continue;
            visited[i] = true;
            result[depth] = i;
            if (dfs(N, depth + 1, visited, result)) {
                return true;
            }
            visited[i] = false;
            result[depth] = -1;
        }

        return false;
    }
}