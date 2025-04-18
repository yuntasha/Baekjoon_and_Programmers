/*
그냥 누적합을 Map에 넣어
그 다음에 현재 누적합이 a이고 현재 값이 b라고 한다면
Map에서 a - b를 찾아서 처리해주자
그럼 일단 0은 1로 해둬서 싹 다 넣는 경우 자연스럽게 추가되도록
 */

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, K, arr));
    }

    static long solution(int N, int K, int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);
        long result = 0;
        int now = 0;

        for (int i = 0; i < N; i++) {
            now += arr[i];
            if (map.containsKey(now - K)) {
                result += map.get(now - K);
            }
            map.put(now, map.getOrDefault(now, 0) + 1);
        }

        return result;
    }
}