import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var lines = new ArrayList<Line>();

        for (int i=0; i<N; i++){
            var r = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            lines.add(new Line(new Point(r[0], r[1]), new Point(r[2], r[3])));
        }

        System.out.println(solution(N, lines));
    }

    static String solution(int N, List<Line> lines){
        var g = IntStream.range(0,N).toArray();

        for (int i=0; i<N; i++){
            var now = lines.get(i);
            for (int j=0; j<i; j++){
                if (now.isCross(lines.get(j))) {
                    var ni = findGroup(i, g);
                    var nj = findGroup(j, g);
                    g[Math.max(ni, nj)] = Math.min(ni, nj);
                }
            }
        }

        var hm = new HashMap<Integer, Integer>();
        var max = 0;

        for (int i=0; i<N; i++){
            var n = findGroup(i, g);
            var now = hm.getOrDefault(n, 0)+1;
            max = Math.max(max, now);
            hm.put(n, now);
        }
//        System.out.println(Arrays.stream(g).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        return hm.keySet().size() + "\n" + max;
    }

    static int findGroup(int n, int[] g){
        if (n==g[n]) {
            return n;
        }
        var r = findGroup(g[n], g);
        g[n] = r;
        return r;
    }



    static class Line{
        Point p1;
        Point p2;

        Line(Point p1, Point p2){
            if (p1.compareTo(p2)<0){
                this.p1 = p1;
                this.p2 = p2;
            } else {
                this.p1 = p2;
                this.p2 = p1;
            }
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
            return Integer.compare(result, 0);
        }
    }

    static class Point implements Comparable<Point>{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x){
                return this.y - o.y;
            }
            return this.x - o.x;
        }
    }
}