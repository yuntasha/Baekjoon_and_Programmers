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
            for (Point p2 : points) {
                if (Math.abs(p2.x - p1.x) >= A || Math.abs(p2.y - p1.y) >= B) continue;
                result = Math.max(result, Math.abs(p2.v - p1.v));
            }
        }

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