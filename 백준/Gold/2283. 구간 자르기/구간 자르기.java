/*
투포인터에 누적합처럼해보자
 */

import java.io.*;
import java.util.*;

public class Main {

    static int MAX = 1_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        int[] sum = new int[MAX + 1];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());

            sum[Integer.parseInt(input.nextToken())]++;
            sum[Integer.parseInt(input.nextToken())]--;
        }

        System.out.print(solution(N, K, sum));
    }

    public static String solution(int N, int K, int[] arr) {
        int s = 0;
        int e = 0;

//        System.out.println(Arrays.toString(arr));

        for (int i = 1; i <= MAX; i++) arr[i] += arr[i - 1];

//        System.out.println(Arrays.toString(arr));

        int sum = 0;

        while (s <= e && e <= MAX) {
            if (sum == K) {
                return s + " " + e;
            }

            if (sum < K) {
                sum += arr[e++];
            } else {
                sum -= arr[s++];
            }
//            System.out.println("sum = " + sum);
        }

        return "0 0";
    }
}

