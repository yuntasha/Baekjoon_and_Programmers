import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] MAP;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        MAP = new int[N][N];
        for (int i=0; i<N; i++) {
            Arrays.fill(MAP[i], 1);
        }

        for (int i=0;i<M; i++) {
            var XYLF =  Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int x = XYLF[0]; x<XYLF[0]+XYLF[2]; x++) {
                for (int y = XYLF[1]; y<XYLF[1]+XYLF[2]; y++) {
                    MAP[x][y] = 1<<XYLF[3];
                }
            }
        }

        System.out.println(solution(N, M));
    }

    static int solution(int N, int M) {
        var result = 0;
        DP = new int[N][N];
        for (int seed1=1; seed1<7; seed1++) {
            for (int seed2 = seed1 + 1; seed2<8; seed2++) {
                var now = (1<<seed1) | (1<<seed2);
                for (int i=0; i<N; i++) {
                    DP[i][0] = (MAP[i][0] & now)>0?1:0;
                    DP[0][i] = (MAP[0][i] & now)>0?1:0;
                }
                for (int x=1; x<N; x++) {
                    for (int y=1; y<N; y++) {
                        if ((MAP[x][y] & now) > 0) {
                            DP[x][y] = Math.min(DP[x-1][y-1], Math.min(DP[x-1][y], DP[x][y-1]))+1;
                            result = Math.max(result, DP[x][y]);
                        } else {
                            DP[x][y] = 0;
                        }
                    }
                }
            }
        }

        return result*result;
    }
}