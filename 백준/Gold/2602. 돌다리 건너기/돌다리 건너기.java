/*
음
우선은 뭐 완탐은 2^100 ㅌㅌ
근데 음
문자 위치, 현재 위치, 다리 종류
이렇게면 끝

문자 -> 다리 위치 -> 종류
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        char[] seq = bf.readLine().toCharArray();

        char[][] bridges = {bf.readLine().toCharArray(), bf.readLine().toCharArray()};

        System.out.println(solution(seq, bridges));
    }

    public static int solution(char[] seq, char[][] bridges) {
        int[][][] dp = new int[2][seq.length][bridges[0].length];

        for (int bIdx = 0; bIdx < bridges[0].length; bIdx++) {
            for (int bType = 0; bType < 2; bType++) {
                if (seq[0] == bridges[bType][bIdx]) dp[bType][0][bIdx] = 1;
            }
        }

        for (int seqIdx = 1; seqIdx < seq.length; seqIdx++) {
            for (int bIdx = 0; bIdx < bridges[0].length - 1; bIdx++) {
                for (int bType = 0; bType < 2; bType++) {
                    if (dp[bType][seqIdx - 1][bIdx] == 0) continue;
                    for (int i = bIdx + 1; i < bridges[0].length; i++) {
                        if (bridges[bType ^ 1][i] == seq[seqIdx]) dp[bType ^ 1][seqIdx][i] += dp[bType][seqIdx - 1][bIdx];
                    }
                }
            }
        }

        int result = 0;

        for (int i = 0; i < bridges[0].length; i++) {
            result += dp[0][seq.length - 1][i] + dp[1][seq.length - 1][i];
        }

        return result;
    }
}
