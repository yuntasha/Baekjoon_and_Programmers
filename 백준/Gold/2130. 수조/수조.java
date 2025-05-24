import java.io.*;
import java.util.*;

/*
수조가 위치한 높이 : b
수조 자체의 높이 : h
수조의 가로 길이 : w
수조의 세로 길이 : d


 */

public class Main {

    static String OVERFLOW = "OVERFLOW";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Bowl> bowls = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            bowls.add(new Bowl(Long.parseLong(input.nextToken()), Long.parseLong(input.nextToken()), Long.parseLong(input.nextToken()), Long.parseLong(input.nextToken())));
        }

        double V = Double.parseDouble(bf.readLine());

        System.out.println(solution(N, bowls, V));
    }

    static String solution(int N, List<Bowl> bowls, double V) {
        double s = 0;
        double e = getMax(bowls);

        if (!isP(e, bowls, V)) return OVERFLOW;

        while (s + 0.001 < e) {
            double m = (s + e) / 2;

            if (isP(m, bowls, V)) {
                e = m;
            } else {
                s = m;
            }
        }

        return String.format("%.2f", s);
    }

    static double getMax(List<Bowl> bowls) {
        double result = 0;

        for (Bowl b : bowls) {
            result = Math.max(result, b.getH());
        }

        return result;
    }

    // 이 높이로 해당 부피를 채울 수 있는가
    // FFFF TTTT 방식으로 진행함
    static boolean isP(double now, List<Bowl> bowls, double V) {
        double sum = 0;

        for (Bowl b : bowls) {
            sum += b.getB(now);
        }

        return sum >= V;
    }

    static class Bowl {
        long b;
        long h;
        long w;
        long d;

        public Bowl(long b, long h, long w, long d) {
            this.b = b;
            this.h = h;
            this.w = w;
            this.d = d;
        }

        public double getB(double water) {
            double now = Math.max(0, water - b);
            now = Math.min(now, h);

            return w * d * now;
        }

        public double getH() {
            return b + h;
        }
    }
}