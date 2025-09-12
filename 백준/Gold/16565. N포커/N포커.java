/*
NC1
1 ~ 13까지 나오는거
13 C i * 52-4i C n-4i
그럼 2개가 겹치는게 2개씩 만들어지고 3개는 3개 4개는 4개?
2를 따로 처리
콤비네이션 정리중에 i C j = i-1 C j + i-1 C j-1 이런게 있음
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    static int MOD = 10_007;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    public static int solution(int N) {
        int[][] comb = new int[53][53];

        for (int i = 0; i <= 52; i++) comb[i][0] = 1;

        for (int i = 1; i <= 52; i++) {
            for (int j = 1; j <= 52; j++) comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % MOD;
        }

        int result = 0;

        for (int i = 1; i <= N / 4; i++) {
            if ((i & 1) == 1) result = (result + comb[13][i] * comb[52 - (i << 2)][N - (i << 2)]) % MOD;
            else result = (result - (comb[13][i] * comb[52 - (i << 2)][N - (i << 2)]) % MOD + MOD) % MOD;
        }

        return result;
    }
}