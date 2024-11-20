import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var arr = new long[N][];

        for (int i=0;i<N; i++) {
            arr[i] = Arrays.stream(bf.readLine().split("")).mapToLong(Long::parseLong).toArray();
        }

        System.out.println(solution(N, M, arr));
    }

    static long solution(int N, int M, long[][] arr) {
        var sum = makeSum(N, M, arr);

        var result = 0L;

        result = Math.max(result, case1(N, M, sum));
        result = Math.max(result, case2(N, M, sum));
        result = Math.max(result, case3(N, M, sum));
        result = Math.max(result, case4(N, M, sum));
        result = Math.max(result, case5(N, M, sum));
        result = Math.max(result, case6(N, M, sum));

        return result;
    }

    static long[][] makeSum(int N, int M, long[][] arr) {
        var result = new long[N][M];

        result[0][0] = arr[0][0];

        for (int i=1; i<M; i++) {
            result[0][i] = arr[0][i] + result[0][i-1];
        }

        for (int n=1; n<N; n++) {
            result[n][0] = result[n-1][0] + arr[n][0];
            for (int m=1; m<M; m++) {
                result[n][m] = result[n][m-1] + arr[n][m] + result[n-1][m] - result[n-1][m-1];
            }
        }

        return result;
    }


    // 세로로 3개
    static long case1(int N, int M, long[][] sum) {
        var result = 0L;
        for (int i=0; i<N-1; i++) {
            for (int j=i+1; j<N-1; j++) {
                result =  Math.max(result, sum[i][M-1] * (sum[j][M-1] - sum[i][M-1]) * (sum[N-1][M-1] - sum[j][M-1]));
            }
        }

        return result;
    }

    // 가로로 3개
    static long case2(int N, int M, long[][] sum) {
        var result = 0L;
        for (int i=0; i<M-1; i++) {
            for (int j=i+1; j<M-1; j++) {
                result =  Math.max(result, sum[N-1][i] * (sum[N-1][j] - sum[N-1][i]) * (sum[N-1][M-1] - sum[N-1][j]));
            }
        }

        return result;
    }

    // 위에 1개 아래 2개
    static long case3(int N, int M, long[][] sum) {
        var result = 0L;

        for (int i=0; i<N-1; i++) {
            for (int j=0; j<M-1; j++) {
                result =  Math.max(result, sum[i][M-1] * find(i+1, 0, N-1, j, sum) * find(i+1, j+1, N-1, M-1, sum));
            }
        }

        return result;
    }

    // 위에 2개 아래 1개
    static long case4(int N, int M, long[][] sum) {
        var result = 0L;

        for (int i=0; i<N-1; i++) {
            for (int j=0; j<M-1; j++) {
                result =  Math.max(result, find(i+1, 0, N-1, M-1, sum) * find(0, 0, i, j, sum) * find(0, j+1, i, M-1, sum));
            }
        }

        return result;
    }

    // 좌 2개 우 1개
    static long case5(int N, int M, long[][] sum) {
        var result = 0L;

        for (int i=0; i<N-1; i++) {
            for (int j=0; j<M-1; j++) {
                result =  Math.max(result, find(0, j+1, N-1, M-1, sum) * find(0, 0, i, j, sum) * find(i+1, 0, N-1, j, sum));
            }
        }

        return result;
    }

    // 좌 1개 우 2개
    static long case6(int N, int M, long[][] sum) {
        var result = 0L;

        for (int i=0; i<N-1; i++) {
            for (int j=0; j<M-1; j++) {
                result =  Math.max(result, find(0, 0, N-1, j, sum) * find(0, j+1, i, M-1, sum) * find(i+1, j+1, N-1, M-1, sum));
            }
        }

        return result;
    }

    private static long find(int x1, int y1, int x2, int y2, long[][] sum) {
        if (x1==0 && y1 == 0) return sum[x2][y2];
        if (x1==0) return sum[x2][y2] - sum[x2][y1-1];
        if (y1==0) return sum[x2][y2] - sum[x1-1][y2];
        return sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
    }
}