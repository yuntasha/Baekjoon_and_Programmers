/*
DP다 그냥 시간 + 이때까지 있던 번호
뭐 암튼 알잖아?
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Work> works = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            works.add(new Work(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }

        System.out.println(solution(N, works));
    }

    static int solution(int N, List<Work> works) {
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            for (int p : works.get(i).prev) {
                dp[i] = Math.max(dp[i], dp[p - 1]);
            }
            dp[i] += works.get(i).t;
        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    static class Work {
        int t;
        List<Integer> prev = new ArrayList<>();

        Work(int[] input) {
            t = input[0];

            for (int i = 0; i < input[1]; i++) {
                prev.add(input[i + 2]);
            }
        }
    }
}