/*
bit로...
그냥 뒤에서부터 앞에서부터 하나씩 해보자
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = bf.readLine().replaceAll(" ", "").chars().map(Main::convert).toArray();

        System.out.println(solution(N, arr));
    }

    public static int convert(int c) {
        return c == '<' ? -1 : 1;
    }

    public static String solution(int N, int[] arr) {
        return biggest(N, arr) + "\n" + smallest(N, arr);
    }

    public static String smallest(int N, int[] arr) {
        int[] result = new int[N + 1];

        for (int i = 0; i < 10; i++) {
            result[0] = i;
            if (findSmall(1 << i, 1, N, arr, result)) break;
        }

        return convertResult(result);
    }

    public static String biggest(int N, int[] arr) {
        int[] result = new int[N + 1];

        for (int i = 9; i >= 0; i--) {
            result[0] = i;
            if (findBig(1 << i, 1, N, arr, result)) break;
        }

        return convertResult(result);
    }

    public static String convertResult(int[] result) {
        return Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(""));
    }

    public static boolean findSmall(int bits, int idx, int N, int[] arr, int[] result) {
        if (idx > N) return true;

        for (int i = 0; i < 10; i++) {
            if ((bits & (1 << i)) > 0) continue;
            if (arr[idx - 1] != Integer.compare(result[idx - 1], i)) continue;
            result[idx] = i;
            if (findSmall(bits | (1 << i), idx + 1, N, arr, result)) return true;
        }

        return false;
    }

    public static boolean findBig(int bits, int idx, int N, int[] arr, int[] result) {
        if (idx > N) return true;

        for (int i = 9; i >= 0; i--) {
            if ((bits & (1 << i)) > 0) continue;
            if (arr[idx - 1] != Integer.compare(result[idx - 1], i)) continue;
            result[idx] = i;
            if (findBig(bits | (1 << i), idx + 1, N, arr, result)) return true;
        }

        return false;
    }
}