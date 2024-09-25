import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static char[][] dict = new char[200_000][9];
    static int N = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            var now = bf.readLine().strip();
            if (now.equals("-")) break;
            dict[N] = now.toCharArray();
            Arrays.sort(dict[N++]);
        }

        while (true) {
            var now = bf.readLine().strip();
            if (now.equals("#")) break;

            System.out.println(solution(now.toCharArray()));
        }
    }



    static String solution(char[] puzzle) {
        Arrays.sort(puzzle);
        int[] result = new int[26];
        for (int i=0; i<N; i++) {
            var pIdx = 0;
            var aIdx = 0;
            var now = true;
            for (char c: dict[i]) {
                while (pIdx < 9 && puzzle[pIdx]!=c) pIdx++;
                if (pIdx < 9 && puzzle[pIdx]==c) pIdx++;
                else {
                    now = false;
                    break;
                }
            }

            if (now) {
                for (int j=0; j<dict[i].length; j++) {
                    if (j>0 && dict[i][j]==dict[i][j-1]) continue;
                    result[dict[i][j]-'A']++;
                }
            }
         }
        var maxW = new StringBuilder();
        var minW = new StringBuilder();
        var maxV = 0;
        var minV = Integer.MAX_VALUE;

        for (int j=0; j<9; j++) {
            var c = puzzle[j];
            if (j>0 && puzzle[j]==puzzle[j-1]) continue;
            if (minV>result[c-'A']) {
                minV = result[c-'A'];
                minW = new StringBuilder();
                minW.append(c);
            } else if (minV==result[c-'A']) {
                minW.append(c);
            }

            if (maxV<result[c-'A']) {
                maxV = result[c-'A'];
                maxW = new StringBuilder();
                maxW.append(c);
            } else if (maxV==result[c-'A']) {
                maxW.append(c);
            }
        }
        return minW.toString() + " " + minV + " " + maxW.toString() + " " + maxV;
    }
}