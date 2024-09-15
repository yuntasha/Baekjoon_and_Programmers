import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static char[][] arr;
    static int N, M;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][] visited;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = NM[0];
        M = NM[1];

        arr = new char[N][M];
        visited = new boolean[N][M];
        dp = new int[N][M];

        for (int i=0; i<N; i++){
            arr[i] = bf.readLine().strip().toCharArray();
        }
        System.out.println(solution());
    }

    static int solution() {
        return find(0, 0);
    }

    static int find(int n, int m){
        if (dp[n][m]!=0) return dp[n][m];
        var now = getInt(arr[n][m]);
        var result = 0;
        for (int i=0; i<4; i++) {
            var x = dx[i]*now + n;
            var y = dy[i]*now + m;
            if (isEnd(x, y)) {
                result = Math.max(result, 1);
                continue;
            }
            if (visited[x][y]) {
                return -1;
            }
            visited[x][y] = true;
            var next = find(x, y);
            visited[x][y] = false;
            if (next==-1) return -1;
            result = Math.max(result, next+1);
        }
        dp[n][m] = result;
        return result;
    }

    static boolean isEnd(int n, int m) {
        return !isIn(n, m) || isHole(n, m);
    }

    static boolean isHole(int n, int m) {
        return arr[n][m]=='H';
    }

    static boolean isIn(int n, int m) {
        return 0<=n && n<N && 0<=m && m<M;
    }
    static int getInt(char c){
        return c-'0';
    }
}