import java.io.*;
import java.util.*;

/*
DP임

 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {
        Arrays.sort(arr);
        int[] DP = new int[N + 1];

        DP[1] = arr[0];

        for (int i = 2; i <= N; i++) {
            DP[i] = Math.max(DP[i - 2] + arr[i - 1] * arr[i - 2], DP[i - 1] + arr[i - 1]);
        }

        return DP[N];
    }
}