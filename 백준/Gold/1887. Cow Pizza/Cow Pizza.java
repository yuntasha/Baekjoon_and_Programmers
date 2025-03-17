/*
2의 20승은 1,000,000
백만이다 대충 200만이라고 생각하고 ㄱㄱ...
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        int T = read();
        int N = read();

        int[] set = new int[N];

        for (int i = 0; i < N; i++) {
            int n = read();

            for (int j = 0; j < n; j++) {
                set[i] |= (1 << (read() - 1));
            }
        }

        System.out.println(solution(T, N, set));
    }

    public static int solution(int T, int N, int[] set) {
        int result = 0;
        int max = (1 << T);
        Loop : for (int i = 0; i < max; i++) {
            for (int s : set) {
                if ((s & i) == s) continue Loop;
            }
            result++;
        }
        return result;
    }

    public static int read() throws IOException {
        int n = 0;
        int c;
        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}