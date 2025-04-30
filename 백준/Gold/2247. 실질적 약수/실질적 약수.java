import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    static long solution(long N) {
        long result = 0;

        for (int i = 2; i <= N / 2; i++) {
            result += ((N / i) - 1) * i;
            result %= 1_000_000;
        }

        return result;
    }
}