import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
일단 선에 맞춰서 int[N+1][2]에 채워 넣자
이렇게 넣는데 만약 각각 2개가 아니라 +가 된다면 실패
성공하면 BFS느낌으로 순서 탐색
그리고 반대로도 하나 더 리스트 만들어줌
그리고 각각 LCS하고 최댓값 구하기
N - 최댓값하면 끝
 */

public class Main {

    static final int FAIL = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] next = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            next[i][0] = a;
            next[i][1] = b;
        }

        System.out.println(solution(N, next));
    }

    static int solution(int N, int[][] next) {
        int result = 0;

        for (int i = 1; i <= N; i++) {
            List<Integer> l1 = findList(i, next);
            
            if (l1.size() != N) return FAIL;
            
            List<Integer> l2 = findRev(N, l1);

            result = Math.max(result, findLcs(N, l1));
            result = Math.max(result, findLcs(N, l2));
        }

        return N - result;
    }

    static List<Integer> findList(int s, int[][] next) {
        List<Integer> l = new ArrayList<>();

        l.add(s);
        int prev = s;
        int now = next[s][0];

        while (now != s) {
            l.add(now);
            for (int i : next[now]) {
                if (prev == i) continue;
                prev = now;
                now = i;
                break;
            }
        }

        return l;
    }

    static List<Integer> findRev(int N, List<Integer> l) {
        List<Integer> l2 = new ArrayList<>();

        l2.add(l.get(0));
        for (int i = 1; i < N; i++) {
            l2.add(l.get(N - i));
        }

        return l2;
    }

    static int findLcs(int N, List<Integer> l) {
        List<Integer> dp = new ArrayList<>();

        for (int n : l) {
            if (dp.isEmpty() || dp.get(dp.size() - 1) < n) {
                dp.add(n);
                continue;
            }

            dp.set(find(n, dp), n);
        }

        return dp.size();
    }

    static int find(int n, List<Integer> dp) {
        int s = 0;
        int e = dp.size() - 1;

        if (dp.get(0) > n) {
            return 0;
        }

        while (s + 1 < e) {
            int m = (s + e) >> 1;

            if (dp.get(m) < n) {
                s = m;
            } else {
                e = m;
            }
        }

        return e;
    }
}