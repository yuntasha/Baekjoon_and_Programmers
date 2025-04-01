/*
그냥 각 시간에 몇명이 활동하는지 적어두자
그리고 한명씩 빼보면서 몇시간 빠지는지 확인하고 최소값 찾기
그리고 전체 활동 시간에서 빼버리자
*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] p = new int[N][2];

        int[] t = new int[1001];

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            p[i][0] = Integer.parseInt(input.nextToken());
            p[i][1] = Integer.parseInt(input.nextToken());

            for (int j = p[i][0]; j < p[i][1]; j++) {
                t[j]++;
            }
        }

        System.out.println(solution(N, p, t));
    }

    public static int solution(int N, int[][] ps, int[] t) {
        int result = 1000000;

        for (int[] p : ps) {
            int now = 0;
            for (int i = p[0]; i < p[1]; i++) {
                if (t[i] == 1) now++;
            }
            result = Math.min(result, now);
        }

        result = -result;

        for (int i = 0; i < 1001; i++) {
            if (t[i] > 0) result++;
        }

        return result;
    }
}