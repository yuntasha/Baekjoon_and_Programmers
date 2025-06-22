/*
100 * 100으로 10_000개의 좌표가 생성
해당 좌표를 기준으로 우상향으로 만들어버리자
100만정도 될듯
 */

import java.io.*;
import java.util.*;

public class Main {

    static long[][] p = new long[100][2];

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        long A = Long.parseLong(input.nextToken());
        long B = Long.parseLong(input.nextToken());

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            p[i][0] = Long.parseLong(input.nextToken());
            p[i][1] = Long.parseLong(input.nextToken());
        }

        System.out.println(solution(N, A, B, p));
    }

    static int solution(int N, long A, long B, long[][] p) {
        int result = 0;

        HashSet<Long> x = new HashSet<>();
        HashSet<Long> y = new HashSet<>();

        for (int i = 0; i < N; i++) {
            x.add(p[i][0]);
            y.add(p[i][1]);
        }

        for (long i : x) {
            for (long j : y) {
                int now = 0;
                for (int n = 0; n < N; n++) {
                    if (i <= p[n][0] && p[n][0] <= i + A && j <= p[n][1] && p[n][1] <= j + B) now++;
                }
                result = Math.max(result, now);
            }
        }

        return result;
    }

    static int read() throws IOException {
        int n = 0;
        int c;

        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}