import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
DP로 풀어야 할 것 같다
DP[가로 길이][세로 길이] = 0으로 이렇게 구해야할 것 같음
가로로 잘랐을때와 세로로 잘랐을떄 기준으로 만들어줌
그렇게 전체 탐색
 */

public class Main {

    static int MAX_VALUE = 200_000;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] DP = new int[W+1][H+1];

        for (int i=0; i<=W; i++) {
            for (int j=0; j<=H; j++) {
                DP[i][j] = MAX_VALUE;
            }
        }

        DP[1][1] = 1;

        int N = Integer.parseInt(bf.readLine());

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());

            DP[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 0;
        }

        System.out.println(solution(W, H, DP));
    }

    public static int solution(int W, int H, int[][] DP) {
        for (int w=1; w<=W; w++) {
            for (int h=1; h<=H; h++) {

                for (int cutW = 1; cutW <= w/2; cutW++) {
                    DP[w][h] = Math.min(DP[w][h], DP[w-cutW][h] + DP[cutW][h]);
                }

                for (int cutH = 1; cutH <= h/2; cutH++) {
                    DP[w][h] = Math.min(DP[w][h], DP[w][h-cutH] + DP[w][cutH]);
                }

            }
        }

        return DP[W][H];
    }
}