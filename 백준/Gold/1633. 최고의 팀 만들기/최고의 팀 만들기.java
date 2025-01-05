import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
3가지 경우
이 사람이 아무것도 아닌 경우 DP[N][흑 참가][백 참가] = 그대로 넘어옴
이 사람이 흑인 경우 DP[N][흑 참가][백 참가] = DP[N-1][흑 참가-1][백 참가]
이 사람이 백인 경우 DP[N][흑 참가][백 참가] = DP[N-1][흑 참가][백 참가]
그럼 DP[사람 수][흑 참가][백 참가]
*/

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[][] ability = new int[1001][];

        int idx = 0;

        while (true) {
            try {
                ability[idx] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                idx++;
            } catch (Exception e) {
                break;
            }
        }

        System.out.println(solution(idx, ability));
    }

    static int solution(int N, int[][] ablility) {
        int[][][] DP = new int[N][16][16];

        DP[0][0][0] = 0;
        DP[0][1][0] = ablility[0][0];
        DP[0][0][1] = ablility[0][1];

        for (int i=1; i<N; i++) {
            for (int w=0; w<16; w++) {
                for (int b=0; b<16; b++) {
                    // 아무것도 안함
                    DP[i][w][b] = DP[i-1][w][b];

                    // 백을 뽑자
                    if (w > 0) {
                        DP[i][w][b] = Math.max(DP[i][w][b], DP[i-1][w-1][b] + ablility[i][0]);
                    }

                    // 흑을 뽑자
                    if (b > 0) {
                        DP[i][w][b] = Math.max(DP[i][w][b], DP[i-1][w][b-1] + ablility[i][1]);
                    }
                }
            }
        }

        return DP[N-1][15][15];
    }
}
