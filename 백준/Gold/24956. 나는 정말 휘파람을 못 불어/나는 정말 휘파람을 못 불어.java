/*
E가 나오면 앞에 WHE가 몇개인지
W나오면 W추가
H나오면 앞의 W개수만큼 WH 추가
E나오면 앞의 WH개수만큼 WHE 추가 & WHE 숫자만큼 WHEE개수 추가
E가 여러개가 나올 수 있네
 */

import java.io.*;
import java.util.*;

public class Main {

    static int MAX = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        char[] s = bf.readLine().toCharArray();

        System.out.println(solution(N, s));
    }

    static int solution(int N, char[] s) {
        int W = 0;
        int WH = 0;
        int WHE = 0;
        int WHEE = 0;

        for (int i = 0; i < N; i++) {
            if (s[i] == 'W') {
                W++;
                W %= MAX;
            } else if (s[i] == 'H') {
                WH += W;
                WH %= MAX;
            } else if (s[i] == 'E') {
                WHEE += WHEE;
                WHEE %= MAX;
                WHEE += WHE;
                WHEE %= MAX;

                WHE += WH;
                WHE %= MAX;
            }
        }

        return WHEE;
    }
}

//2 3 7 5