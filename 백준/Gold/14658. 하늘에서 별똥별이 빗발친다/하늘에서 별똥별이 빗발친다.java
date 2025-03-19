/*
별똥별 좌표 위치 기준으로 트램펄린 설치 + 해당 네모안에 들어오는지 체크
100 * 100 * 100
깔끔하네
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int L = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        List<Point> stars = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            input = new StringTokenizer(bf.readLine());
            stars.add(new Point(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken())));
        }

        System.out.println(solution(N, M, L, K, stars));
    }

    public static int solution(int N, int M, int L, int K, List<Point> stars) {

        int result = 0;

        for (Point px : stars) {
            for (Point py : stars) {

                int now = 0;

                for (Point p : stars) {
                    if (p.isIn(px.x, py.y, L)) now++;
                }

                result = Math.max(result, now);
            }
        }

        return K - result;
    }

    public static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean isIn(int x, int y, int L) {
            return x <= this.x && this.x <= x + L && y <= this.y && this.y <= y + L;
        }
    }
}