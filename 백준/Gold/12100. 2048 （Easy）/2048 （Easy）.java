import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());
        var arr = new int[N][N];
        for (int i=0; i<N; i++){
            arr[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[][] arr){
        return find(N, arr, 0);
    }

    static int find(int N, int[][] arr, int depth) {
        if (depth==5) {
            return Arrays.stream(arr).flatMapToInt(Arrays::stream).max().getAsInt();
        } else {
            depth++;
            var result = 0;
            result = Math.max(find(N, moveR(N, arr), depth), result);
            result = Math.max(find(N, moveL(N, arr), depth), result);
            result = Math.max(find(N, moveD(N, arr), depth), result);
            result = Math.max(find(N, moveU(N, arr), depth), result);
            return result;
        }
    }

    static int[][] moveR(int N, int[][] arr) {
        var result = new int[N][N];
        for (int i=0; i<N; i++) { //dx나 dy로 나누어서 결정
            var idx = N-1;
            var prev = arr[i][N-1];
            for (int now=N-2; now>=0; now--) {
                if (arr[i][now]==0) continue;
                if (arr[i][now] == prev) {
                    result[i][idx--] = prev*2;
                    prev = 0;
                } else {
                    if (prev!=0) {
                        result[i][idx--] = prev;
                    }
                    prev = arr[i][now];
                }
            }
            result[i][idx] = prev;
        }
        return result;
    }

    static int[][] moveL(int N, int[][] arr) {
        var result = new int[N][N];
        for (int i=0; i<N; i++) { //dx나 dy로 나누어서 결정
            var idx = 0;
            var prev = arr[i][0];
            for (int now=1; now<N; now++) {
                if (arr[i][now]==0) continue;
                if (arr[i][now] == prev) {
                    result[i][idx++] = prev*2;
                    prev = 0;
                } else {
                    if (prev!=0) {
                        result[i][idx++] = prev;
                    }
                    prev = arr[i][now];
                }
            }
            result[i][idx] = prev;
        }
        return result;
    }

    static int[][] moveD(int N, int[][] arr) {
        var result = new int[N][N];
        for (int i=0; i<N; i++) { //dx나 dy로 나누어서 결정
            var idx = N-1;
            var prev = arr[N-1][i];
            for (int now=N-2; now>=0; now--) {
                if (arr[now][i]==0) continue;
                if (arr[now][i] == prev) {
                    result[idx--][i] = prev*2;
                    prev = 0;
                } else {
                    if (prev!=0) {
                        result[idx--][i] = prev;
                    }
                    prev = arr[now][i];
                }
            }
            result[idx][i] = prev;
        }
        return result;
    }

    static int[][] moveU(int N, int[][] arr) {
        var result = new int[N][N];
        for (int i=0; i<N; i++) { //dx나 dy로 나누어서 결정
            var idx = 0;
            var prev = arr[0][i];
            for (int now=1; now<N; now++) {
                if (arr[now][i]==0) continue;
                if (arr[now][i] == prev) {
                    result[idx++][i] = prev*2;
                    prev = 0;
                } else {
                    if (prev!=0) {
                        result[idx++][i] = prev;
                    }
                    prev = arr[now][i];
                }
            }
            result[idx][i] = prev;
        }
        return result;
    }
}