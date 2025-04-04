import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
그러니까 마을에 우수 마을 선정된 마을 사람의 총 합이 최대가 되도록 서렂ㅇ
근데 근처 마을이 우수 마을이면 나는 우수마을이면 안되고
근처 마을이 우수 마을이 아니면 쟤는 우수마을이여야한다.

최대 총합 1억임 1만 * 1만

dfs로 조회하되 dp로 저장해서 효율 챙기자

근데 하나라도 들어가는 가정하에 가장 최대인 경우의 수는?
일단 최대를 구해 근데 전부 사용안한다로 나오면 가장 차이가 적은 애를 바꿔버리기 굿

이렇게 만들면 최대 2만 * 1만 근데 이건 말이 안되니까 굿

현재가 1일때 사용가능한 경우 -> 다음 다음이 0일때 나오는 최댓값 = 다음이 1인데 걔 포함하지 않고 최댓값, 다음이 0일때 나오는 최댓값
현재가 0일때 사용가능한 경우 -> 다음 중 하나라도 1이 나오는 경우

현재가 1일 경우 -> max(다음이 0일 경우, 다음이 1일 경우 - 다음 인원수)의 합
현재가 0일 경우 -> 다음 중 하나라도 1이 나오는 경우 => 했음
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] people = new int[N + 1];

        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(input.nextToken());
        }

        List<List<Integer>> links = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            links.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            links.get(a).add(b);
            links.get(b).add(a);
        }

        System.out.print(solution(N, people, links));
    }

    static int solution(int N, int[] people, List<List<Integer>> links) {
        int[][] dp = new int[N + 1][2];
        int result = Math.max(dfs(N, people, links, dp, 1, 0, 0), dfs(N, people, links, dp, 1, 1, 0));

//        for (int i = 1; i <= N; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        return result;
    }

    static int dfs(int N, int[] people, List<List<Integer>> links, int[][] dp, int now, int use, int before) {
        if (dp[now][use] > 0) return dp[now][use];

        if (use == 0) {
            int min = Integer.MAX_VALUE;
            boolean isOk = false;

            for (int next : links.get(now)) {
                if (next == before) continue;
                int a = dfs(N, people, links, dp, next, 0, now);
                int b = dfs(N, people, links, dp, next, 1, now);

                if (a > b) {
                    dp[now][use] += a;
                    min = Math.min(min, a - b);
                } else {
                    dp[now][use] += b;
                    isOk = true;
                }
            }

            if (!isOk && links.get(now).size() > 1) {
                dp[now][use] -= min;
            }
        } else {
            for (int next : links.get(now)) {
                if (next == before) continue;
                dp[now][use] += Math.max(dfs(N, people, links, dp, next, 0, now), dfs(N, people, links, dp, next, 1, now) - people[next]);
            }
            dp[now][use] += people[now];
        }

        return dp[now][use];
    }
}

/*
7
100 1 1 100 1 1 100
1 2
2 3
3 4
3 5
5 6
5 7

301

4
18 17 15 4
2 1
3 1
4 2

    18
17      15
4
 */