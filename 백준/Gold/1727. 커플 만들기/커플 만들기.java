import java.io.*;
import java.util.*;

/*
DP[x][y] = min(DP[x][y-1], DP[x-1][y-1] + Math.abs())
n이 x명이고 m이 y명일때
x > y 는 불가능 = MAX로 만들기

 */

public class Main {

    static final int MAX = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[] arr1 = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] arr2 = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (N > M) {
            int temp = N;
            N = M;
            M = temp;

            int[] ter = arr1;
            arr1 = arr2;
            arr2 = ter;
        }

        System.out.println(solution(N, M, arr1, arr2));
    }

    static int solution(int N, int M, int[] arr1, int[] arr2) {
        int[][] DP = new int[N + 1][M + 1];

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        for (int n = 1; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n > m) DP[n][m] = MAX;
                else DP[n][m] = Math.min(DP[n][m-1], DP[n-1][m-1] + Math.abs(arr1[n-1] - arr2[m-1]));
            }
        }

        return DP[N][M];
    }
}