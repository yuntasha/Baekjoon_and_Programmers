/*
Map에 set넣으먄 괜찮겠지?
128MB는 안전할듯?
그냥 52 52배열 나올것 같은디
dfs로 그냥 들어갔다가 음..
플워로 안되나
되는구나
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        boolean[][] isT = new boolean[52][52];

        for (int i = 0; i < N; i++) {
            String[] s = bf.readLine().split(" ");
            isT[convert(s[0].charAt(0))][convert(s[2].charAt(0))] = true;
        }

        System.out.print(solution(N, isT));
    }

    public static String solution(int N, boolean[][] isT) {
        for (int m = 0; m < 52; m++) {
            for (int s = 0; s < 52; s++) {
                for (int e = 0; e < 52; e++) {
                    isT[s][e] |= isT[s][m] && isT[m][e];
                }
            }
        }

        StringBuilder output = new StringBuilder();

        int count = 0;

        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                if (i == j) continue;
                if (!isT[i][j]) continue;
                output.append(convert(i)).append(" => ").append(convert(j)).append("\n");
                count++;
            }
        }

        return count + "\n" + output.toString();
    }

    public static int convert(char c) {
        return c >= 'a' ? c - 'a' + 26 : c - 'A';
    }

    public static char convert(int i) {
        return i >= 26 ? (char) (i - 26 + 'a') : (char) (i + 'A');
    }
}