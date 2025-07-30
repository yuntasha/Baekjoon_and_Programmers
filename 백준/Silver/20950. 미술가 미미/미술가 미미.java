/*
개수, rgb각각의 합
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int MAX = 7;
    static int R = 0;
    static int G = 1;
    static int B = 2;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] colors = new int[N][];

        for (int i = 0; i < N; i++) colors[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] gom = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, colors, gom));
    }

    public static int solution(int N, int[][] colors, int[] gom) {
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            result = Math.min(result, find(1, colors[i][R], colors[i][G], colors[i][B], i, N, colors, gom));
        }

        return result;
    }

    public static int find(int n, int r, int g, int b, int prev, int N, int[][] colors, int[] gom) {
        int min = Integer.MAX_VALUE;

        if (n > 1) min = get(n, r, g, b, gom);

        if (n == Math.min(MAX, N)) return min;

        for (int i = prev + 1; i < N; i++) {
            min = Math.min(min, find(n + 1, r + colors[i][R], g + colors[i][G], b + colors[i][B], i, N, colors, gom));
        }

        return min;
    }

    public static int get(int n, int r, int g, int b, int[] gom) {
        return Math.abs(r / n - gom[R]) + Math.abs(g / n - gom[G]) + Math.abs(b / n - gom[B]);
    }
}