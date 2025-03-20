/*
그냥 애들 싹다 넣었다 뺐다
20만 * 20만
2개 안되는 애들 그냥 싹다 제거
그러면 그냥 2이상인데 그러면 그냥 2개라는 뜻

0 - 1인 애들 그냥 싹다 없애버려야함
그러면 걔네가 준 선물 반납하면
걔네한테 준 선물은 자동으로 버려짐
그러면 선물의 총 숫자는 2N개보다 줄어든다
그말은 1개를 전부 없애면 알아서 처리가 될 것
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] count = new int[N + 1];
        int[][] give = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            give[i][0] = a;
            give[i][1] = b;

            count[a]++;
            count[b]++;
        }

        System.out.println(solution(N, count, give));
    }

    public static String solution(int N, int[] count, int[][] give) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (count[i] < 2) q.add(i);
        }

        while (!q.isEmpty()) {
            int now = q.remove();

            for (int i = 0; i < 2; i++) {
                count[give[now][i]]--;
                if (count[give[now][i]] == 1) q.add(give[now][i]);
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (count[i] == 2) result.add(i);
        }

        return result.size() + "\n" + result.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }
}