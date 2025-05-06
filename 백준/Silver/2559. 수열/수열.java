import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, K, arr));
    }

    static int solution(int N, int K, int[] arr) {
        int[] sum = new int[N];

        sum[0] = arr[0];

        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        int result = sum[K - 1];

        for (int i = 0; i < N - K; i++) {
            result = Math.max(result, sum[i + K] - sum[i]);
        }

        return result;
    }
}