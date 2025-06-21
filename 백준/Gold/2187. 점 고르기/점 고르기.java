import java.io.*;

public class Main {

    static int[] x = new int[1000];
    static int[] y = new int[1000];
    static int[] v = new int[1000];

    public static void main(String[] args) throws IOException {

        int N = read();
        int A = read();
        int B = read();

        for (int i = 0; i < N; i++) {
            x[i] = read();
            y[i] = read();
            v[i] = read();
        }

        System.out.println(solution(N, A, B, x, y, v));
    }

    static int solution(int N, int A, int B, int[] x, int[] y, int[] v) {
        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (Math.abs(x[j] - x[i]) < A && Math.abs(y[j] - y[i]) < B) {
                    result = Math.max(result, Math.abs(v[i] - v[j]));
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