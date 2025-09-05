/*
싸이클 도는 애들이 몇개인지 구하는 느낌인데
각각을 완탐 돌려도 100 * 100
그냥 DFS로 돌려보면 될듯?

정답은 TreeSet에 넣고 한번에 처리하자
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        System.out.print(solution(N, arr));
    }

    public static String solution(int N, int[] arr) {
        Set<Integer> result = new TreeSet<>();

        for (int i = 1; i <= N; i++) {
            find(arr[i], i, arr, new boolean[N + 1], result);
        }

        return result.size() + "\n" + result.stream().map(String::valueOf).collect(Collectors.joining("\n"));
    }

    public static boolean find(int n, int first, int[] arr, boolean[] visited, Set<Integer> result) {
        if (n == first) {
            result.add(n);
            return true;
        }

        if (visited[n]) return false;
        visited[n] = true;

        if (find(arr[n], first, arr, visited, result)) {
            result.add(n);
            return true;
        }

        return false;
    }
}