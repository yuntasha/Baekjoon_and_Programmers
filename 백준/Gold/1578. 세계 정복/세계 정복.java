import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        long[] arr = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        System.out.println(solution(N, K, arr));
    }

    static long solution(long N, long K, long[] arr) {
        if (N<K) return 0;
        long s = 0L;
        long e = Arrays.stream(arr).sum();

        while (s+1 < e) {
            long m = (s+e)/2;

            if (chk(K, arr, m)) {
                s = m;
            } else {
                e = m;
            }
        }

        return s;
    }

    static boolean chk(long K, long[] arr, long g) {
        long result = 0L;

        for (long i : arr) {
            result += Math.min(i, g); // 각 나라에서 뽑을 수 있는 사람 수
        }

        return g * K <= result;
    }
}