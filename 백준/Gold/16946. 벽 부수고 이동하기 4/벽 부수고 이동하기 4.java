import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;


public class Main {

    static int N;
    static int M;
    static int[][] map;
    static int[][] group;
    static int[] cnt;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = NM[0];
        M = NM[1];

        map = new int[N][M];

        for (int i=0; i<N; i++){
            map[i] = Arrays.stream(bufferedReader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(Arrays.stream(solution()).map(arr -> Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(""))).collect(Collectors.joining("\n")));
    }

    static int[][] solution(){
        var result = new int[N][M];
        group = new int[N][M];
        cnt = new int[N*M+1];

        var now = 1;

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j]==1 || group[i][j]>0) continue;
                dfs(i, j, now++);
            }
        }

        var set = new HashSet<Integer>();

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] == 0) continue;
                result[i][j] = 1;
                for (int k=0; k<4; k++) {
                    var x = i+dx[k];
                    var y = j+dy[k];
                    if (!isIn(x, y)) continue;
                    set.add(group[x][y]);
                }
                for (int k: set) {
                    result[i][j] += cnt[k];
                    result[i][j]%=10;
                }
                set.clear();
            }
        }

        return result;
    }

    static void dfs(int n, int m, int now) {
        cnt[now]++;
        cnt[now]%=10;
        group[n][m] = now;
        for (int i=0; i<4; i++) {
            var x = n+dx[i];
            var y = m+dy[i];
            if (!isIn(x, y) || map[x][y]==1 || group[x][y]>0) continue;
            dfs(x, y, now);
        }
    }

    static boolean isIn(int n, int m){
        return 0<=n && n<N && 0<=m && m<M;
    }
}