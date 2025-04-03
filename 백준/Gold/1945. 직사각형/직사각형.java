import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
각 모서리의 꼭지점 위치로 잡아서 넘기자
좌상 점과 우하 점이 중요하다
모두 자연수니까 ㄱㅊ
최대 1만개
좌표는 최대 10억

그럼 시작과 끝으로 우큐 만들어서 조회하자
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Point> points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int ax = Integer.parseInt(input.nextToken());
            int ay = Integer.parseInt(input.nextToken());
            int bx = Integer.parseInt(input.nextToken());
            int by = Integer.parseInt(input.nextToken());

            points.add(new Point(ax, by, 1));
            points.add(new Point(bx, ay, -1));
        }

        System.out.print(solution(N, points));
    }

    static int solution(int N, List<Point> points) {
        points.sort(Comparator.comparingDouble(Point::getV).thenComparingInt(Point::getN));

        int result = 0;
        int now = 0;

        for (Point p : points) {
            now += p.n;
            result = Math.max(result, now);
        }

        return result;
    }

    static class Point {
        int x;
        int y;
        int n;

        public Point(int x, int y, int n) {
            this.x = x;
            this.y = y;
            this.n = n;
        }

        double getV() {
            return (double) x/ (double) y;
        }

        int getN() {
            return -n;
        }
    }
}