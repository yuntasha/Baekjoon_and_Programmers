import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var RCT = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var R = RCT[0];
        var C = RCT[1];
        var T = RCT[2];

        var arr = new int[R][C];
        var aircon = new int[2];


        for (int i=0; i<R; i++){
            arr[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<C; j++){
                if (arr[i][j] == -1 && aircon[0] == 0) aircon = new int[]{i, j};
            }
        }

        System.out.println(solution(R, C, T, arr, aircon));
    }

    static int solution(int R, int C, int T, int[][] arr, int[] aircon){
        for (int i=0; i<T; i++){
            spread(R, C, arr);
            chungjung(R, C, arr, aircon);
        }

        return Arrays.stream(arr).mapToInt(a -> Arrays.stream(a).sum()).sum()+2;
    }

    static void spread(int R, int C, int[][] arr) {
        var dx = new int[]{0, 1, 0, -1};
        var dy = new int[]{1, 0, -1, 0};
        var temp = new int[R][C];
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                if (arr[i][j] <= 0) continue;
                var cnt = 0;
                for (int k=0; k<4; k++){
                    var x = i + dx[k];
                    var y = j + dy[k];
                    if (!isIn(R, C, x, y) || arr[x][y] == -1) continue;
                    cnt++;
                    temp[x][y] += arr[i][j]/5;
                }
                temp[i][j]-=(arr[i][j]/5)*cnt;
            }
        }
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                arr[i][j]+=temp[i][j];
            }
        }
    }

    static void chungjung(int R, int C, int[][] arr, int[] aircon){
        var temp = 0;
        var x = aircon[0];
        var y = aircon[1];
        var dx = new int[]{0, -1, 0, 1, 0};
        var dy = new int[]{1, 0, -1, 0, 1};
        for (int i=0; i<5; i++){
            x+=dx[i];
            y+=dy[i];
            while (isIn(R, C, x, y) && (aircon[0] != x || aircon[1] !=y)) {
                var t = temp;
                temp = arr[x][y];
                arr[x][y] = t;
                x+=dx[i];
                y+=dy[i];
            }
            if (aircon[0] == x && aircon[1] == y) break;
            x-=dx[i];
            y-=dy[i];
        }

        temp = 0;
        x = aircon[0]+1;
        y = aircon[1];

        for (int i=0; i<5; i++){
            x-=dx[i];
            y+=dy[i];
            while (isIn(R, C, x, y) && (aircon[0]+1 != x || aircon[1] != y)) {
                var t = temp;
                temp = arr[x][y];
                arr[x][y] = t;
                x-=dx[i];
                y+=dy[i];
            }
            if (aircon[0]+1 == x && aircon[1] == y) break;
            x+=dx[i];
            y-=dy[i];
        }
    }

    static boolean isIn(int N, int M, int x, int y){
        return 0<=x && x<N && 0<=y && y<M;
    }
}