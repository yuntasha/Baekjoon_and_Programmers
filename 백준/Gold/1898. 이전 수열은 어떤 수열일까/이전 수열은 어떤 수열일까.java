/*
그냥 더 작은거 안썼으면 그걸로 교체
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        int N = read();

        int[] arr = new int[N];
        int[] idx = new int[N + 1];

        for (int i = 0; i < N; i++) {
            arr[i] = read();
            idx[arr[i]] = i;
        }

        System.out.println(Arrays.stream(solution(N, arr, idx)).mapToObj(String::valueOf).collect(Collectors.joining("\n")));
    }

    public static int[] solution(int N, int[] arr, int[] idx) {
        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            if (result[i] > 0) continue;
            if (arr[i] != 1 && idx[arr[i] - 1] > i) {
                result[i] = arr[i] - 1;
                result[idx[arr[i] - 1]] = arr[i];
                continue;
            }
            result[i] = arr[i];
        }

        return result;
    }

    static int read() throws IOException {
        int n = 0;
        int c;
        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}