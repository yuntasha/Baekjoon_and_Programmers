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

        var monkeys = new ArrayList<Monkey>();

        for (int i=0; i<N; i++) {
            var xy = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            monkeys.add(new Monkey(xy[0], xy[1]));
        }

        System.out.println(solution(N, monkeys));
    }

    static long solution(int N, List<Monkey> monkeys) {
        var min = monkeys.stream().min(Comparator.comparingLong(Monkey::getY)).get().getY();
        var max = monkeys.stream().max(Comparator.comparingLong(Monkey::getY)).get().getY();
        
        var degrees = new ArrayList<Degree>();

        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                degrees.add(new Degree(monkeys.get(i), monkeys.get(j)));
            }
        }

        while (min+1<max) {
            var mid = (min+max)/2;
            var n1 = (mid+min)/2;
            var n2 = (mid+max)/2;
            var m1 = degrees.stream().mapToLong(d -> d.getDistance(n1)).max().getAsLong();
            var m2 = degrees.stream().mapToLong(d -> d.getDistance(n2)).max().getAsLong();
            if (m1<m2) {
                max = mid;
            } else {
                min = mid;
            }
        }
        var result = min;

        return Math.min(degrees.stream().mapToLong(d -> d.getDistance(result)).max().getAsLong(), degrees.stream().mapToLong(d -> d.getDistance(result+1)).max().getAsLong());
    }

    static class Monkey {
        long x;
        long y;

        public Monkey(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getY() {
            return y;
        }
    }

    static class Degree {
        Monkey m1;
        Monkey m2;

        public Degree(Monkey m1, Monkey m2) {
            this.m1 = m1;
            this.m2 = m2;
        }

        public long getDistance(long n) {
            if (m1.x==m2.x) {
                return Math.abs(m1.y-m2.y);
            }
            return Math.abs(Math.abs(m1.y-n) + Math.abs(m2.y-n) + Math.abs(m1.x-m2.x));
        }
    }
}