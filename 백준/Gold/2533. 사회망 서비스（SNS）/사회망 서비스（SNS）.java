/*
내가 얼리어답터가 아니면 나랑 연결된 애들은 전부 얼리어답터여야함
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<List<Integer>> friend = new ArrayList<>(N + 1);

        friend.add(null);

        for (int i = 0; i < N; i++) friend.add(new ArrayList<>());

        for (int i = 1; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            friend.get(a).add(b);
            friend.get(b).add(a);
        }

        System.out.println(solution(N, friend));
    }

    public static int solution(int N, List<List<Integer>> friend) {
        int[][] dp = new int[N + 1][2];

        return Math.min(find(1, 0, 0, dp, friend), find(1, 1, 0, dp, friend));
    }

    public static int find(int n, int ed, int prev, int[][] dp, List<List<Integer>> friend) {
        if (dp[n][ed] != 0) return Math.max(dp[n][ed], 0);

        int result = 0;

        if (ed == 0) {
            for (int f : friend.get(n)) {
                if (f == prev) continue;
                result += find(f, 1, n, dp, friend);
            }
        } else {
            for (int f : friend.get(n)) {
                if (f == prev) continue;
                result += Math.min(find(f, 0, n, dp, friend), find(f, 1, n, dp, friend));
            }
            result++;
        }

        dp[n][ed] = result;
        if (result == 0) dp[n][ed] = -1;

        return result;
    }
}
