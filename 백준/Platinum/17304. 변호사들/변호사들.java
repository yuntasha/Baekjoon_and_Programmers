/*
서로 마주보는 애들이 있으면 그거 중 1개는 제거해줘야함

조건
1. 모든 변호사는 변호를 1회 이상 진행
2. 모든 변호사는 변호를 1회 이상 받음
3. 2명의 변호사 서로 변호는 불가능

10만개의 강한 연결이 가능함
2차원 배열을 만들어서 처리

1번은 아니네?

이렇게 탐색이 아닌가?
그럼...
양쪽 전부 있는 경우
일단 2차원 리스트로 만들어줌

변호가 된 애들은 제거해도 될듯?
변호가 된 애들은 제거해주자
그리고 변호가 안된 애들로 다 넘겨주고 걔네도 제거

그리고 남은 애들은?
현재 변호가 양방향 변호 상황에 그거 없으면 끝나는 애들
내부적으로 싸이클이 존재만 하면 해결됨
1 2
2 3
3 1
2 4
4 5
이런느낌

그래서 전부 방문해보고 싸이클이 탐색 안된게 있다면 종료 false

일단 그냥 되는 애들 제거
양방향만 존재하는 그래프 생성
그리고 그냥 되는 애들 기준으로 완탐해서 되는 애들로 교체
그리고 남은 애들 연결할때 사이클이 존재하면 제거
39, 40은 각각 visited 만들어서 처리할 것
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    static long NUM = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        Set<Long> connects = new TreeSet<>();

        int[] give = new int[N];

        List<List<Integer>> lines = new ArrayList<>();

        for (int i = 0; i < N; i++) lines.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken()) - 1;
            int b = Integer.parseInt(input.nextToken()) - 1;

            connects.add(hash(a, b));
            if (connects.contains(hash(b, a))) {
                lines.get(a).add(b);
                lines.get(b).add(a);
                give[a]--;
                give[b]--;
            };

            give[b]++;
        }

        System.out.println(solution(N, M, give, lines) ? "YES" : "NO");
    }

    public static long hash(int a, int b) {
        return a * NUM + b;
    }

    public static boolean solution(int N, int M, int[] give, List<List<Integer>> lines) {
        makeGood(N, give, lines);
        return check(N, give, lines);
    }

    public static boolean check(int N, int[] give, List<List<Integer>> lines) {
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            if (give[i] > 0) continue;
            if (!findCycle(i, -1, visited, give, lines)) {
                return false;
            }
        }

        return true;
    }

    public static boolean findCycle(int n, int prev, boolean[] visited, int[] give, List<List<Integer>> lines) {
        if (visited[n]) return true;

        visited[n] = true;

        boolean result = false;

        for (int next : lines.get(n)) {
            if (prev == next) continue;
            result |= findCycle(next, n, visited, give, lines);
        }

        return result;
    }

    public static void makeGood(int N, int[] give, List<List<Integer>> lines) {
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            if (give[i] == 0) continue;

            for (int next : lines.get(i)) {
                dfsGood(next, give, visited, lines);
            }
        }
    }

    public static void dfsGood(int n, int[] give, boolean[] visited, List<List<Integer>> lines) {
        if (give[n] > 0) return;
        if (visited[n]) return;

        visited[n] = true;
        give[n] = 1;

        for (int next : lines.get(n)) {
            dfsGood(next, give, visited, lines);
        }
    }
}