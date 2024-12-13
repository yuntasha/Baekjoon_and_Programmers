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

        System.out.println(solution(N, arr).stream().map(String::valueOf).collect(Collectors.joining("\n")));
    }

    static List<Integer> solution(int N, int[][] arr) {
        List<Integer> list = new ArrayList<>();
        dfs(N, arr, 0, 1, 0, list, IntStream.range(0, N).toArray(), 0);

        List<Integer> result = new ArrayList<>();
        list.sort(Comparator.naturalOrder());
        result.add(list.get(0));
        result.add(list.get(list.size()-1));

        return result;
    }

    static void dfs(int N, int[][] arr, int now, int point, int visited, List<Integer> list, int[] group, int cycles) {
        if (now == N) {
            list.add(point * ((cycles & 1) > 0 ? 1 : -1));
            return;
        }

        for (int i=0; i<N; i++) {
            if ((visited & (1<<i)) > 0) continue;
            if (i==now) {
                dfs(N, arr, now + 1, point * arr[now][i], visited | (1 << i), list, group, cycles + 1);
                continue;
            }
            int[] nextGroup = Arrays.copyOf(group, N);
            int ig = findG(i, nextGroup);
            int ng = findG(now, nextGroup);

            if (ig == ng) {
                dfs(N, arr, now + 1, point * arr[now][i], visited | (1 << i), list, nextGroup, cycles + 1);
                continue;
            }
            nextGroup[Math.max(ig, ng)] = Math.min(ig, ng);
            dfs(N, arr, now + 1, point * arr[now][i], visited | (1 << i), list, nextGroup, cycles);
        }
    }

    static int findG(int n, int[] group) {
        if (group[n]!=n) group[n] = findG(group[n], group);
        return group[n];
    }
}