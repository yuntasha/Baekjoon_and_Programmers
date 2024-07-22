import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var map = new int[N][N];

        for (int i=0; i<N; i++){
            map[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, map));
    }

//    이거 비숍문제 머리 너무 안돌아가서 담에 ㄱㄱ;;

    static int solution(int N, int[][] map){
        var result = dfs(N, map, new boolean[N*2-1], new boolean[N*2-1], 0, -2);
        result += dfs(N, map, new boolean[N*2-1], new boolean[N*2-1], 0, -1);
        return result;
    }

    static int dfs(int N, int[][] map, boolean[] pline, boolean[] mline, int x, int y) {
        var result = 0;

        for (int j=y+2; j<N; j+=2) {
            if (map[x][j] == 0 || pline[x+j] || mline[x-j+N-1]) continue;
            pline[x+j] = true;
            mline[x-j+N-1] = true;
            result = Math.max(result, dfs(N, map, pline, mline, x, j)+1);
            pline[x+j] = false;
            mline[x-j+N-1] = false;
        }

        for (int i=x+1; i<N; i++){
            var init = 0;
            if ((x+y+10)%2 != i%2) init++;
            for (int j=init; j<N; j+=2){
                if (map[i][j] == 0 || pline[i+j] || mline[i-j+N-1]) continue;
                pline[i+j] = true;
                mline[i-j+N-1] = true;
                result = Math.max(result, dfs(N, map, pline, mline, i, j)+1);
                pline[i+j] = false;
                mline[i-j+N-1] = false;
            }
        }

        return result;
    }
}