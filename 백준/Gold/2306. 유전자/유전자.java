/*
음흠흠
dp로 푸는데 양끝점을 기준으로 만들면 될듯?
만약 양끝이 at or gc로 됐다?
그럼 양끝점 하나하나옮기고 그거 +2로 만들어서 리턴

아니라면 그 사이 전부 체킹
 */

import java.io.*;
import java.util.*;

public class Main {

    static char a = 'a';
    static char t = 't';
    static char g = 'g';
    static char c = 'c';

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();

        System.out.println(solution(s));
    }

    public static int solution(String dna) {
        return find(0, dna.length() - 1, dna, new int[dna.length()][dna.length()]);
    }

    public static int find(int s, int e, String dna, int[][] dp) {
        if (dp[s][e] != 0) return Math.max(dp[s][e], 0);
        if (s >= e) return 0;

        int result = 0;

        if ((dna.charAt(s) == a && dna.charAt(e) == t) || (dna.charAt(s) == g && dna.charAt(e) == c)) {
            result = find(s + 1, e - 1, dna, dp) + 2;
        }

        for (int m = s; m < e; m++) {
            result = Math.max(result, find(s, m, dna, dp) + find(m + 1, e, dna, dp));
        }

        dp[s][e] = result;
        if (dp[s][e] == 0) dp[s][e] = -1;

        return result;
    }
}
