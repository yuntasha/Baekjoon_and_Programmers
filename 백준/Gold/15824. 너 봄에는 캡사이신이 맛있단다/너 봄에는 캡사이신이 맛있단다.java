import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        long[] arr = new long[N];

        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(input.nextToken());
        }

        System.out.println(solution(N, arr));
    }

    public static long solution(int N, long[] arr) {
        Arrays.sort(arr);

        long[] prefixSum = new long[N];

        long now = 0;

        for (int i = 0; i < N; i++) {
            now += arr[N - i - 1] - arr[i];
            now %= MOD;
            prefixSum[i] = now;
        }

        long result = 0;
        long multi = 1;

        for (int l = 2; l <= N; l++) {
            int idx = Math.min(N - l, l - 2);
            long v = (prefixSum[idx] * multi) % MOD;
            result += v;
            result %= MOD;
            multi = multi << 1;
            multi %= MOD;
        }

        return result;
    }
}