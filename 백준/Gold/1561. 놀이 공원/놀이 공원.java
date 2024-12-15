import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static long MAX_VALUE = 60_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long[] arr = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        System.out.println(solution(N, M, arr));
    }

    static long solution(long N, long M, long[] arr) {
        if (N <= M) return N;

        long s = 0L;
        long e = MAX_VALUE;

        while (s+1 < e) {
            long mid = (s+e)/2;

            long result = findN(mid, arr);

            if (result < N) {
                s = mid;
            } else {
                e = mid;
            }
        }

        long idx = N - findN(s, arr);

        for (int i=0; i<M; i++) {
            if (e%arr[i]==0) {
                idx--;
                if (idx == 0) return i+1;
            }
        }

        throw new IllegalArgumentException();
    }

    static long findN(long t, long[] arr) {
        long result = arr.length;
        for (int i=0; i<arr.length; i++) {
            result += t/arr[i];
        }

        return result;
    }
}