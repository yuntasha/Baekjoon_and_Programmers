/*
최대 소수 2번째 자리까지
그러니까 . 앞에 있는거 100배, .뒤에 있는거 더해주기
그 다음에 ccw로 4개의 점이 한 직선 위에 존재하는지 확인
그러니까 a -> b, c -> d가 있다면
a b c로 한번
a b d로 한번
만약 둘 다 0이면 정렬했을때
a < d, c < b or a > d, c > b가 나오면 같은 선분

한 점이 트롤짓 할 수 있다
점이라면 -1로 보내는게 맞는 것 같다
점인데 포함되어 있다면 -1보내고 아니면 뭐 냅두겠지?
 */

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Line> lines = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] points = bf.readLine().split(" ");
            lines.add(new Line(new Point(convert(points[0]), convert(points[1])), new Point(convert(points[2]), convert(points[3]))));
        }

        System.out.println(solution(N, lines));
    }

    static int convert(String s) {
        String[] ss = s.split("\\.");
        if (ss[0].isEmpty()) {
            return Integer.parseInt(ss[1]);
        }
        if (ss.length == 1) return Integer.parseInt(ss[0]) * 100;
        return Integer.parseInt(ss[0]) * 100 + (ss[1].length() == 1 ? Integer.parseInt(ss[1]) * 10 : Integer.parseInt(ss[1]));
    }

    static int solution(int N, List<Line> lines) {
        int[] g = IntStream.range(0, N).toArray();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (!lines.get(i).isEqual(lines.get(j))) continue;
                boolean isP = false;
                if (lines.get(i).isPoint()) {
                    g[i] = -1;
                    isP = true;
                }
                if (lines.get(j).isPoint()) {
                    g[j] = -1;
                    isP = true;
                }
                if (isP) continue;

                int a = find(i, g);
                int b = find(j, g);

                g[Math.max(a, b)] = Math.min(a, b);
            }
        }

        HashSet<Integer> count = new HashSet<>();

        for (int i = 0; i < N; i++) {
            if (g[i] == -1) continue;
            count.add(find(i, g));
        }

        return count.size();
    }

    static int find(int n, int[] g) {
        if (n == g[n]) return n;
        return g[n] = find(g[n], g);
    }

    static class Line {
        Point a;
        Point b;

        public Line(Point a, Point b) {
            if (a.compare(b) == 1) {
                this.b = a;
                this.a = b;
            } else  {
                this.a = a;
                this.b = b;
            }
        }

        public boolean isEqual(Line line) {
            return ccw(line.a) == 0 && ccw(line.b) == 0 && (a.compare(line.b) == line.a.compare(b) || a.compare(line.b) == 0 || line.a.compare(b) == 0);
        }

        public int ccw(Point p) {
            return (b.x - a.x) * (p.y - a.y) - (p.x - a.x) * (b.y - a.y);
        }

        public boolean isPoint() {
            return a.compare(b) == 0;
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int compare(Point p) {
            if (this.x < p.x) {
                return -1;
            }
            if (this.x > p.x) {
                return 1;
            }
            if (this.y < p.y) {
                return -1;
            }
            if (this.y > p.y) {
                return 1;
            }
            return 0;
        }
    }
}