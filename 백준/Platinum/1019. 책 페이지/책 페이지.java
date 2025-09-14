/*
앞에 수부터 진행하자
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    static int MAX = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(Arrays.stream(solution(N)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static int[] solution(int N) {
        int[] result = new int[10];

        int m = getFirst(N);

        int pre = 0;
        int suf = N;

        while (m >= 10) {
            int now = suf / m;
            suf %= m;
            for (int i = 0; i < 10; i++) {
                result[i] += m * pre;
            }
            for (int i = 0; i < now; i++) {
                result[i] += m;
            }

            result[now] += suf + 1;
            result[0] -= m;

            m /= 10;
            pre *= 10;
            pre += now;
//            System.out.println(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }


        for (int i = 0; i < 10; i++) {
            result[i] += pre;
        }
        
        for (int i = 1; i <= suf; i++) {
            result[i]++;
        }

        return result;
    }

    public static int getFirst(int N) {
        int result = 1;

        while (N / result >= 10) result *= 10;

        return result;
    }
}