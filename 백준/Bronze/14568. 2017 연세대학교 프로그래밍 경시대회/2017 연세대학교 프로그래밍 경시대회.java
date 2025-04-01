/*
양은 한마리당 a그램
염소는 한마리당 b그램
전체 n마리
소비한 사료 w그램
*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    public static int solution(int N) {
        int result = 0;

        for (int y = 1; y < N; y++) {
            for (int n = y + 2; n < N; n++) {
                if (y + n >= N) continue;
                if (((N - y - n) & 1) == 1) continue;
                result++;
            }
        }

        return result;
    }
}