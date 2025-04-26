/*
백트래킹해 어차피 가능할거야
그러면 이제 해야할 일
앞에서부터 하나씩 가져오는데
문자열로 숫자 비교할 수 있게 만들어야함
그래서 크면 가져오고
1 + 2 + 3 + 4 + 5

n(n+1)/2 = 80
n(n+1) = 160
10 * 11
11 * 12 = 132
12 * 13 = 156
13 * 14

13
1_000_000_000
뭐야 long 그냥 가능하네!
100000000000...000이면 String해야한다 바보야
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String input = bf.readLine();

        System.out.println(solution(input));
    }

    static String solution(String s) {
        int result = find(-1, 0, s);
        if (result == s.length()) return "0";
        return s.substring(result);
    }

    static int find(int prevS, int prevE, String s) {
        if (prevE == s.length()) return prevS;

        int result = 0;

        for (int i = prevE + 1; i <= s.length(); i++) {
            int start = convert(prevE, i, s);
            if (prevS == -1 || compare(prevS, prevE, start, i, s) == -1) {
                result = Math.max(result, find(start, i, s));
            }
        }

        return result;
    }

    static int convert(int sIdx, int eIdx, String s) {
        for (int i = sIdx; i < eIdx; i++) {
            if (s.charAt(i) != '0') return i;
        }
        return eIdx;
    }

    static int compare(int s1, int e1, int s2, int e2, String s) {
        if (e1 - s1 < e2 - s2) return -1;
        if (e1 - s1 > e2 - s2) return 1;

        for (int i = 0; i < e1 - s1; i++) {
            char c1 = s.charAt(i + s1);
            char c2 = s.charAt(i + s2);

            if (c1 < c2) return -1;
            if (c1 > c2) return 1;
        }

        return 0;
    }
}