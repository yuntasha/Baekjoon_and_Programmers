import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NC = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NC[0];
        var C = NC[1];

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        System.out.println(solution(N, C, arr));
    }

    static long solution(int N, int C, long[] arr) {
        var c1 = new ArrayList<Long>();
        c1.add(0L);

        for (int i=0; i<N/2; i++) {
            var n = c1.size();
            for (int k=0; k<n; k++) {
                c1.add(c1.get(k)+arr[i]);
            }
        }

        var c2 = new ArrayList<Long>();
        c2.add(0L);

        for (int i=N/2; i<N; i++) {
            var n = c2.size();
            for (int k=0; k<n; k++) {
                c2.add(c2.get(k)+arr[i]);
            }
        }

        c2.sort(Comparator.naturalOrder());

        var result = 0L;

        for (long l : c1) {
            result += find(C-l, c2);
        }

        return result;
    }

    static long find(long n, List<Long> c2) {
        if (c2.isEmpty()) {
            return 0;
        }

        if (c2.get(0) > n) {
            return 0;
        }

        if (c2.get(c2.size()-1) <= n) {
            return c2.size();
        }

        var s = 0;
        var e = c2.size()-1;

        while (s+1<e) {
            var m = (s+e)/2;

            if (c2.get(m) > n) {
                e = m;
            } else {
                s = m;
            }
        }

        return e;
    }
}