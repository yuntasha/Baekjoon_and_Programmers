import java.io.*;
import java.util.*;

public class Main {

    static int[][] p = new int[1000][3];

    public static void main(String[] args) throws IOException {

        int N = read();
        int A = read();
        int B = read();

        for (int i = 0; i < N; i++) {
            p[i][0] = read();
            p[i][1] = read();
            p[i][2] = read();
        }

        System.out.println(solution(N, A, B, p));
    }

    static int solution(int N, int A, int B, int[][] p) {
        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (Math.abs(p[j][0] - p[i][0]) < A && Math.abs(p[j][1] - p[i][1]) < B) {
                    result = Math.max(result, Math.abs(p[i][2] - p[j][2]));
                }
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