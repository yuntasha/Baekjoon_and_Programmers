import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(bf.readLine());
        var N = Integer.parseInt(st.nextToken());
        var M = Integer.parseInt(st.nextToken());

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        System.out.println(solution(N, M, arr));
    }

    static long solution(int N, int M, long[] arr) {
        var s = 0L;
        var e = Arrays.stream(arr).max().getAsLong();

        if (Arrays.stream(arr).sum() == M) return 0;

        while (s+1<e) {
            var mid = (s+e)/2;

            if (find(mid, M, arr)) {
                s = mid;
            } else {
                e = mid;
            }
        }

        return s;
    }

    static long find(long cut, long[] arr) {
        var result = 0;
        for (long i : arr) {
            result += Math.max(0L, i-cut);
        }
        return result;
    }

    static boolean find(long cut, int M, long[] arr) {
        var result = 0L;
        for (long i : arr) {
            result += Math.max(0L, i-cut);
            if (result >= M) return true;
        }
        if (result >= M) return true;
        return false;
    }
}