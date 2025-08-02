/*
1, 2, 3으로 해당 값을 넣는 것
흠
dp로 해당 값을 만들 수 있는 가짓수를 미리 구하고 백트래킹으로 구해도 괜찮을 느낌인데?
1 + 1 + 2 이런것도 봐주잖아
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int plus = Integer.parseInt(input.nextToken());
        int minus = Integer.parseInt(input.nextToken());
        int multi = Integer.parseInt(input.nextToken());
        int divide = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, arr, plus, minus, multi, divide));
    }

    public static String solution(int N, int[] arr, int plus, int minus, int multi, int divide) {
        int[] result = {Integer.MAX_VALUE, Integer.MIN_VALUE};

        find(arr[0], 1, result, N, arr, plus, minus, multi, divide);

        return result[1] + "\n" + result[0];
    }

    public static void find(int now, int n, int[] result, int N, int[] arr, int plus, int minus, int multi, int divide) {
        if (N == n) {
            result[0] = Math.min(result[0], now);
            result[1] = Math.max(result[1], now);
        }

        if (plus > 0) {
            find(now + arr[n], n + 1, result, N, arr, plus - 1, minus, multi, divide);
        }

        if (minus > 0) {
            find(now - arr[n], n + 1, result, N, arr, plus, minus - 1, multi, divide);
        }

        if (multi > 0) {
            find(now * arr[n], n + 1, result, N, arr, plus, minus, multi - 1, divide);
        }

        if (divide > 0) {
            find(now / arr[n], n + 1, result, N, arr, plus, minus, multi, divide - 1);
        }
    }
}