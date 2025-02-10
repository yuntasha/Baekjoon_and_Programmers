import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        long N = Long.parseLong(input.nextToken());
        long M = Long.parseLong(input.nextToken());

        long[] arr = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        System.out.println(solution(N, M, arr));
    }

    static long solution(long N, long M, long[] arr) {
        long s = 0;
        long e = Arrays.stream(arr).max().orElse(0L) * M;

        while (s + 1 < e) {
            long m = (s + e) >> 1;

            if (isP(N, M, m, arr)) {
                e = m;
            } else {
                s = m;
            }
        }

        return e;
    }

    static boolean isP(long N, long M, long n, long[] arr) {
        long result = 0L;

        for (long l : arr) {
            result += n/l;
        }

        return result >= M;
    }
}