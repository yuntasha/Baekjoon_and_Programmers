import java.io.*;
import java.util.*;

/*
간단한 투 포인터 문제?
오븐 부분 들어갈 수 있는 최대 숫자로 변경
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[] oven = new int[N];
        int[] pizza = new int[M];

        input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            oven[i] = Integer.parseInt(input.nextToken());
        }

        input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < M; i++) {
            pizza[i] = Integer.parseInt(input.nextToken());
        }

        System.out.println(solution(N, M, oven, pizza));
    }

    static int solution(int N, int M, int[] oven, int[] pizza) {
        for (int i = 1; i < N; i++) {
            oven[i] = Math.min(oven[i], oven[i-1]);
        }

        int pIdx = 0;
        int depth = N - 1;

        while (pIdx < M && depth >= 0) {
            if (oven[depth] >= pizza[pIdx]) {
                depth--;
                pIdx++;
            } else {
                depth--;
            }
        }

        return depth + 1 + (pIdx == M ? 1 : 0);
    }
}