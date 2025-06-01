import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
1_000_000_000_000
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        StringBuilder output = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(bf.readLine());
            int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            output.append("\n").append(solution(N, arr));
        }
        System.out.println(output.substring(1));
    }

    public static int solution(int N, int[] arr) {
        int sum = 0;
        Set<Integer> sums = new HashSet<>();

        for (int i : arr) {
            sum += i;
            sums.add(sum);
        }

        if (sum == 0) return 0;

        for (int i = 1; i <= sum / 2; i++) {
            if (sum % i > 0) continue;
            if (!isOK(i, sum, sums)) continue;
            return N - (sum / i);
        }

        return N - 1;
    }

    static boolean isOK(int n, int sum, Set<Integer> sums) {
        for (int i = n; i <= sum; i += n) {
            if (!sums.contains(i)) return false;
        }
        return true;
    }
}