import java.io.*;
import java.util.*;

/*
200 * 200인데
40000이면 4만
왼위 오아 이런거 다 제외하고 하면 딱 4억?
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] arr = new int[N][M + 1];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(input.nextToken()) + arr[i][j - 1];
            }
        }

        System.out.println(solution(N, M, arr));
    }

    static int solution(int N, int M, int[][] arr) {
        int result = Integer.MIN_VALUE;

        for (int sm = 1; sm <= M; sm++) {
            for (int em = sm; em <= M; em++) {
                for (int sn = 0; sn < N; sn++) {
                    int now = 0;
                    for (int en = sn; en < N; en++) {
                        if (sm == 0) now += arr[en][em];
                        else now += arr[en][em] - arr[en][sm - 1];

                        result = Math.max(result, now);
                    }
                }
            }
        }

        return result;
    }
}