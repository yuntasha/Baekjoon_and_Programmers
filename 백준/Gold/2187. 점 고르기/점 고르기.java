import java.io.*;
import java.util.*;

public class Main {

    static int[] x = new int[1000];
    static int[] y = new int[1000];
    static int[] v = new int[1000];

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int A = Integer.parseInt(input.nextToken());
        int B = Integer.parseInt(input.nextToken());

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            x[i] = Integer.parseInt(input.nextToken());
            y[i] = Integer.parseInt(input.nextToken());
            v[i] = Integer.parseInt(input.nextToken());
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
}