import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int max = 1;
    static boolean[] visited = new boolean[26];

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        var arr = new char[N][M];

        for (int i=0; i<N; i++){
            arr[i] = bufferedReader.readLine().strip().toCharArray();
        }

        visited[arr[0][0]-'A'] = true;
        
        dfs(N, M, arr, 0, 0, 1);

        System.out.println(max);
    }

    static void dfs(int N, int M, char[][] arr, int x, int y, int now) {
        var dests = new int[][]{{0,1}, {1,0}, {-1,0}, {0,-1}};

        for (int[] dest: dests){
            var dx = x + dest[0];
            var dy = y + dest[1];
            if (!(0<=dx && dx<N && 0<=dy && dy<M)) continue;
            if (visited[arr[dx][dy]-'A']) continue;
            visited[arr[dx][dy]-'A'] = true;
            max = Math.max(max, now+1);
            dfs(N, M, arr, dx, dy, now+1);
            visited[arr[dx][dy]-'A'] = false;
        }
    }
}