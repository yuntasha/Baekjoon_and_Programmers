import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var points = new ArrayList<Point>();

        for (int i=0; i<N; i++) {
            points.add(new Point(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }

        System.out.println(solution(N, points));
    }

    static int solution(int N, List<Point> points) {
        var result = Integer.MAX_VALUE;

        var xList = new ArrayList<Integer>();
        var yList = new ArrayList<Integer>();

        for (Point p: points) {
            xList.add(p.x);
            yList.add(p.y);
        }

        xList.sort(Comparator.naturalOrder());
        yList.sort(Comparator.naturalOrder());

        for (int xs=0; xs<N; xs++) {
            for (int xe=xs; xe<N; xe++) {
                for (int ys=0; ys<N; ys++) {
                    for (int ye=ys; ye<N; ye++) {
                        var now = new Square(xList.get(xs), xList.get(xe), yList.get(ys), yList.get(ye));
                        if (now.inCnt(points) >= N/2) {
                            result = Math.min(result, now.getExtend());
                        }
                    }
                }
            }
        }

        return result;
    }


    static class Point {
        final int x;
        final int y;

        public Point(int[] input) {
            this.x = input[0];
            this.y = input[1];
        }
    }

    static class Square {
        final int x1;
        final int x2;
        final int y1;
        final int y2;

        public Square(int x1, int x2, int y1, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        int inCnt(List<Point> points) {
            var result = 0;
            for (Point p: points) {
                if (x1<=p.x && p.x<=x2 && y1<=p.y && p.y<=y2) {
                    result++;
                }
            }
            return result;
        }

        int getExtend() {
            return (x2-x1+2) * (y2-y1+2);
        }
    }
}