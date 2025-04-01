/*
양은 한마리당 a그램
염소는 한마리당 b그램
전체 n마리
소비한 사료 w그램
*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int a = Integer.parseInt(input.nextToken());
        int b = Integer.parseInt(input.nextToken());
        int n = Integer.parseInt(input.nextToken());
        int w = Integer.parseInt(input.nextToken());

        System.out.println(Arrays.stream(solution(a, b, n, w)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static int[] solution(int a, int b, int n, int w) {
        int resultA = 0;
        int resultB = 0;
        int count = 0;

        for (int i = 1; i < n; i++) {
            if (i * a + (n - i) * b == w) {
                count++;
                resultA = i;
                resultB = n - i;
            }
        }

        if (count != 1) return makeInt(-1);
        return makeInt(resultA, resultB);
    }

    public static int[] makeInt(int... args) {
        return args;
    }
}