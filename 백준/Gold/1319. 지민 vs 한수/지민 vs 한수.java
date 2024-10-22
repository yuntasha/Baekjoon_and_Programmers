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

        var trees = new ArrayList<Tree>();

        for (int i=0; i<N; i++) {
            trees.add(new Tree(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }

        System.out.println(solution(N, trees));
    }

    static int solution(int N, List<Tree> trees) {
        trees.sort(Comparator.comparingInt(Tree::getX)
                .thenComparingInt(Tree::getY));
        var result = Integer.MAX_VALUE;
        for (int i=0; i<N-1; i++) {
            var x = trees.get(i);
            Loop: for (int j=i+1; j<N; j++) {
                var y = trees.get(j);
                var a = x.v;
                var b = y.v;
                var line = new Line(trees.get(i), trees.get(j));
                var now = 0;
                for (int t=0; t<N; t++) {
                    if (i==t || j==t) {
                        continue;
                    }
                    var n = line.getValue(trees.get(t));
                    if (n==0) {
                        if ((x.x==y.x && x.y<trees.get(t).y && trees.get(t).y<y.y) || (x.x<trees.get(t).x && trees.get(t).x<y.x)) {
                            continue Loop;
                        }
                        if (x.x==y.x) {
                            if ((x.y+y.y)/2 >trees.get(t).y) {
                                a+=trees.get(t).v;
                            } else {
                                b+=trees.get(t).v;
                            }
                        } else {
                            if ((x.x+y.x)/2 >trees.get(t).x) {
                                a+=trees.get(t).v;
                            } else {
                                b+=trees.get(t).v;
                            }
                        }
                    } else {
                        now+=n;
                    }
                }
                result = Math.min(result, Math.abs(now+a+b));
                result = Math.min(result, Math.abs(now-a+b));
                result = Math.min(result, Math.abs(now+a-b));
                result = Math.min(result, Math.abs(now-a-b));
            }
        }

        return result;
    }

    static class Line {
        // dx * y = dy * x + b
        int dx;
        int dy;
        int b;

        Line(Tree a, Tree b) {
            this.dx = a.x-b.x;
            this.dy = a.y-b.y;
            this.b = this.dx*a.y - this.dy*a.x;
        }

        int getValue(Tree t) {
            var x = this.dy*t.x + b;
            var y = this.dx*t.y;

            if (x==y) {
                return 0;
            } else if (y>x) {
                return -t.v;
            } else {
                return t.v;
            }
        }
    }

    static class Tree {
        int x;
        int y;
        int v;

        Tree(int[] input) {
            this.x = input[0];
            this.y = input[1];
            this.v = input[2];
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}