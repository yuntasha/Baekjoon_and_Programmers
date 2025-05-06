/*
이거 카데인알고리즘아니냐?
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {
        int[] dp = new int[N];

        dp[0] = arr[0];

        int result = arr[0];

        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + arr[i];
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}