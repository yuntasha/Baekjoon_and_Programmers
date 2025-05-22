import java.io.*;
import java.util.*;

/*
주사위를 쌓는데 위아래는 숫자가 같아야함
ABCDEF순서대로 주어짐
A F
B D
C E
0 5
1 3
2 4


나머진 상관이 없다...!

사실상 밑에 숫자를 뭘 둘것이냐에 따라 정해져있음
 */

public class Main {

    static int[] oppo = new int[]{5, 3, 4, 1, 2, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] dices = new int[N][];

        for (int i = 0; i < N; i++) {
            dices[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, dices));
    }

    static int solution(int N, int[][] dices) {
        int result = 0;

        int num = 1;
        int now = 0;

        for (int i = 0; i < N; i++) {
            int idx = findIdx(dices[i], num);
            now += findMax(dices[i], idx);
            num = dices[i][oppo[idx]];
        }

        result = Math.max(result, now);

        num = 2;
        now = 0;

        for (int i = 0; i < N; i++) {
            int idx = findIdx(dices[i], num);
            now += findMax(dices[i], idx);
            num = dices[i][oppo[idx]];
        }

        result = Math.max(result, now);

        num = 3;
        now = 0;

        for (int i = 0; i < N; i++) {
            int idx = findIdx(dices[i], num);
            now += findMax(dices[i], idx);
            num = dices[i][oppo[idx]];
        }

        result = Math.max(result, now);

        num = 4;
        now = 0;

        for (int i = 0; i < N; i++) {
            int idx = findIdx(dices[i], num);
            now += findMax(dices[i], idx);
            num = dices[i][oppo[idx]];
        }

        result = Math.max(result, now);

        num = 5;
        now = 0;

        for (int i = 0; i < N; i++) {
            int idx = findIdx(dices[i], num);
            now += findMax(dices[i], idx);
            num = dices[i][oppo[idx]];
        }

        result = Math.max(result, now);

        num = 6;
        now = 0;

        for (int i = 0; i < N; i++) {
            int idx = findIdx(dices[i], num);
            now += findMax(dices[i], idx);
            num = dices[i][oppo[idx]];
        }

        result = Math.max(result, now);

        return result;
    }

    static int findMax(int[] dice, int idx) {
        int result = 0;

        for (int i = 0; i < 6; i++) {
            if (i == idx || i == oppo[idx]) continue;
            result = Math.max(result, dice[i]);
        }

        return result;
    }

    static int findIdx(int[] dice, int now) {
        for (int i = 0; i < 6; i++) {
            if (dice[i] == now) return i;
        }
        return 5;
    }
}