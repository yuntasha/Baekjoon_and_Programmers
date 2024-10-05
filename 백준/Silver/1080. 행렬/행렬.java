import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] degree;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var A = new int[N][];
        var B = new int[N][];

        for (int i=0; i<N; i++) {
            A[i] = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i=0; i<N; i++) {
            B[i] = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, M, A, B));
    }

    static int solution(int N, int M, int[][] A, int[][] B) {
        var result = 0;
        for (int i=0; i<N-2; i++) {
            for (int j=0; j<M-2; j++) {
                if (A[i][j]!=B[i][j]) {
                    reverse(i, j, A);
                    result++;
                }
            }
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (A[i][j]!=B[i][j]) return -1;
            }
        }

        return result;
    }

    static void reverse(int n, int m, int[][] A) {
        for (int i=n; i<n+3; i++) {
            for (int j=m; j<m+3; j++) {
                A[i][j] = A[i][j]^1;
            }
        }
    }
}