import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        List<Alien> aliens = new ArrayList<>();

        aliens.add(null);

        for (int i = 1; i <= N; i++) {
            input = new StringTokenizer(bf.readLine());

            long a = Long.parseLong(input.nextToken());
            long b = Long.parseLong(input.nextToken());

            aliens.add(new Alien(i, a, b));
        }

        int[] g = IntStream.rangeClosed(0, N).toArray();

        int m = 0;

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());
            int a = find(Integer.parseInt(input.nextToken()), g);
            int b = find(Integer.parseInt(input.nextToken()), g);

            if (a == b) m++;

            g[Math.max(a, b)] = Math.min(a, b);
        }
        
        M -= m;

        System.out.println(solution(N, M, aliens, g));
    }

    static String solution(int N, int M, List<Alien> aliens, int[] g) {
        List<Line> lines = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                lines.add(
                        new Line(i, j,
                                Math.sqrt(Math.pow(aliens.get(i).x - aliens.get(j).x, 2)
                                        + Math.pow(aliens.get(i).y - aliens.get(j).y, 2))
                        )
                );
            }
        }

        lines.sort(Comparator.comparingDouble(Line::getD));

        int remain = N - M - 1;
        double result = 0.0;

        for (Line line : lines) {
            if (remain == 0) break;
            int a = find(line.a, g);
            int b = find(line.b, g);
            if (a == b) continue;

            remain--;

            g[Math.max(a, b)] = Math.min(a, b);

            result += line.d;
        }

        return String.format("%.2f", Math.round(result * 100) / 100.0);
    }

    static int find(int n, int[] g) {
        if (n != g[n]) g[n] = find(g[n], g);
        return g[n];
    }

    static class Line {
        int a;
        int b;
        double d;

        public Line(int a, int b, double d) {
            this.a = a;
            this.b = b;
            this.d = d;
        }

        public double getD() {
            return d;
        }
    }

    static class Alien {
        int n;
        long x;
        long y;

        public Alien(int n, long x, long y) {
            this.n = n;
            this.x = x;
            this.y = y;
        }
    }
}