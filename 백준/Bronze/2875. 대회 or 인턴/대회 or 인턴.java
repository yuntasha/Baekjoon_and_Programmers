import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, M, K));
    }

    public static int solution(int N, int M, int K) {
        int result = 0;

        for (int i = Math.max(0, K - M); i <= Math.min(K, N); i++) {
            int m = N - i;
            int w = M - (K - i);

            result = Math.max(result, Math.min(m >> 1, w));
        }

        return result;
    }
}