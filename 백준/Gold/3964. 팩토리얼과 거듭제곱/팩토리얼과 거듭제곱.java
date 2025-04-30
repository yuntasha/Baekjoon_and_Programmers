/*
1 2 3 4
4 2
2는 2개
4는 1개
3개

b가 소수가 아닌 경우
10일때 2 * 5가 있음
2는 8개 5는 2개
그럼그럼
12의 경우
2 6, 3 4, 1 12를 전부 찾아야해?

2의 약수면 2의 개수
3의 약수면 3의 개수
4의 약수면 4의 개수
1_000_000_000 <= 이미 10억임
근데 K의 약수를 구하면 되니까
1_000_000 <= 100만임
100만 싹 돌면서 약수의 개수를 구하는거임
소수인 애들을 싹 찾아서 소수로 이루어진 약수의 개수를 구해 => 귀찮으니 Map으로 만들자
소수 => 개수
이렇게
그리고 그 소수가 들어가는 개수를 찾아서 처리 -> 이건 이미 만듦
그리고 가장 작은 수로 처리하자

소수판정 해야하나? 모르겠다 그냥 해보자
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        StringBuilder output = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            long a = Long.parseLong(input.nextToken());
            long b = Long.parseLong(input.nextToken());

            output.append(solution(a, b)).append("\n");
        }

        System.out.println(output);
    }

    static long solution(long n, long k) {
        HashMap<Long, Long> hm = new HashMap<>();

        long now = k;

        for (long i = 2; i <= Math.sqrt(k) && now > 1; i++) {
            while (now % i == 0) {
                hm.put(i, hm.getOrDefault(i, 0L) + 1);
                now /= i;
            }
        }

        if (now > 1) hm.put(now, 1L);

        long result = Long.MAX_VALUE;

        for (long key : hm.keySet()) {
            result = Math.min(result, count(n, key) / hm.get(key));
        }

        return result;
    }

    static long count(long n, long k) {
        long result = 0;

        long now = n;

        while (k <= now) {
            result += now / k;
            now /= k;
        }

        return result;
    }
}