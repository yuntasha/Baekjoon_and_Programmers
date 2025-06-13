/*
1000 * 1000이잖아
그러면 1_000_000_000인데
개오바임
이거 카데인 알고리즘이랑 비슷하네
앞에부터 따라오는게 최대냐 아니면 위에서 바로 내려오는게 최대냐 이거로 dp 한번 더 엮어주는 문제
구간 합이라고 생각하면 편함
특정 위치에서 끝나는 구간합 중 최댓값 구하는 문제
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(solution(getI(), getI()));
    }

    static int solution(int N, int M) throws IOException {
        int[] result = new int[M];
        int[] map = new int[M];

        for (int j = 0; j < M; j++) map[j] = read();
        result[0] = map[0];

        for (int i = 1; i < M; i++) {
            result[i] = map[i] + result[i - 1];
        }

        int[] r = new int[M];
        for (int i = 1; i < N; i++) {
            map[0] = read();
            r[0] = result[0] + map[0];
            for (int j = 1; j < M; j++) {
                map[j] = read();
                r[j] = map[j] + Math.max(r[j - 1], result[j]);
            }

            int prev = result[M - 1] + map[M - 1];
            result[M - 1] = Math.max(r[M - 1], prev);
            for (int j = M - 2; j >= 0; j--) {
                prev = map[j] + Math.max(prev, result[j]);
                result[j] = Math.max(prev, r[j]);
            }
        }

        return result[M - 1];
    }

    static int read() throws IOException {
        int n = System.in.read();
        return n == '-' ? -getI(0) : getI(n & 15);
    }

    static int getI(int n) throws IOException {
        int c;
        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
    static int getI() throws IOException {
        int n = 0;
        int c;
        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}