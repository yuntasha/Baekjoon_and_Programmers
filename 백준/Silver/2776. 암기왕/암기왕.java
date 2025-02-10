import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());
        StringJoiner result = new StringJoiner("\n");

        for (int t = 0; t < T; t++) {
            bf.readLine();
            int[] N = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            bf.readLine();
            int[] M = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            result.add(solution(N, M));
        }

        System.out.println(result.toString());
    }

    static String solution(int[] N, int[] M) {
        StringJoiner sj = new StringJoiner("\n");

        Arrays.sort(N);

        for (int i : M) {
            sj.add(String.valueOf(bi(i, N)));
        }

        return sj.toString();
    }

    static int bi(int n, int[] M) {
        if (M[0] > n) return 0;
        if (M[M.length - 1] < n) return 0;
        if (M[0] == n) return 1;

        int s = 0;
        int e = M.length - 1;

        while (s + 1 < e) {
            int m = (s + e) >> 1;

            if (M[m] < n) {
                s = m;
            } else {
                e = m;
            }
        }

        return M[e] == n ? 1 : 0;
    }
}