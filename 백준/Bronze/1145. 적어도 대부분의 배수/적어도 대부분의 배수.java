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
        int d = Integer.parseInt(input.nextToken());
        int e = Integer.parseInt(input.nextToken());

        System.out.println(solution(a, b, c, d, e));
    }

    public static int solution(int a, int b, int c, int d, int e) {
        for (int i = 1; i < 1000000; i++) {
            int count = 0;
            if (i % a == 0) count++;
            if (i % b == 0) count++;
            if (i % c == 0) count++;
            if (i % d == 0) count++;
            if (i % e == 0) count++;
            if (count >= 3) return i;
        }

        return 0;
    }
}