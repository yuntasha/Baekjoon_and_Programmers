import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int H = Integer.parseInt(input.nextToken());
        int W = Integer.parseInt(input.nextToken());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(H, W, arr));
    }

    static int solution(int H, int W, int[] arr) {
        int[] prev = new int[W];
        int[] suf = new int[W];

        prev[0] = arr[0];

        for (int i = 1; i < W; i++) {
            prev[i] = Math.max(prev[i - 1], arr[i]);
        }

        suf[W - 1] = arr[W - 1];

        for (int i = W - 2; i >= 0; i--) {
            suf[i] = Math.max(suf[i + 1], arr[i]);
        }

        int result = 0;

        for (int i = 0; i < W; i++) {
            result += Math.max(Math.min(prev[i], suf[i]) - arr[i], 0);
        }

        return result;
    }
}