/*
모든 단어는 a-z 소문자로 이루어져있음
그냥 큐로 싹다 밀어붙이면 안되나
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int L = Integer.parseInt(input.nextToken());

        char[] s = bf.readLine().toCharArray();
        List<String> words = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            words.add(bf.readLine());
        }

        System.out.println(solution(N, L, s, words));
    }

    static int solution(int N, int L, char[] s, List<String> words) {
        int[] dp = new int[L + 1];

        for (int i = 0; i <= L; i++) {
            dp[i] = i;
        }

        for (int e = 1; e <= L; e++) {
            for (int i = 0; i < N; i++) {
                char[] word = words.get(i).toCharArray();

                int sIdx = e - 1;
                int wIdx = word.length - 1;
                int del = 0;

                while (sIdx >= 0 && wIdx >= 0) {
                    if (s[sIdx] == word[wIdx]) wIdx--;
                    else del++;
                    sIdx--;
                    if (wIdx == -1) {
                        dp[e] = Math.min(dp[e], dp[sIdx + 1] + del);
                        break;
                    }
                }
            }
        }

        return dp[L];
    }
}