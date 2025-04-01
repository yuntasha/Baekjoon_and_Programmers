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

        int[] size = new int[9];

        for (int i = 0; i < 9; i++) {
            size[i] = Integer.parseInt(bf.readLine());
        }

        System.out.println(Arrays.stream(solution(size)).mapToObj(String::valueOf).collect(Collectors.joining("\n")));
    }

    public static int[] solution(int[] size) {
        Arrays.sort(size);

        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                int sum = 0;
                for (int n = 0; n < 9; n++) {
                    if (n == i || n == j) continue;
                    sum += size[n];
                }

                if (sum == 100) {
                    int[] result = new int[7];

                    int idx = 0;

                    for (int n = 0; n < 9; n++) {
                        if (n == i || n == j) continue;
                        result[idx++] = size[n];
                    }

                    return result;
                }
            }
        }

        return null;
    }
}