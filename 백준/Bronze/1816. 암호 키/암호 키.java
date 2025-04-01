import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static String YES = "YES";
    static String NO = "NO";

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        for (int i = 0; i < N; i++) {
            System.out.println(solution(Long.parseLong(bf.readLine())));
        }
    }

    public static String solution(long N) {
        for (int i = 2; i < 1_000_000; i++) {
            if (N % i == 0) return NO;
        }
        return YES;
    }
}