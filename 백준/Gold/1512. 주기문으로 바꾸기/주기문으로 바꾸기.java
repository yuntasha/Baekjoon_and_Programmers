import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var M = Integer.parseInt(bf.readLine());

        var s = bf.readLine().toCharArray();

        System.out.println(solution(M, s));
    }

    static int solution(int M, char[] s) {
        var result = Integer.MAX_VALUE;

        for (int m=1; m<=M; m++) {
            result = Math.min(result, find(m, s));
        }

        return result;
    }

    static int find(int m, char[] s) {
        var dp = new int[m][4];

        for (int i=0; i<s.length; i++) {
            var now = chk(s[i]);
            for (int c=0; c<4; c++) {
                if (c==now) continue;
                dp[i%m][c]++;
            }
        }

        var result = 0;

        for (int i=0; i<m; i++) {
            var now = Integer.MAX_VALUE;
            for (int c=0; c<4; c++) {
                now = Math.min(now, dp[i][c]);
            }
            result += now;
        }

        return result;
    }

    static int chk(char c) {
        if (c=='A') return 0;
        if (c=='C') return 1;
        if (c=='G') return 2;
        if (c=='T') return 3;

        throw new IllegalArgumentException();
    }
}