/*
백트래킹 완탐...
32 * 1000
ㅋㅋ
2^5
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int set = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] cutLine = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] foods = new int[N][];

        for (int i = 0; i < N; i++) {
            foods[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, cutLine, foods));
    }

    public static String solution(int N, int[] cutLine, int[][] foods) {
        String result = "";
        int min = Integer.MAX_VALUE;
        for (int bit = 1; bit < (1 << N); bit++) {
            int[] now = new int[5];

            for (int i = 0; i < N; i++) {
                if ((bit & (1 << i)) == 0) continue;
                now[0] += foods[i][0];
                now[1] += foods[i][1];
                now[2] += foods[i][2];
                now[3] += foods[i][3];
                now[4] += foods[i][4];
            }

            if (now[0] >= cutLine[0] && now[1] >= cutLine[1] && now[2] >= cutLine[2] && now[3] >= cutLine[3]) {
                String s = convert(bit, N);
                if (min > now[4] || (min == now[4] && s.compareTo(result) < 0)) {
                    min = now[4];
                    result = s;
                }
            }
        }

        if (min == Integer.MAX_VALUE) return "-1";
        return min + "\n" + result;
    }

    public static String convert(int bit, int N) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            if ((bit & (1 << i)) == 0) continue;
            sb.append(" ").append(i + 1);
        }

        return sb.substring(1);
    }
}