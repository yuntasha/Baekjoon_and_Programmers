/*
작은 수부터 채운다고 생각

 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static final int MAX = 1 << 15;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] result = solution();

        int n;
        StringBuilder output = new StringBuilder();

        while ((n = Integer.parseInt(bf.readLine())) > 0) {
            output.append("\n").append(result[n]);
        }

        System.out.println(output.substring(1));
    }

    public static int[] solution() {
        int[][] dp = new int[MAX + 1][5];

        dp[0][0] = 1;

        for (int i = 1; i * i <= MAX; i++) {
            for (int s = 0; s + (i * i) <= MAX; s++) {
                for (int t = 0; t < 4; t++) {
                    dp[s + (i * i)][t + 1] += dp[s][t];
                }
            }
        }

        int[] result = new int[MAX + 1];

        for (int i = 1; i <= MAX; i++) result[i] = dp[i][1] + dp[i][2] +dp[i][3] +dp[i][4];

        return result;
    }
}