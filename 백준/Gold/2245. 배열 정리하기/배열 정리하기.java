/*
오?
위치를 저장하면 될 것 같은데
그래서 아래 위 위 아래 아래 위
1 2 -> 2 3 -> 3 4이런 느낌으로 계속 찾으면서 해보면 될 것 같은

-1이 나오는 경우는 각 숫자가 2개씩 있어야한다.


그럼 모든 위치를 순회하면서
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] A = new int[N];
        int[] B = new int[N];
        List<List<Integer>> idx = new ArrayList<>();

        idx.add(null);

        StringTokenizer input = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++) {
            idx.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(input.nextToken());
            idx.get(A[i]).add(i << 1);
        }

        input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(input.nextToken());
            idx.get(B[i]).add((i << 1) | 1);
        }

        System.out.print(solution(N, A, B, idx));
    }

    public static int solution(int N, int[] A, int[] B, List<List<Integer>> idx) {
        for (int i = 1; i <= N; i++) {
            if (idx.get(i).size() != 2) return -1;
        }

        int result = 0;
        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            result += find(i, A, B, idx, visited);
        }

        return result;
    }

    public static int find(int start, int[] A, int[] B, List<List<Integer>> idx, boolean[] visited) {
        if (A[start] == B[start]) return 0;

        int cnt = 0;
        int rev = 0;

        int now = start;
        int value = B[start];

        do {
            if ((idx.get(value).get(0) >> 1) == now) {
                now = idx.get(value).get(1);
            } else {
                now = idx.get(value).get(0);
            }

            if ((now & 1) == 1) {
                value = A[now >> 1];
                rev++;
            } else {
                value = B[now >> 1];
            }

            cnt++;

            now = now >> 1;
            visited[now] = true;
        } while (start != now);

        return Math.min(rev, cnt - rev);
    }
}
/*
3 2 7 4  6 5  3 9 1 1
6 8 4 10 8 10 7 5 2 9
 */