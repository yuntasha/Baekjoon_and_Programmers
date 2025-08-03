/*
Set을 사용해서 빠르게 넣고 뺴자
 */

import java.io.*;
import java.util.*;

public class Main {

    static int MIN = -1_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        int[][] tastes = new int[N][];

        for (int i = 0; i < N; i++) tastes[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, K, tastes));
    }

    public static int solution(int N, int K, int[][] tastes) {
        return find(0, new HashSet<>(), N, K, tastes);
    }

    public static int find(int n, Set<Integer> set, int N, int K, int[][] tastes) {
        if (n == N) {
            if (set.size() != K) return MIN;
            return getTaste(set, tastes);
        }

        int result = find(n + 1, set, N, K, tastes);

        if (set.size() < K) {
            set.add(n);
            result = Math.max(result, find(n + 1, set, N, K, tastes));
            set.remove(n);
        }

        return result;
    }

    public static int getTaste(Set<Integer> set, int[][] tastes) {
        int result = 0;
        for (int i : set) {
            for (int j : set) {
                result += tastes[i][j];
            }
        }

        return result >> 1;
    }
}