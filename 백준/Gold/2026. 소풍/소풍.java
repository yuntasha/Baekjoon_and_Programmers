/*
N명중 K명 뽑기
친구 관계 K명인 애들 뽑기
그럼 그냥 BFS로해도 괜찮을 것 같은데
근데 유니온파인드가 더 빠를듯?
아 이게 1, 2가 친구고 2, 3이 친구라면 1, 3친구가 확정은 아니구나
이게 되는게 빡세니까 백트래킹
이거 근데 어떻게 알라고;;;

 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int K = Integer.parseInt(input.nextToken());
        int N = Integer.parseInt(input.nextToken());
        int F = Integer.parseInt(input.nextToken());

        boolean[][] friend = new boolean[N + 1][N + 1];

        for (int i = 0; i < F; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            friend[a][b] = friend[b][a] = true;
        }

        System.out.println(solution(K, N, F, friend));
    }

    static String solution(int K, int N, int F, boolean[][] friend) {
        List<Integer> group = new ArrayList<>();

        if (!find(0, K, N, friend, group)) return "-1";

        return group.stream().map(String::valueOf).collect(Collectors.joining("\n"));
    }

    static boolean find(int k, int K, int N, boolean[][] friend, List<Integer> group) {
        if (k == K) return true;
        int start = getFirst(group);
        if (start == N + 1) return false;

        for (int i = start; i <= N; i++) {
            if (!isOk(i, group, friend)) continue;
            group.add(i);
            if (find(k + 1, K, N, friend, group)) return true;
            group.remove(group.remove(group.size() - 1));
        }

        return false;
    }

    static boolean isOk(int n, List<Integer> group, boolean[][] friend) {
        for (int i : group) {
            if (!friend[n][i]) return false;
        }
        return true;
    }

    static int getFirst(List<Integer> group) {
        return group.isEmpty() ? 1 : (group.get(group.size() - 1) + 1);
    }
}