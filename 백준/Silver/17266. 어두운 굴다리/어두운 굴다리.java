import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int M = Integer.parseInt(bf.readLine());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, M, arr));
    }

    static int solution(int N, int M, int[] arr) {
        int s = 0;
        int e = N + 1;

        while (s + 1 < e) {
            int m = (s + e) >> 1;

            if (isP(N, m, arr)) {
                e = m;
            } else {
                s = m;
            }
        }

        return e;
    }

    static boolean isP(int N, int n, int[] arr) {
        int now = 0;

        for (int i : arr) {
            if (now < i - n) return false;
            now = i + n;
        }

        return now >= N;
    }
}