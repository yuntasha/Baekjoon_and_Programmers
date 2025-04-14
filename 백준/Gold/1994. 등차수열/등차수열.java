/*
음음
N개의 배열이 주어질 때 가장 긴 등차수열 찾기
정렬하고 이후에 있는 애랑 비교해서 넣어버리면 될듯?
N * N을 만들거고
해당 인덱스 번호끼리 비교해서 나오는 등차 수열의 최댓값?
해시맵으로 해당 인덱스 위치를 저장
0인 등차수열은 좀 문제가 있으니 그건 거르자
그냥 가장 마지막에 있는 인덱스 번호로 저장
0이 증가하면 그냥 걸러
따로 찾자
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {
        int result = 0;

        Arrays.sort(arr);

        HashMap<Integer, Integer> index = new HashMap<>();

        for (int i = 0; i < N; i++) {
            index.put(arr[i], i);
        }

        int now = 0;
        int prev = arr[0] - 1;
        for (int i = 0; i < N; i++) {
            if (prev == arr[i]) {
                now++;
            } else {
                now = 1;
            }
            prev = arr[i];
            result = Math.max(result, now);
        }

        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (arr[i] == arr[j]) continue;
                int p = arr[j] - arr[i];
                dp[i][j] = 2;
                if (index.containsKey(arr[i] - p)) {
                    dp[i][j] = dp[index.get(arr[i] - p)][i] + 1;
                }
                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }
}