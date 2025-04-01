import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static String YES = "YES";
    static String NO = "NO";

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Base> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int num = Integer.parseInt(input.nextToken());
            int a = num / 100;
            int b = num / 10 % 10;
            int c = num % 10;

            int strike = Integer.parseInt(input.nextToken());
            int ball = Integer.parseInt(input.nextToken());

            list.add(new Base(a, b, c, strike, ball));
        }

        System.out.println(solution(N, list));
    }

    public static int solution(int N, List<Base> list) {
        int result = 0;

        for (int a = 1; a < 10; a++) {
            for (int b = 1; b < 10; b++) {
                if (a == b) continue;
                Loop : for (int c = 1; c < 10; c++) {
                    if (a == c || b == c) continue;
                    for (Base base : list) {
                        if (!base.isOk(a, b, c)) continue Loop;
                    }
                    result++;
                }
            }
        }

        return result;
    }

    static class Base {
        int a;
        int b;
        int c;
        int strike;
        int ball;

        public Base(int a, int b, int c, int strike, int ball) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.strike = strike;
            this.ball = ball;
        }

        boolean isOk(int a, int b, int c) {
            return getBall(a, b, c) == ball && getStrike(a, b, c) == strike;
        }

        private int getStrike(int a, int b, int c) {
            int result = 0;
            if (this.a == a) result++;
            if (this.b == b) result++;
            if (this.c == c) result++;
            return result;
        }

        private int getBall(int a, int b, int c) {
            int result = 0;
            if (this.a == b || this.a == c) result++;
            if (this.b == a || this.b == c) result++;
            if (this.c == b || this.c == a) result++;

            return result;
        }
    }
}