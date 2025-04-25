/*
5분 적용이라고 할 떄
3분이면 7분까지 8분은 적용안됨

1_000_000_000
너무 크다

1_000_000으로 만들면
dp[다음 인덱스 ][횟수]

어찌 될 것 같은데

그럼 받고 정렬시켜
그리고 DP로 찾자


NlogN으로 정렬
(N logN * 10)으로 다음 탐색 후 그거
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int T = Integer.parseInt(input.nextToken());
        int N = Integer.parseInt(input.nextToken());
        int D = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        List<Integer> time = new ArrayList<>();

        input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            time.add(Integer.parseInt(input.nextToken()));
        }

        System.out.println(solution(T, N, D, K, time));
    }

    static int solution(int T, int N, int D, int K, List<Integer> time) {
        time.add(T + D);
        time.sort(Comparator.naturalOrder());

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 0; i < N; i++) {
            int nextIdx = findNext(time.get(i) + D, time);

            for (int k = 0; k < K; k++) {
                dp[nextIdx][k + 1] = Math.max(dp[nextIdx][k + 1], dp[i][k] + nextIdx - i);
            }

            for (int k = 0; k <= K; k++) {
                dp[i + 1][k] = Math.max(dp[i + 1][k], dp[i][k]);
            }
        }

        return dp[N][K];
    }

    static int findNext(int n, List<Integer> time) {
        int s = 0;
        int e = time.size() - 1;

        while (s + 1 < e) {
            int m = (s + e) >> 1;

            if (time.get(m) < n) {
                s = m;
            } else {
                e = m;
            }
        }

        return e;
    }
}