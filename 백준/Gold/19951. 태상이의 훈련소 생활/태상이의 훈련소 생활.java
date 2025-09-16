/*
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[] prefixSum = new int[N + 1];
        int[] arr = new int[N];

        input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(input.nextToken());

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            int s = Integer.parseInt(input.nextToken());
            int e = Integer.parseInt(input.nextToken());
            int v = Integer.parseInt(input.nextToken());

            prefixSum[s - 1] += v;
            prefixSum[e] -= v;
        }

        System.out.println(Arrays.stream(solution(N, M, prefixSum, arr)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static int[] solution(int N, int M, int[] prefixSum, int[] arr) {
        for (int i = 1; i <= N; i++) prefixSum[i] += prefixSum[i - 1];

        for (int i = 0; i < N; i++) arr[i] += prefixSum[i];

        return arr;
    }
}