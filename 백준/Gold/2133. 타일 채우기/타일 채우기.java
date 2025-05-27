import java.io.*;
import java.util.*;

/*
음
2칸을 채우는 방법
ㅡ
ㅣㅣ
ㅣㅣ
ㅡ
ㅡ
ㅡ
ㅡ
3가지
3칸은 안채워져
4칸은?
저거에
ㅡㅡ

그림을 그리고 왔다
-2에서 일단 *3
-4부터 2의 배수로에 전부 *2
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    static int solution(int N) {
        int[] dp = new int[N + 1];

        dp[0] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 2] * 3;

            for (int j = 4; j <= i; j += 2) {
                dp[i] += dp[i - j] * 2;
            }
        }

        return dp[N];
    }
}