import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
음...
일단... 음....
기본은 R부터 채운다
U가 0개부터 N개일때까지 구해봄
X+Y < N인 경우
X+Y == N인 경우
X+Y > N인 경우
이렇게 3개로 나뉨

X+Y < N인 경우 UR 개수만큼 적고 나머지 R로 가득 채움

X+Y == N인 경우 R부터 채우고 U을 채움

X+Y > N인 경우
    n(X+Y) == N인 경우 : R부터 개수에 맞게 채우고 U를 채움
    n(X+Y) > N인 경우 : 남은 칸수인 X나 Y가 현재 X, Y보다 작은지 확인
        작다면 XY에 맞게 채우고 R부터 채운 U를 마저 채움
    이거 뭐 한번에 처리될 것 같고.... 근데 이거 순서대로 나오려면 앞에 R이 나와야하니까
    R이 적은거부터 탐색하는게 정렬될듯
    그래야 앞에 R이 들어감
 */

public class Main {

    static String FAIL = "-1";
    static String UP = "U";
    static String RIGHT = "R";

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = -Integer.parseInt(st.nextToken());
        int Y = -Integer.parseInt(st.nextToken());
        X += Integer.parseInt(st.nextToken());
        Y += Integer.parseInt(st.nextToken());

        System.out.println(solution(N, X, Y));
    }

    public static String solution(int N, int X, int Y) {
        if (X < 0 || Y < 0) return FAIL;

        StringBuilder result = new StringBuilder();

        if (X + Y <= N) {
            addResult(result, X, Y);
            addResult(result, N - X - Y, 0);
            return result.toString();
        }

        int n = (X+Y)/N;

        for (int r = 0; r <= N; r++) {
            if (n * r > X) continue;
            int u = N - r;
            if (n * u > Y) continue;
            int dr = X - n * r;
            if (dr > r) continue;
            int du = Y - n * u;
            if (du > u) continue;
            addResult(result, dr, du);
            addResult(result, r - dr, u - du);
            return result.toString();
        }

        return FAIL;
    }

    public static void addResult(StringBuilder result, int r, int u) {
        for (int i = 0; i < r; i++) result.append(RIGHT);
        for (int i = 0; i < u; i++) result.append(UP);
    }
}