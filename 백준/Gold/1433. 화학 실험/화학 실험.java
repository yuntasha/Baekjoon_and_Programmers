import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    static int FAIL = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        var over = new ArrayList<Water>();
        var under = new ArrayList<Water>();
        double now = 0.0;

        for (int i=0; i<N; i++) {
            var input = Arrays.stream(bf.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
            if (M == input[0]) {
                now += input[1];
            } else if (M > input[0]) {
                under.add(new Water(input));
            } else {
                over.add(new Water(input));
            }
        }

        System.out.println(solution(N, M, now, under, over));
    }

    static double solution(int N, int M, double now, List<Water> under, List<Water> over) {
        under.sort(Comparator.comparingDouble(Water::getN));
        over.sort(Comparator.comparingDouble(Water::getN).reversed());

        double x = 0;
        double y = 0;

        for (var u:under) {
            x+=u.x;
            y+=u.w;
        }
        for (var o:over) {
            x+=o.x;
            y+=o.w;
        }

        if (100*(x/y) > M) {
            var idx = 0;
            while (idx< over.size()) {
                var n = over.get(idx);
                x-=n.x;
                y-=n.w;
                if (100*(x/y) <= M) {
                    var l = (100*x-y*M)/(n.w*M-100*n.x);
                    x+=n.x*l;
                    y+=n.w*l;
                    break;
                }

                idx++;
            }
        } else if (100*(x/y) < M) {
            var idx = 0;
            while (idx< under.size()) {
                var n = under.get(idx);
                x-=n.x;
                y-=n.w;
                if (100*(x/y) >= M) {
                    var l = (100*x-y*M)/(n.w*M-100*n.x);
                    x+=n.x*l;
                    y+=n.w*l;
                    break;
                }

                idx++;
            }
        }
        return now + y;
    }

    static class Water {
        double n;
        double x;
        double w;

        Water(double[] input) {
            this.n = input[0];
            this.x = input[0]*input[1]/100.0;
            this.w = input[1];
        }

        public double getN() {
            return n;
        }
    }
}