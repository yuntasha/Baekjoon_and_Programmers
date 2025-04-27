/*
모든 경사를 선으로 나타내고 그 선으로 오르막길 내리막길 구분해
나는 높이 즉 y값으로 이분탐색을 진행할거야
그리고 오르막에서 했을때 해당 높이가 가능한 x의 최대값 찾고
내리막에서 했을 때 해당 높이가 가능한 y의 최소값을 찾아
그래서 최소값 <= 최대값으로 가능한 높이인지 판별해
 */

import java.io.*;
import java.util.*;

public class Main {

    static double MAX_VALUE = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int px = Integer.parseInt(input.nextToken());
        int py = Integer.parseInt(input.nextToken());

        List<Line> lines = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            input = new StringTokenizer(bf.readLine());

            int nx = Integer.parseInt(input.nextToken());
            int ny = Integer.parseInt(input.nextToken());

            lines.add(new Line(px, py, nx, ny));

            px = nx;
            py = ny;
        }

        System.out.println(solution(N, lines));
    }

    static String solution(int N, List<Line> lines) {
        double s = 0;
        double e = MAX_VALUE;
        
        if (isP(0, lines)) return "0.00";


        while (s + 0.001 <= e) {
            double m = (s + e) / 2;

            if (isP(m, lines)) {
                e = m;
            } else {
                s = m;
            }
        }

        e = Math.round(e * 100f) / 100f;

        return String.format("%.2f", e);
    }

    static boolean isP(double y, List<Line> lines) {
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        for (Line line : lines) {

            if (line.a == 0) {
                if (y >= line.b) continue;
                return false;
            }
            double x = line.getX(y);

            if (line.a > 0) {
                min = Math.min(min, x);
            } else {
                max = Math.max(max, x);
            }
        }

        return min >= max;
    }

    static class Line {
        double a;
        double b;

        // y = ax + b
        Line(int x1, int y1, int x2, int y2) {
            this.a = ((double) y2 - y1) / ((double) x2 - x1);
            this.b = y1 - (this.a * x1);
        }

        double getX(double y) {
            return (y - b) / a;
        }
    }
}