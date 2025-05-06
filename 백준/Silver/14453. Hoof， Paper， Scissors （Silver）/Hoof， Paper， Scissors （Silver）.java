/*
각 가위바위보의 개수를 세어줌
그리고 특정 인덱스를 기준으로 한번 바꿔보자
 */

import java.io.*;
import java.util.*;

public class Main {

    static char[] HPS = {'H', 'P', 'S'};


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] hpsCount = new int[3][N];

        char c = bf.readLine().charAt(0);

        for (int i = 0; i < 3; i++) {
            if (c == HPS[i]) hpsCount[i][0]++;
        }

        for (int i = 1; i < N; i++) {
            c = bf.readLine().charAt(0);
            hpsCount[0][i] = hpsCount[0][i - 1];
            hpsCount[1][i] = hpsCount[1][i - 1];
            hpsCount[2][i] = hpsCount[2][i - 1];

            for (int j = 0; j < 3; j++) {
                if (c == HPS[j]) hpsCount[j][i]++;
            }
        }

        System.out.println(solution(N, hpsCount));
    }

    static int solution(int N, int[][] hpsCount) {
        int result = 0;

        for (int i = 0; i < N; i++) {
            result = Math.max(result, Math.max(hpsCount[0][N - 1] - hpsCount[0][i], Math.max(hpsCount[1][N - 1] - hpsCount[1][i], hpsCount[2][N - 1] - hpsCount[2][i])) + Math.max(hpsCount[0][i], Math.max(hpsCount[1][i], hpsCount[2][i])));
        }

        return result;
    }
}