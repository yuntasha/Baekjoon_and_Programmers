import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Main {

    static int R;
    static int C;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var line1 = new Line(Arrays.stream(bufferedReader.readLine().split(" ")).mapToLong(Long::parseLong).toArray());
        var line2 = new Line(Arrays.stream(bufferedReader.readLine().split(" ")).mapToLong(Long::parseLong).toArray());


        System.out.println(solution(line1, line2));
    }

    static int solution(Line line1, Line line2){
        if (line2.isCross(line1)) return 1;
        else return 0;
    }

    static class Line{
        Point p1;
        Point p2;

        Line(long[] line){
            var temp = new ArrayList<Point>();
            temp.add(new Point(line[0], line[1]));
            temp.add(new Point(line[2], line[3]));
            temp.sort(Point::compareTo);
            this.p1 = temp.get(0);
            this.p2 = temp.get(1);
        }

        boolean isCross(Line l){
            var result1 = ccw(this.p1, this.p2, l.p1) * ccw(this.p1, this.p2, l.p2);
            var result2 = ccw(l.p1, l.p2, this.p1) * ccw(l.p1, l.p2, this.p2);

            if (result1==0 && result2==0) {
                return this.p1.compareTo(l.p2)<=0 && this.p2.compareTo(l.p1)>=0;
            } else return result1<=0 && result2<=0;
        }

        private int ccw(Point p1, Point p2, Point p3){
            var result = (p2.x-p1.x)*(p3.y-p1.y) - (p2.y-p1.y)*(p3.x-p1.x);
            return Long.compare(result, 0L);
        }
    }

    static class Point implements Comparable<Point>{
        long x;
        long y;

        Point(long x, long y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x){
                return (int)this.y - (int)o.y;
            }
            return (int)this.x - (int)o.x;
        }
    }
}