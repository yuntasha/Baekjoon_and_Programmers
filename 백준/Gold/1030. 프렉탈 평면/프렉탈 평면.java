import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    static int S, N, K, R1, R2, C1, C2, MapSize;
    static int[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var SNKRC = Arrays.stream(bufferedReader.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray();
        S = SNKRC[0];
        N = SNKRC[1];
        K = SNKRC[2];
        R1 = SNKRC[3];
        R2 = SNKRC[4];
        C1 = SNKRC[5];
        C2 = SNKRC[6];
        arr = new int[R2-R1+1][C2-C1+1];
        System.out.println(solution());
    }

    static String solution(){
        MapSize = (int)Math.pow(N, S);
        makeShape(MapSize, 0, 0, false);
        var sb = new StringBuilder();
        for (int i=0; i<R2-R1+1; i++) {
            for (int j=0; j<C2-C1+1; j++){
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    static void makeShape(int nSize, int x, int y, boolean isBlack){
        if (nSize==1) {
            arr[x-R1][y-C1] = isBlack?1:0;
            return;
        }
        var block = nSize/N;
        var blackS = (N-K)/2;
        var blackE = (N-K)/2+K;
        for (int i=0; i<N; i++) {
            var dx = x+i*block;
            for (int j=0; j<N; j++) {
                var dy = y+j*block;
                if (!(dx<=R2 && dx+block>R1 && dy<=C2 && dy+block>C1)) continue;
                makeShape(block, dx, dy, isBlack||(blackS<=i && i<blackE && blackS<=j && j<blackE));
            }
        }
    }
}