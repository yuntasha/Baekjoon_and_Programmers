import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int[] D = {-3, -1, 1, 3};
    static int RIGHT = 123_456_780;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = 0;

        for (int i=0; i<3; i++) {
            var st = new StringTokenizer(bf.readLine());
            N*=10;
            N+=Integer.parseInt(st.nextToken());
            N*=10;
            N+=Integer.parseInt(st.nextToken());
            N*=10;
            N+=Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N));
    }

    static int solution(int N) {

        if (N == RIGHT) return 0;

        var visited = new HashSet<Integer>();

        var q = new LinkedList<Integer>();

        q.add(N);

        var result = 0;

        while (!q.isEmpty()) {
            var nextQ = new LinkedList<Integer>();
            result++;
            while (!q.isEmpty()) {
                var now = q.remove();
                var z = findZ(now);
                for (int d : D) {
                    if (d==-1 && (z==4 || z==7)) continue;
                    if (d==1 && (z==6 || z==3)) continue;
                    if (!isIn(z + d)) continue;
                    var next = swap(now, z, z+d);
                    if (visited.contains(next)) continue;
                    if (next == RIGHT) return result;
                    visited.add(next);
                    nextQ.add(next);
                }
            }
            q = nextQ;
        }

        return -1;
    }

    static int findZ(int n) {
        for (int i=9; i>0; i--) {
            if (n%10==0) return i;
            n/=10;
        }
        return -1;
    }

    static int swap(int n, int a, int b) {
        var ta = pow(9-a);
        var tb = pow(9-b);

        var v1 = n/ta%10;
        var v2 = n/tb%10;

        n-=v1*ta + v2*tb;
        n+=v1*tb + v2*ta;

        return n;
    }

    static int pow(int b) {
        var result = 1;
        for (int i=0; i<b; i++) {
            result = (result<<3) + (result<<1);
        }
        return result;
    }

    static boolean isIn(int i) {
        return 0<i && i<=9;
    }
}