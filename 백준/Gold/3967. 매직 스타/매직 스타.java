/*
각 배열의 위치 넣어주고
알파벳 넣은건 
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int EMPTY = -1;
    static char C_EMPTY = 'x';
    static int[][] lines = {
            {0, 2, 5, 7},
            {7, 8, 9, 10},
            {0, 3, 6, 10},
            {1, 2, 3, 4},
            {1, 5, 8, 11},
            {4, 6, 9, 11}
    };

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[12];
        Arrays.fill(arr, EMPTY);

        String s = bf.readLine();
        if (s.charAt(4) != C_EMPTY) arr[0] = convert(s.charAt(4));

        s = bf.readLine();
        if (s.charAt(1) != C_EMPTY) arr[1] = convert(s.charAt(1));
        if (s.charAt(3) != C_EMPTY) arr[2] = convert(s.charAt(3));
        if (s.charAt(5) != C_EMPTY) arr[3] = convert(s.charAt(5));
        if (s.charAt(7) != C_EMPTY) arr[4] = convert(s.charAt(7));

        s = bf.readLine();
        if (s.charAt(2) != C_EMPTY) arr[5] = convert(s.charAt(2));
        if (s.charAt(6) != C_EMPTY) arr[6] = convert(s.charAt(6));

        s = bf.readLine();
        if (s.charAt(1) != C_EMPTY) arr[7] = convert(s.charAt(1));
        if (s.charAt(3) != C_EMPTY) arr[8] = convert(s.charAt(3));
        if (s.charAt(5) != C_EMPTY) arr[9] = convert(s.charAt(5));
        if (s.charAt(7) != C_EMPTY) arr[10] = convert(s.charAt(7));

        s = bf.readLine();
        if (s.charAt(4) != C_EMPTY) arr[11] = convert(s.charAt(4));

        System.out.println(solution(arr));
    }

    public static String solution(int[] arr) {

        int bits = 0;

        for (int i : arr) {
            if (i != EMPTY) bits |= (1 << i);
        }

        find(0, bits, arr);

        return convert(arr);
    }

    public static boolean find(int n, int bits, int[] arr) {
        if (n == 12) {
            return isOk(arr);
        }

        if (arr[n] != EMPTY) {
            return find(n + 1, bits, arr);
        }

        for (int i = 1; i <= 12; i++) {
            if ((bits & (1 << i)) > 0) continue;
            arr[n] = i;
            if (find(n + 1, bits | (1 << i), arr)) return true;
            arr[n] = EMPTY;
        }

        return false;
    }

    public static boolean isOk(int[] arr) {
        int n = getSum(0, arr);

        for (int i = 1; i < 6; i++) {
            if (n != getSum(i, arr)) return false;
        }

        return true;
    }

    public static int getSum(int l, int[] arr) {
        int result = 0;

        for (int i : lines[l]) {
            result += arr[i];
        }

        return result;
    }

    public static String convert(int[] arr) {
        return  "...." + convert(arr[0]) + "....\n" +
                "." + convert(arr[1]) + "." + convert(arr[2]) + "." + convert(arr[3]) + "." + convert(arr[4]) + ".\n" +
                ".." + convert(arr[5]) + "..." + convert(arr[6]) + "..\n" +
                "." + convert(arr[7]) + "." + convert(arr[8]) + "." + convert(arr[9]) + "." + convert(arr[10]) + ".\n" +
                "...." + convert(arr[11]) + "....";
    }

    public static char convert(int i) {
        return (char) ('A' + i - 1);
    }

    public static int convert(char c) {
        return c - 'A' + 1;
    }
}