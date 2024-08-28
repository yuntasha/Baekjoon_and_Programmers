import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] dp = new int[15][1024*32];

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var arr = new int[N][M];

        for (int i=0; i<N; i++){
            arr[i] = Arrays.stream(bufferedReader.readLine().strip().split("")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(solution(N, M, arr));
    }

    static int solution(int N, int M, int[][] arr){
        var xpm = new int[]{-1, -1, 1, 1};
        var ypm = new int[]{-1, 1, -1, 1};
        var result = -1;
        for (int dx=0; dx<=N; dx++) {
            for (int dy=0; dy<=M; dy++) {
                if (dx==0 && dy==0) continue;
                for (int startX=0; startX<N; startX++) {
                    for (int startY=0; startY<M; startY++) {
                        for (int i=0; i<4; i++) {
                            result = Math.max(result, findNum(N, M, arr, startX, startY, dx*xpm[i], dy*ypm[i]));
                        }
                    }
                }
            }
        }
        return result;
    }

    static int findNum(int N, int M, int[][] arr, int startX, int startY, int dx, int dy){
        var result = -1;
        var num=0;
        var i = startX;
        var j = startY;
        while (0<=i && i<N && 0<=j && j<M) {
            num*=10;
            num+=arr[i][j];
            if (Math.sqrt(num) % 1 == 0) result = num;
            i+=dx;
            j+=dy;
        }
        return result;
    }
}