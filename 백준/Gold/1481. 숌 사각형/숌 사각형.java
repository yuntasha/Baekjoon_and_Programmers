import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    static int[] GARO_COUNT;
    static int[] SERO_COUNT;
    static int[] GARO_BITS;
    static int[] SERO_BITS;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(bf.readLine());

        var N = Integer.parseInt(st.nextToken());
        var D = Integer.parseInt(st.nextToken());

        System.out.println(Arrays.stream(solution(N, D)).map(arr -> Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" "))).collect(Collectors.joining("\n")));
    }

    static int[][] solution(int N, int D) {
        var result = new int[N][N];
        GARO_COUNT = new int[N];
        SERO_COUNT = new int[N];
        GARO_BITS = new int[N];
        SERO_BITS = new int[N];

        dfs(N, D, result, 0, 0);

        return result;
    }

    static boolean dfs(int N, int D, int[][] result, int x, int y) {
        if (x==N) {
            return true;
        }
        var nextX = y<N-1?x:x+1;
        var nextY = y<N-1?y+1:0;

//        System.out.println(Arrays.stream(result).map(arr -> Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" "))).collect(Collectors.joining("\n", "x = " + x + " y = " + y + "\n", "\n")));

        for (int i=0; i<D; i++) {
            var nowGaro = GARO_BITS[x];
            var nowSero = SERO_BITS[y];
            if ((nowGaro & (1<<i)) == 0) { // 가로줄에 없던 수
                GARO_BITS[x] = GARO_BITS[x] | (1<<i);
                GARO_COUNT[x]++;
            }

            if ((nowSero & (1<<i)) == 0) { // 세로줄에 없던 수
                SERO_BITS[y] = SERO_BITS[y] | (1<<i);
                SERO_COUNT[y]++;
            }

            if (N-x-1 >= D-SERO_COUNT[y] && N-y-1 >= D-GARO_COUNT[x]) { // 세로 기준 - 앞으로 넣을 수 있는 숫자 < 넣어야 하는 숫자 -> 즉 실패라면 false
                result[x][y] = i;
                if (dfs(N, D, result, nextX, nextY)) return true;
            }

            if ((nowGaro & (1<<i)) == 0) { // 가로줄에 없던 수
                GARO_BITS[x] = nowGaro;
                GARO_COUNT[x]--;
            }

            if ((nowSero & (1<<i)) == 0) { // 세로줄에 없던 수
                SERO_BITS[y] = nowSero;
                SERO_COUNT[y]--;
            }
        }

        return false;
    }
}