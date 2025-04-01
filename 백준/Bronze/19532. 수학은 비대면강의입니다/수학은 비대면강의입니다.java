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
        int f = Integer.parseInt(input.nextToken());

        System.out.println(Arrays.stream(solution(a, b, c, d, e, f)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static int[] solution(int a, int b, int c, int d, int e, int f) {
        for (int x = -999; x < 1000; x++) {
            for (int y = -999; y < 1000; y++) {
                if (isOk(a, b, c, d, e, f, x, y)) return make(x, y);
            }
        }

        return make(-1);
    }

    public static int[] make(int... args) {
        return args;
    }

    public static boolean isOk(int a, int b, int c, int d, int e, int f, int x, int y) {
        return a * x + b * y == c && d * x + e * y == f;
    }
}