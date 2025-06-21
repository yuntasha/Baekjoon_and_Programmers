import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int A = Integer.parseInt(input.nextToken());
        int B = Integer.parseInt(input.nextToken());

        int[] x = new int[N];
        int[] y = new int[N];
        int[] v = new int[N];

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