import java.io.*;
import java.util.*;

public class Main {

    static int MIN = -100_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(bf.readLine());

        System.out.print(solution(N, M, arr));
    }

    public static int solution(int N, int M, int[] arr) {
        int result = Integer.MAX_VALUE;

        Arrays.sort(arr);

        int s = 0;
        int e = 1;

        while (e < N) {
            if (s == e) {
                e++;
                continue;
            }
            if (arr[e] - arr[s] >= M) {
                result = Math.min(result, arr[e] - arr[s]);
                s++;
            } else {
                e++;
            }
        }

        return result;
    }
}