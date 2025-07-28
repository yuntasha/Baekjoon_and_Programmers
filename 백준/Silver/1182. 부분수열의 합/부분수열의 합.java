import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int S = Integer.parseInt(input.nextToken());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, S, arr));
    }

    public static int solution(int N, int S, int[] arr) {
        return find(0, 0, N, S, arr) - (S == 0 ? 1 : 0);
    }

    public static int find(int n, int sum, int N, int S, int[] arr) {
        if (n == N) return sum == S ? 1 : 0;

        return find(n + 1, sum + arr[n], N, S, arr) + find(n + 1, sum, N, S, arr);
    }
}