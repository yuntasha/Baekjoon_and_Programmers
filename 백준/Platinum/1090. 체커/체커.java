import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Integer> xArr = new ArrayList<>();
        List<Integer> yArr = new ArrayList<>();

        List<Point> points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int x = Integer.parseInt(input.nextToken());
            int y = Integer.parseInt(input.nextToken());

            xArr.add(x);
            yArr.add(y);

            points.add(new Point(x, y));
        }

        System.out.println(solution(N, xArr, yArr, points));
    }

    public static String solution(int N, List<Integer> xArr, List<Integer> yArr, List<Point> points) {
        int[] result = new int[N];

        Arrays.fill(result, Integer.MAX_VALUE);

        for (int x : xArr) {
            for (int y : yArr) {
                List<Integer> dis = new ArrayList<>();

                for (Point p : points) {
                    dis.add(p.getD(x, y));
                }

                dis.sort(Comparator.naturalOrder());

                int now = 0;
                for (int i = 0; i < N; i++) {
                    now += dis.get(i);
                    result[i] = Math.min(result[i], now);
                }
            }
        }

        return Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    public static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getD(int x, int y) {
            return Math.abs(this.x - x) + Math.abs(this.y - y);
        }
    }
}