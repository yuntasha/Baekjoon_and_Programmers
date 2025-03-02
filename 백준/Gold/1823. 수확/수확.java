import java.io.*;
import java.util.*;

/*
투 포인터하면 또 문제 생기네..
그러면 DP로 하자
남은 시작과 끝으로 설정
안에서부터 채워간다는 설정으로 바꾸자
그러면 s와 e가 있다고 할 때
우리가 베어낸 개수가 s + (size - e - 1)임
그러면 거기에 + 1을 해주어야 이번에 얻는 점수가 나옴
그러면 size + s - e가 이번에 얻을 점수라는 뜻
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Integer> byu = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            byu.add(Integer.parseInt(bf.readLine()));
        }

        System.out.println(solution(N, byu));
    }

    static int solution(int N, List<Integer> byu) {
        int[][] dp = new int[N][N];

        return dfs(N, byu, 0, N - 1, dp);
    }

    static int dfs(int N, List<Integer> byu, int s, int e, int[][] dp) {
        if (dp[s][e] > 0) return dp[s][e];
        if (s == e) return dp[s][e] = byu.get(s) * N;
        int now = N + s - e;
        return dp[s][e] = Math.max(dfs(N, byu, s + 1, e, dp) + now * byu.get(s), dfs(N, byu, s, e - 1, dp) + now * byu.get(e));
    }
}