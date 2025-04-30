import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        StringBuilder output = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(input.nextToken());

            long[] arr = new long[N];

            for (int i = 0; i < N; i++) {
                arr[i] = Long.parseLong(input.nextToken());
            }

            output.append(solution(N, arr)).append("\n");
        }

        System.out.print(output);
    }

    static long solution(int N, long[] arr) {
        long result = 0;

        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                result += gcd(arr[j], arr[i]);
            }
        }

        return result;
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}