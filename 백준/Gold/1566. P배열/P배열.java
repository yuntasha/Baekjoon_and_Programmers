import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, M, arr));
    }

    static int solution(int N, int M, int[][] arr) {
        int result = dfs(0, 0);
        return result==Integer.MAX_VALUE?-1:result;
    }

    static int dfs(int count, int now) {
        if (now == N) {
            int add = isSuccess();
            return add >= 0 ? count + add : Integer.MAX_VALUE;
        }

        int result = Integer.MAX_VALUE;
        result = Math.min(result, dfs(count, now + 1));


        for (int i=0; i<M; i++) {
            arr[now][i] = -arr[now][i];
        }
        result = Math.min(result, dfs(count + 1, now + 1));
        for (int i=0; i<M; i++) {
            arr[now][i] = -arr[now][i];
        }

        return result;
    }

    static int isSuccess() {

        int count = 0;
        int[] garo = new int[N];

        for (int j = 0; j < M; j++) {
            int now = 0;
            for (int i = 0; i < N; i++) {
                now += arr[i][j];
            }

            if (now == 0) return -1;
            int v = 1;
            if (now < 0) {
                v = -1;
                count++;
            }
            for (int i = 0; i < N; i++) {
                garo[i] += arr[i][j] * v;
            }
        }

        for (int i = 0; i < N; i++) {
            if (garo[i] <= 0) return -1;
        }

        return count;
    }
}