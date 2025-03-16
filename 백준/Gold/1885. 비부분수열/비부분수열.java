/*
이거 가장 앞에 있는 숫자대로 빠르게 찾아나가면 될 느낌인데
1-N까지 그룹으로 그냥 지어두고하면 될 ㄷ,ㅅ?
각 그룹에서 가장 마지막에 나온 애들만 컷하고 하나 더 넣으면 되니까...?
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        int N = read();

        int K = read();

        int result = 1;
        int[] count = new int[K + 1];

        int now = 0;

        for (int i = 0; i < N; i++) {
            int n = read();
            if (result == count[n]) continue;
            count[n]++;
            now++;
            if (now == K) {
                now = 0;
                result++;
            }
        }

        System.out.println(result);
    }

    public static int read() throws IOException {
        int n = 0;
        int c;
        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}