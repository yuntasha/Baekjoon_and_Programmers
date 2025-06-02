import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
1_000_000_000_000
 */

public class Main {

    static long[] REVERSE = {0, 1, 2, -1, -1, 5, 9, -1, 8, 6};
    static String SUCCESS = "yes";
    static String FAIL = "no";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(bf.readLine());

        System.out.println(solution(N) ? SUCCESS : FAIL);
    }

    public static boolean solution(long N) {
        if (!isS(N)) return false;
        long r = getReverse(N);
        if (r == -1) return false;
        return isS(r);
    }

    static long getReverse(long N) {
        long result = 0;
        while (N > 0) {
            result *= 10;
            result += REVERSE[(int) (N % 10)];
            if (REVERSE[(int) (N % 10)] == -1) return -1;
            N /= 10;
        }

        return result;
    }

    static boolean isS(long N) {
        if (N == 1) return false;
        for (long i = 2; i <= Math.sqrt(N); i++){
            if (N % i == 0) return false;
        }
        return true;
    }
}