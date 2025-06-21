import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int A = Integer.parseInt(input.nextToken());
        int B = Integer.parseInt(input.nextToken());

        List<Point> points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            points.add(new Point(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken())));
        }

        System.out.println(solution(N, A, B, points));
    }

    static int solution(int N, int A, int B, List<Point> points) {
        int result = 0;

        for (Point p1 : points) {
            int min1 = 3_000_000;
            int min2 = 3_000_000;
            int max1 = 0;
            int max2 = 0;
            for (Point p2 : points) {
                if (Math.abs(p2.x - p1.x) >= A || Math.abs(p2.y - p1.y) >= B) continue;
                if (p1.x > p2.x) continue;
                if (p1.y <= p2.y) {
                    min1 = Math.min(min1, p2.v);
                    max1 = Math.max(max1, p2.v);
                }
                if (p1.y >= p2.y) {
                    min2 = Math.min(min2, p2.v);
                    max2 = Math.max(max2, p2.v);
                }
            }

            result = Math.max(result, max1 - min1);
            result = Math.max(result, max2 - min2);
        }
        // 4 2
        // 1 4, 4 6

        return result;
    }

    static class Point {
        int x;
        int y;
        int v;

        public Point(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }
}