/*
반드시 하나 이상은 구매해야한다는 내용이 있음
그럼 앞에서부터 차례대로 구매하는데 앞에 값이 음수면 처리하고 아니면 더해주자
s를 둬서 미리 정해둔 다음에 그 후 처리

1_000 * 1_000 * 10_000
10_000_000_000
 */

import java.io.*;
import java.util.*;


public class Main {

    static int MIN = -10001;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] nArr = new int[N];
        int[][] arrs = new int[N][];

        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(bf.readLine());

            StringTokenizer input = new StringTokenizer(bf.readLine());

            arrs[i] = new int[nArr[i]];

            for (int j = 0; j < nArr[i]; j++) arrs[i][j] = Integer.parseInt(input.nextToken());
        }

        System.out.println(solution(N, nArr, arrs));
    }

    public static String solution(int N, int[] nArr, int[][] arrs) {
        long sum = 0;

        int[][] result = new int[N][2];

        for (int i = 0; i < N; i++) {
            sum += find(nArr[i], arrs[i], result[i]);
        }

        StringBuilder output = new StringBuilder();

        output.append(sum);

        for (int i = 0; i < N; i++) {
            output.append("\n").append(result[i][0]).append(" ").append(result[i][1]);
        }

        return output.toString();
    }

    public static long find(int N, int[] arr, int[] result) {
        int s = -N * 2;
        int e = 0;

        long max = MIN;
        long now = 0;
        int ns = 0;

        for (int i = 0; i < N; i++) {
            if (now <= 0) {
                now = 0;
                ns = i;
            }

            now += arr[i];

            if ((max == now && i - ns < e - s) || max < now) {
                e = i;
                s = ns;
                max = now;
            }
        }

        result[0] = s + 1;
        result[1] = e + 1;

        return max;
    }
}