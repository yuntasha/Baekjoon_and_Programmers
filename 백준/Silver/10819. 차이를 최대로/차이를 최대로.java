/*
bit로...
그냥 뒤에서부터 앞에서부터 하나씩 해보자
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int FIRST = -1000;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    public static int solution(int N, int[] arr) {
        int result = 0;

        for (int i = 0; i < N; i++) {
            result = Math.max(result, find(arr[i], 1 << i, 0, N, arr));
        }

        return result;
    }

    public static int find(int prev, int bits, int sum, int N, int[] arr) {
        if (bits == (1 << N) - 1) return sum;

        int now = 0;

        for (int i = 0; i < N; i++) {
            if ((bits & (1 << i)) > 0) continue;
            int add = Math.abs(prev - arr[i]);
            now = Math.max(now, find(arr[i], bits | (1 << i), sum + add, N, arr));
        }

        return now;
    }
}