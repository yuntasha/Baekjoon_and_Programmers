/*
각 위치에서 싸이클 어떻게 되는지 구하면 될 것 같은데
그리고 최소공배수 대충 만들면 될 듯
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N + 1];

        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(input.nextToken());
        }

        System.out.print(solution(N, arr));
    }

    public static int solution(int N, int[] arr) {
        int result = 1;

        int[] visited = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (visited[i] > 0) continue;
            find(arr[i], i, arr, visited, 1);
            result = add(result, visited[i]);
        }

        return result;
    }

    public static int add(int a, int b) {
        int g = gcd(Math.max(a, b), Math.min(a, b));
        return a / g * b;
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static int find(int n, int first, int[] arr, int[] visited, int cnt) {
        if (n == first) return visited[n] = cnt;
        return visited[n] = find(arr[n], first, arr, visited, cnt + 1);
    }
}