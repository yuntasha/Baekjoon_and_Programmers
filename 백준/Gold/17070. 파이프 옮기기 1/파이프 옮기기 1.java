import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

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
        var dp = new int[3][N][N-1];
        dp[0][0][0] = 1;
        var dest = new int[][]{{0, 1}, {1, 1}, {1, 0}};
        for (int i=0; i<N; i++){
            for (int j=0; j<N-1; j++){

                for (int d=0; d<3; d++){
                    var x = i+dest[d][0];
                    var y = j+dest[d][1];

                    if (!(0<=x && x<N && 0<=y && y<N-1)) continue;
                    if (arr[x][y+1] == 1) continue;
                    if (d == 1 && (arr[x-1][y+1] == 1 || arr[x][y] == 1)) continue;

                    for (int lastD=0; lastD<3; lastD++){

                        if (Math.abs(lastD-d)==2) continue;

                        dp[d][x][y] += dp[lastD][i][j];
                    }

                    var decode = new int[N][N-1];

                    for (int i1=0; i1<N; i1++){
                        for (int i2=0; i2<N-1; i2++){
                            decode[i1][i2] = dp[0][i1][i2] + dp[1][i1][i2] + dp[2][i1][i2];
                        }
                    }
                }
            }
        }


        return dp[0][N-1][N-2] + dp[1][N-1][N-2] + dp[2][N-1][N-2];
    }
}