import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static long TIME = 100_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var C = Integer.parseInt(bf.readLine());
        for (int c=0; c<C; c++) {
            var s = bf.readLine().split(" ");
            System.out.println(solution(s[0], Long.parseLong(s[1]), Long.parseLong(s[2]), Long.parseLong(s[3]))?"May Pass.":"TLE!");
        }
    }

    static boolean solution(String S, long N, long T, long L) {
        switch (S) {
            case "O(N)":
                return N * T <= L * TIME;
            case "O(N^2)":
                if (N > 100000) {
                    return false;
                }
                return N * N * T <= L * TIME;
            case "O(N^3)":
                if (N > 1000) {
                    return false;
                }
                return N * N * N * T <= L * TIME;
            case "O(2^N)": {
                if (N > 30) {
                    return false;
                }
                var now = 1L;
                for (long l = 0L; l < N; l++) {
                    now *= 2L;
                }
                return now * T <= L * TIME;
            }
            case "O(N!)": {
                var now = 1L;
                if (N > 13) {
                    return false;
                }
                for (long l = 1L; l <= N; l++) {
                    now *= l;
                }
                return now * T <= L * TIME;
            }
        }
        return false;
    }
}