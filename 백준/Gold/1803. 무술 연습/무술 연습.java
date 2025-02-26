import java.io.*;
import java.util.*;

/*
일단 탐색을 하는게 나을 것 같은 느낌
-1로 대통합을 시키고
어차피 얘는 0아니면 1임
그러니까 0일때 구해보고 성공하면 그 값을 가져오고
실패하면 1일때 구해보자
하나도 없는애들 그냥 전부 활쟁이로 만들어버림
그리고 그거 맞은애들 방패로 만들고 그리고 원래 쏘던애 맞는 활 -1
그리고 0

근데 싸이클이 있는 경우
싸이클이 있다면 무조건 그것은 1일듯 홀수라면 존재하지 않을 것이고 짝수라면 아무거나 쥐어주면 된다.
계속 값이 변하므로 매번 그냥 정렬시켜주자
그러면 n^2logn인데 늦는데?
역방향 정렬로 스택처럼 활용

 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[] arr = new int[N + M + 1];

        input = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(input.nextToken()) + N;
        }

        input = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= M; i++) {
            arr[i + N] = Integer.parseInt(input.nextToken());
        }

        System.out.println(solution(N, M, arr));
    }

    static String solution(int N, int M, int[] arr) {
        int[] count = new int[N + M + 1];
        boolean[] isShot = new boolean[N + M + 1];
        boolean[] visited = new boolean[N + M + 1];
        int[] result = new int[N + M + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N + M; i++) {
            count[arr[i]]++;
        }

        for (int i = 1; i <= N + M; i++) {
            if (count[i] == 0) {
                q.add(i);
                visited[i] = true;
            }
        }

        while (!q.isEmpty()) {
            int now = q.remove();

            if (isShot[now]) {
                result[now] = 0;
            } else {
                result[now] = 1;
                isShot[arr[now]] = true;
            }

            count[arr[now]]--;

            if (count[arr[now]] == 0) {
                q.add(arr[now]);
                visited[arr[now]] = true;
            }
        }


        while (true) {
            int now = -1;

            for (int i = 1; i <= N + M; i++) {
                if (!visited[i] && isShot[i]) {
                    now = i;
                    break;
                }
            }

            if (now < 0) {
                for (int i = 1; i <= N + M; i++) {
                    if (!visited[i]) {
                        now = i;
                        break;
                    }
                }
                if (now < 0) break;
            }

            q.add(now);
            visited[now] = true;

            while (!q.isEmpty()) {
                now = q.remove();

                if (isShot[now]) {
                    result[now] = 0;
                } else {
                    result[now] = 1;
                    isShot[arr[now]] = true;
                }

                count[arr[now]]--;

                if (count[arr[now]] == 0) {
                    q.add(arr[now]);
                    visited[arr[now]] = true;
                }
            }
        }

        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= N; i++)
            output.append(result[i]);
        output.append("\n");
        for (int i = N + 1; i <= N + M; i++)
            output.append(result[i]);

        return output.toString();
    }
}

/*
6 6
1 2 3 4 5 6
2 1 4 3 6 5
 */