/*
3차원 배열로 각 인덱스에 각 수가 몇개씩 들어갔는지 체크
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] arr = new int[N][];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][][] sum = solution(N, arr);

        int T = Integer.parseInt(bf.readLine());
        StringBuilder output = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            output.append("\n").append(query(Integer.parseInt(input.nextToken()) - 1, Integer.parseInt(input.nextToken()) - 1, Integer.parseInt(input.nextToken()) - 1, Integer.parseInt(input.nextToken()) - 1, sum));
        }

        System.out.println(output.substring(1));
    }

    static int[][][] solution(int N, int[][] arr) {
        int[][][] count = new int[N][N][11];

        count[0][0][arr[0][0]]++;

        for (int i = 1; i < N; i++) {
            for (int n = 0; n < 11; n++) {
                count[0][i][n] = count[0][i - 1][n];
            }
            count[0][i][arr[0][i]]++;
        }

        for (int i = 1; i < N; i++) {
            count[i][0][arr[i][0]]++;

            for (int j = 1; j < N; j++) {
                for (int n = 0; n < 11; n++) {
                    count[i][j][n] = count[i][j - 1][n];
                }
                count[i][j][arr[i][j]]++;
            }

            for (int j = 0; j < N; j++) {
                for (int n = 0; n < 11; n++) {
                    count[i][j][n] += count[i - 1][j][n];
                }
            }
        }

//        for (int n = 1; n < 10; n++) {
//            System.out.println("n = " + n);
//
//            for (int i = 0; i < N; i++) {
//                StringJoiner sb = new StringJoiner(" ");
//                for (int j = 0; j < N; j++) {
//                    sb.add(String.valueOf(count[i][j][n]));
//                }
//
//                System.out.println(sb);
//            }
//        }

        return count;
    }

    static int query(int x1, int y1, int x2, int y2, int[][][] count) {
        if (x1 == 0 && y1 == 0) {
            return count(count[x2][y2]);
        }

        if (x1 == 0) {
            int result = 0;

            for (int i = 0; i < 11; i++) {
                if (count[x2][y2][i] - count[x2][y1 - 1][i] > 0) result++;
            }

            return result;
        }

        if (y1 == 0) {
            int result = 0;

            for (int i = 0; i < 11; i++) {
                if (count[x2][y2][i] - count[x1 - 1][y2][i] > 0) result++;
            }

            return result;
        }

        int result = 0;

        for (int i = 0; i < 11; i++) {
            if (count[x2][y2][i] - count[x1 - 1][y2][i] - count[x2][y1 - 1][i] + count[x1 - 1][y1 - 1][i] > 0) result++;
        }

        return result;
    }

    static int count(int[] count) {
        int result = 0;

        for (int i = 0; i < 11; i++) {
            if (count[i] > 0) result++;
        }

        return result;
    }
}