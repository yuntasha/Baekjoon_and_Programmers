/*
사다리 정보는 a, b
b번 세로선과 b + 1번 세로선을 a번 점선 위치에서 연결
갈 수 있다면 그냥 무조건 옮긴다는 생각으로 전부 확인해주면?
최대 3인데?
300 * 299 * 298
300 * 299
300
이렇게 하면 될 듯?
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int H = Integer.parseInt(input.nextToken());

        boolean[][] garo = new boolean[N][H];

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            garo[b - 1][a - 1] = true;
        }
        System.out.println(solution(N, M, H, garo));
    }

    public static int solution(int N, int M, int H, boolean[][] garo) {
        int result = 4;

        if (isSuccess(N, M, H, garo)) return 0;

        for (int i1 = 0; i1 < N * H; i1++) {
            if (result <= 1) break;
            if (garo[i1 / H][i1 % H]) continue;
            garo[i1 / H][i1 % H] = true;
            if (isSuccess(N, M, H, garo)) result = 1;

            for (int i2 = i1 + 1; i2 < N * H; i2++) {
                if (result <= 2) break;
                if (garo[i2 / H][i2 % H]) continue;
                garo[i2 / H][i2 % H] = true;
                if (isSuccess(N, M, H, garo)) result = 2;

                for (int i3 = i2 + 1; i3 < N * H; i3++) {
                    if (result <= 3) break;
                    if (garo[i3 / H][i3 % H]) continue;
                    garo[i3 / H][i3 % H] = true;
                    if (isSuccess(N, M, H, garo)) result = 3;
                    garo[i3 / H][i3 % H] = false;
                }

                garo[i2 / H][i2 % H] = false;
            }
            garo[i1 / H][i1 % H] = false;
        }
        return result == 4 ? -1 : result;
    }

    public static boolean isSuccess(int N, int M, int H, boolean[][] garo) {
        for (int i = 0; i < N; i++) {
            if (!isSame(i, N, M, H, garo)) return false;
        }

        return true;
    }

    public static boolean isSame(int first, int N, int M, int H, boolean[][] garo) {
        int now = first;
        for (int h = 0; h < H; h++) {
            if (now < N - 1 && garo[now][h]) now++;
            else if (now > 0 && garo[now - 1][h]) now--;
        }

        return now == first;
    }
}