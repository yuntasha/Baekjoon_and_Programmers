import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    static int[] P, S;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        P = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        S = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N));
    }

    static int solution(int N) {
        var result = 1;
        var collect = IntStream.range(0, N).map(i -> i%3).toArray();
        if (isSame(collect, P)) return 0;
        var now = shuffle(N, P, S);
        while (!isSame(now, P)) {
            if (isSame(collect, now)) return result;
            now = shuffle(N, now, S);
            result++;
        }
        return -1;
    }

    static int[] shuffle(int N, int[] a, int[] S) {
        var result = new int[N];

        for (int i=0; i<N; i++) {
            result[S[i]] = a[i];
        }

        return result;
    }

    static boolean isSame(int[] a, int[] b) {
        for (int i=0; i<a.length; i++){
            if (a[i] != b[i]) return false;
        }
        return true;
    }
}