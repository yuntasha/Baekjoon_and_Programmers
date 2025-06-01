import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
1_000_000_000_000
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(bf.readLine());
        System.out.println(solution(N));
    }

    public static String solution(long N) {
        List<Long> result = new ArrayList<>();
        for (long i = 2; i < Math.sqrt(N); i++) {
            while (N % i == 0) {
                result.add(i);
                N /= i;
            }
        }
        if (N > 1) result.add(N);

        return result.size() + "\n" + result.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }
}