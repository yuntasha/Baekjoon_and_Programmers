import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var players = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var powers = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var D = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, players, powers, D));
    }

    static long solution(int N, int[] players, int[] powers, int D) {
        var init = 0L;
        for (int n=0; n<N; n++) {
            init += (long) players[n] * powers[n];
            players[n] = Math.min(D, players[n]);
        }

        var DP = new long[D+1];

        for (int n=0; n<N-1; n++) { // 이 레벨의 플레이어가
            while (players[n]>0) { // 인원수만큼만
                for (int d=D-1; d>=0; d--) { // 올리기 시작하는 날짜
                    for (int l=n+1; l<N; l++) {// 이 레벨로 올리는데
                        if (d+(l-n) > D) { // 날짜 넘어가면 끝
                            break;
                        }
                        DP[d+l-n] = Math.max(DP[d+l-n], DP[d]+powers[l]-powers[n]);
                    }
                }
                players[n]--;
            }
        }

        return init + DP[D];
    }
}

// 각 레벨에서 그 특정한 레벨로 옮기는 것 그것에 대한 시간
// 레벨 1->2, 1->3 ... 4->5까지 반복