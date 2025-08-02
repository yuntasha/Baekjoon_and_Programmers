import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] foods = new int[N][];

        for (int i = 0; i < N; i++) foods[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, foods));
    }

    public static int solution(int N, int[][] foods) {
        return find(false, 0, N, 1, 0, foods);
    }

    public static int find(boolean isOK, int n, int N, int t1, int t2, int[][] foods) {
        if (n == N) return isOK ? Math.abs(t1 - t2) : Integer.MAX_VALUE;
        return Math.min(find(true, n + 1, N, t1 * foods[n][0], t2 + foods[n][1], foods), find(isOK, n + 1, N, t1, t2, foods));
    }
}