import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int a = Integer.parseInt(input.nextToken());
        int b = Integer.parseInt(input.nextToken());
        int c = Integer.parseInt(input.nextToken());
        int N = Integer.parseInt(input.nextToken());

        System.out.println(solution(a, b, c, N));
    }

    public static int solution(int a, int b, int c, int N) {
        for (int i = 0; i <= N / a; i++) {
            for (int j = 0; j <= N / b; j++) {
                if (a * i + b * j > N) break;
                if ((N - a * i - b * j) % c == 0) return 1;
            }
        }

        return 0;
    }
}