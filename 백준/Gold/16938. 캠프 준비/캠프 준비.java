import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int MAX = 1_000_000;
    static int MIN = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int L = Integer.parseInt(input.nextToken());
        int R = Integer.parseInt(input.nextToken());
        int X = Integer.parseInt(input.nextToken());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, L, R, X, arr));
    }

    public static int solution(int N, int L, int R, int X, int[] arr) {
        return find(0, 0, MAX, MIN, N, L, R, X, arr);
    }

    public static int find(int n, int sum, int min, int max, int N, int L, int R, int X, int[] arr) {
        if (n == N) return (L <= sum && sum <= R && max - min >= X) ? 1 : 0;

        return find(n + 1, sum + arr[n], Math.min(min, arr[n]), Math.max(max, arr[n]), N, L, R, X, arr) + find(n + 1, sum, min, max, N, L, R, X, arr);
    }
}