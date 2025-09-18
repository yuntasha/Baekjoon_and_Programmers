/*
정렬 정렬 정렬
앞에서부터 가장 작은거부터 계속 다음꺼로 올려
그거 반복해

A, B 2개 2중 for문 돌리고
C로 이분탐색 때려버려

이거 돌려서 해버려

1000 * 1000 * 3 * 10
30_000_000
 */

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int A = Integer.parseInt(input.nextToken());
        int B = Integer.parseInt(input.nextToken());
        int C = Integer.parseInt(input.nextToken());

        int[] aArr = new int[A];
        int[] bArr = new int[B];
        int[] cArr = new int[C];

        input = new StringTokenizer(bf.readLine());
        for (int i = 0; i < A; i++) aArr[i] = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(bf.readLine());
        for (int i = 0; i < B; i++) bArr[i] = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(bf.readLine());
        for (int i = 0; i < C; i++) cArr[i] = Integer.parseInt(input.nextToken());

        System.out.println(solution(A, B, C, aArr, bArr, cArr));
    }

    public static int solution(int A, int B, int C, int[] aArr, int[] bArr, int[] cArr) {
        int result = Integer.MAX_VALUE;

        Arrays.sort(aArr);
        Arrays.sort(bArr);
        Arrays.sort(cArr);

        result = Math.min(result, find(A, B, C, aArr, bArr, cArr));
        result = Math.min(result, find(A, C, B, aArr, cArr, bArr));
        result = Math.min(result, find(C, B, A, cArr, bArr, aArr));

        return result;
    }

    public static int find(int A, int B, int C, int[] aArr, int[] bArr, int[] cArr) {
        int result = Integer.MAX_VALUE;

        for (int a : aArr) {
            for (int b : bArr) {
                if (isPossible(Math.min(a, b), Math.max(a, b), cArr)) result = Math.min(result, Math.abs(a - b));
            }
        }

        return result;
    }

    public static boolean isPossible(int min, int max, int[] arr) {
        return findIdxLeft(min, arr) <= findIdxRight(max, arr);
    }

    // n <= x
    public static int findIdxLeft(int n, int[] arr) {
        int N = arr.length;
        if (arr[0] > n) return 0;
        if (arr[N - 1] < n) return N;

        int s = 0;
        int e = N - 1;

        while (s + 1 < e) {
            int m = (s + e) >> 1;

            if (arr[m] < n) s = m;
            else e = m;
        }

        return e;
    }

    // x <= n
    public static int findIdxRight(int n, int[] arr) {
        int N = arr.length;
        if (arr[0] > n) return -1;
        if (arr[N - 1] < n) return N - 1;

        int s = 0;
        int e = N - 1;

        while (s + 1 < e) {
            int m = (s + e) >> 1;

            if (arr[m] <= n) s = m;
            else e = m;
        }

        return s;
    }
}