/*
K의 개수가 S의 2배
K가 1개 이상일 경우
가장 긴 문자열의 길이
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static char S = 'S';
    static char K = 'K';

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String input = bf.readLine();

        System.out.println(solution(input.toCharArray()));
    }

    static int solution(char[] input) {
        int[] sum = new int[input.length];
        int[] count = new int[input.length];

        int start = -1;

        if (input[0] == S) {
            start = 0;
            count[0]++;
            sum[0] = -2;
        }

        if (input[0] == K) {
            start = 0;
            count[0]++;
            sum[0] = 1;
        }

        for (int i = 1; i < input.length; i++) {
            sum[i] = sum[i - 1];
            count[i] = count[i - 1];
            if (input[i] == S) {
                if (start == -1) start = i;
                count[i]++;
                sum[i] -= 2;
            } else if (input[i] == K) {
                if (start == -1) start = i;
                count[i]++;
                sum[i]++;
            }
        }

        if (start == -1) return -1;

        HashMap<Integer, Integer> min = new HashMap<>();
        min.put(0, -1);
        int result = -1;

//        System.out.println("Arrays.toString(count) = " + Arrays.toString(sum));
//        System.out.println("min = " + min);

        for (int i = start; i < input.length; i++) {
            if (min.containsKey(sum[i])) {
//                System.out.println(i);
                if (count[i] == 0 || (min.get(sum[i]) != -1 && count[i] == count[min.get(sum[i])])) continue;
                result = Math.max(result, i - min.get(sum[i]));
//                if (i - min.get(count[i]) + 1 == 11) {
//                    System.out.println("count[i] = " + count[i]);
//                    System.out.println("min.get(count[i]) = " + min.get(count[i]));
//                    System.out.println("i = " + i);
//                }
            } else {
                min.put(sum[i], i);
            }
        }

        return result;
    }
}