/*
갈수록 점점 커짐
해당 숫자에서 이길 수 있는지 여부가 나올거임
상대가 전부 이긴다면 지는거고 이기는게 하나라도 있으면 걔가 이김
 */

import java.io.*;
import java.util.*;

public class Main {

    static int WIN = 1;
    static int DEFEAT = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        Set<Integer> set = new HashSet<>();

        input = new StringTokenizer(bf.readLine());

        while (input.hasMoreTokens()) {
            set.add(Integer.parseInt(input.nextToken()));
        }

        System.out.println(solution(N, K, set));
    }

    public static int solution(int N, int K, Set<Integer> set) {
        return isWin(0, new int[N + 1], N, K, set) ? 1 : 0;
    }

    public static boolean isWin(int n, int[] dp, int N, int K, Set<Integer> set) {
        if (dp[n] > 0) return dp[n] == WIN;
        boolean result = false;

        for (int i = n + 1; i <= Math.min(n + K, N); i++) {
            if (set.contains(i)) continue;
            result |= !isWin(i, dp, N, K, set);
        }

        if (result) dp[n] = WIN;
        else dp[n] = DEFEAT;

        return dp[n] == WIN;
    }
}