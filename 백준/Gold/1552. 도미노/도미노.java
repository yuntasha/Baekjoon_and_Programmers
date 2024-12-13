import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] arr = new int[N][N];

        for (int i=0; i<N; i++) {
            char[] input = bf.readLine().toCharArray();
            for (int j=0; j<N; j++) {
                arr[i][j] = (input[j] - (input[j] >= 'A'?'A'-1:'0')) * (input[j] >= 'A'?-1:1);
            }
        }

        System.out.println(Arrays.stream(solution(N, arr)).mapToObj(String::valueOf).collect(Collectors.joining("\n")));
    }

    static int[] solution(int N, int[][] arr) {
        int[] result = new int[2];
        result[0] = Integer.MAX_VALUE;
        result[1] = Integer.MIN_VALUE;

        dfs(N, arr, 0, 1, 0, result, IntStream.range(0, N).toArray(), 0);

        return result;
    }

    static void dfs(int N, int[][] arr, int now, int point, int visited, int[] result, int[] group, int cycles) {
        if (now == N) {
            int n = point * ((cycles & 1) > 0 ? 1 : -1);
            result[0] = Math.min(result[0], n);
            result[1] = Math.max(result[1], n);
            return;
        }

        for (int i=0; i<N; i++) {
            if ((visited & (1<<i)) > 0) continue;
            if (i==now) {
                dfs(N, arr, now + 1, point * arr[now][i], visited | (1 << i), result, group, cycles + 1);
                continue;
            }
            int[] nextGroup = Arrays.copyOf(group, N);
            int ig = findG(i, nextGroup);
            int ng = findG(now, nextGroup);

            if (ig == ng) {
                dfs(N, arr, now + 1, point * arr[now][i], visited | (1 << i), result, nextGroup, cycles + 1);
                continue;
            }
            nextGroup[Math.max(ig, ng)] = Math.min(ig, ng);
            dfs(N, arr, now + 1, point * arr[now][i], visited | (1 << i), result, nextGroup, cycles);
        }
    }

    static int findG(int n, int[] group) {
        if (group[n]!=n) group[n] = findG(group[n], group);
        return group[n];
    }
}