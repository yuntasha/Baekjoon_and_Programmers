import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
닫힌 다각형 즉 한 선분 서로 공유된다는 소리이기도 하다
따라서 선분이 서로 인접하다면 서로 움직이는게 가능하다는 소리이기도 함
같은 선분을 가진다면 그것은 연결됐다는 소리
ㄷㄱㅈ
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<List<Integer>> connect = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            connect.add(new ArrayList<>());
        }

        HashMap<Line, Integer> lines = new HashMap<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int m = Integer.parseInt(input.nextToken());

            Point p = getPoint(input);
            Point fp = p;

            for (int j = 1; j < m; j++) {
                Point np = getPoint(input);
                Line l = new Line(p, np);
                if (lines.containsKey(l)) {
                    connect.get(lines.get(l)).add(i);
                    connect.get(i).add(lines.get(l));
                } else {
                    lines.put(l, i);
                }
                p = np;
            }

            Line l = new Line(p, fp);
            if (lines.containsKey(l)) {
                connect.get(lines.get(l)).add(i);
                connect.get(i).add(lines.get(l));
            } else {
                lines.put(l, i);
            }
        }

        int M = Integer.parseInt(bf.readLine());

        StringJoiner result = new StringJoiner("\n");

        for (int i = 0; i < M; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(input.nextToken()) - 1;
            int b = Integer.parseInt(input.nextToken()) - 1;

            result.add(String.valueOf(solution(N, connect, a, b)));
        }

        System.out.println(result);
    }

    static Point getPoint(StringTokenizer input) {
        int x = Integer.parseInt(input.nextToken());
        int y = Integer.parseInt(input.nextToken());
        int z = Integer.parseInt(input.nextToken());

        return new Point(x, y, z);
    }

    static int solution(int N, List<List<Integer>> connect, int a, int b) {
        int[] visited = new int[N];

        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.add(a);
        visited[a] = 1;

        while (!q.isEmpty()) {
            int now = q.remove();

            for (int next : connect.get(now)) {
                if (visited[next] > 0) continue;
                visited[next] = visited[now] + 1;
                q.add(next);
            }
            if (visited[b] > 0) break;
        }

        return visited[b] - 1;
    }

    static class Line {
        Point p1;
        Point p2;

        public Line(Point p1, Point p2) {
            this.p1 = Point.bigger(p1, p2);
            this.p2 = Point.smaller(p1, p2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Line line = (Line) o;
            return Objects.equals(p1, line.p1) && Objects.equals(p2, line.p2);
        }

        @Override
        public int hashCode() {
            int h1 = p1.hashCode();
            int h2 = p2.hashCode();
            return h1 * h2 + h1 + h2;
        }
    }

    static class Point {
        int x;
        int y;
        int z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        static Point bigger(Point p1, Point p2) {
            if (p1.x < p2.x) {
                return p1;
            }
            if (p1.x > p2.x) {
                return p2;
            }
            if (p1.y < p2.y) {
                return p1;
            }
            if (p1.y > p2.y) {
                return p2;
            }
            if (p1.z < p2.z) {
                return p1;
            }
            if (p1.z > p2.z) {
                return p2;
            }

            return p1;
        }

        static Point smaller(Point p1, Point p2) {
            if (p1.x < p2.x) {
                return p2;
            }
            if (p1.x > p2.x) {
                return p1;
            }
            if (p1.y < p2.y) {
                return p2;
            }
            if (p1.y > p2.y) {
                return p1;
            }
            if (p1.z < p2.z) {
                return p2;
            }
            if (p1.z > p2.z) {
                return p1;
            }

            return p2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y && z == point.z;
        }

        @Override
        public int hashCode() {
            return x * y * z + x * y + y * z + x * z + x + y + z;
        }
    }
}