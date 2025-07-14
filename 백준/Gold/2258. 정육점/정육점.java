/*
아
음음
두가지 종류가 있다
하나는 하나만 사는 경우
하나는 2개이상 사는 경우

정렬 후 누적합을 하자

그럼 왼쪽으로 가면서 같은 경우 전부 더해주는 것
그리고 오른쪽으로 가면서 다른 경우 찾는 것

일단 더해주면서 왼쪽에서 오른쪽 출발

 */

import java.io.*;
import java.util.*;

public class Main {

    static int AMOUNT = 0;
    static int COST = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] meats = new int[N][2];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());

            meats[i][AMOUNT] = Integer.parseInt(input.nextToken());
            meats[i][COST] = Integer.parseInt(input.nextToken());
        }

        System.out.print(solution(N, M, meats));
    }

    public static int solution(int N, int M, int[][] meats) {
        Arrays.sort(meats, (a, b) -> a[COST] == b[COST] ? b[AMOUNT] - a[AMOUNT] : a[COST] - b[COST]);

        int result = Integer.MAX_VALUE;
        int sum = 0;
        int now = 0;
        int prev = -1;

        for (int i = 0; i < N; i++) {
            if (prev != meats[i][COST]) {
                prev = meats[i][COST];
                now = 0;
            }

            sum += meats[i][AMOUNT];
            now += meats[i][COST];
            if (sum >= M) result = Math.min(result, now);
        }

        return sum < M ? -1 : result;
    }
}