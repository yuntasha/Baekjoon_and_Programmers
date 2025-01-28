import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
오른쪽 위에부분만 찾고 *4
그다음에 x좌표가 늘어갈수록 y좌표는 줄어들 것이기 때문에 쫙 탐색하면 될듯
움직이는 방법이 오 아 오아
그렇게 만ㄷ들고
해당 조건이 맞으면 성림
 */

public class Main {

    static long[] dx = {0L, 1L, 1L};
    static long[] dy = {-1L, 0L, -1L};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(solution(Long.parseLong(bf.readLine())));
    }

    public static long solution(long N) {
        N = N >> 1;
        long degree = N * N;

        long result = 1L;

        long x = 0;
        long y = N - 1;

        Loop : while (y >= 0) {
            for (int i = 0; i < 3; i++) {
                long nx = x + dx[i];
                long ny = y + dy[i];

                if (check(nx, ny, degree)) {
                    x = nx;
                    y = ny;
                    result++;
                    break;
                }

                if (i==2) break Loop;
            }
        }

        return result * 4;
    }

    static boolean check(long x, long y, long degree) {
        return getD(x, y) < degree && getD(x + 1, y + 1) > degree;
    }

    static long getD(long x, long y) {
        return x * x + y * y;
    }
}