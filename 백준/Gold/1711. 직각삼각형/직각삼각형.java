import java.io.*;
import java.util.*;

/*
이거 브루트포스
각 좌표에 몇개인지 구해놓자 HashMap사용하거나 트리맵 사용하면 될듯 << 메모리 아끼기
그리고 각 점을 보면서 다 곱해버리자
결과 최댓값은 아니겠지만 혹시 모르니 long으로 반환
1500 * 1499 * 1498이 일단 30억정도 됨
평행하는게 아니네?
제한시간 5초네 그냥 깡 완탐인듯
거리를 미리 구한다? N(N+1)/2가 되는데
기본적으로 늘어남 어차피 3개 비교해야하는데

 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Point> points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            long x = Long.parseLong(input.nextToken());
            long y = Long.parseLong(input.nextToken());

            points.add(new Point(x, y));
        }

        System.out.println(solution(N, points));
    }

    static long solution(int N, List<Point> points) {
        long result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    long a = getDis(points.get(i), points.get(j));
                    long b = getDis(points.get(j), points.get(k));
                    long c = getDis(points.get(k), points.get(i));

                    if (isT(a, b, c)) result++;
                }
            }
        }
        return result;
    }

    static boolean isT(long a, long b, long c) {
        return a + b == c || a + c == b || b + c == a;
    }

    static long getDis(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}