import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
일단 진법을 먼저 쳐내야할듯
가능한 진법 여러개를 앞에서부터 쭉 받아서 처리해보자
진법은 0으로 시작을 안하면 된다.

1번 인덱스부터 그냥 잘라서 Integer로 받아버리면 될 것 같음
    이때 시작이 0이면 넘김

진법으로 나누는 부분(백트래킹)
    가능한 모든 위치까지 잘라서 처리해버림 자를 수 있으면 자른다
    시작점이 끝이라면 1개 찾음 처리
    끝 인덱스에 닿거나

이거 시초뜰 느낌인데
너무 많아;;
예를 들어 11111111111이렇게 도배해버리면 가지가 안쳐지는 경우가 너무 많음

아 저거 진법 받는것까지 그대로 처리
35 * 35 * 35 이정도 나오려나
그렇게 가보자

35까지 안되네?
ㅋㅋ...
10000000000000000 0
10000000000000000

아 처음 시작점은 0이 안되지만 앞에 숫자가 이미 있다면 뒤의 0은 의미가 있는 숫자구나...
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();

        System.out.println(solution(s));
    }

    static long solution(String s) {
        long result = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') continue;
            result += find(i, s.substring(i), s);
        }

        return result;
    }

    static long find(int last, String format, String s) {
        if (s.charAt(0) == '0') return last == 1 ? 1 : 0;

        long[] dp = new long[last + 1];

        dp[0] = 1;

        for (int i = 0; i < last; i++) {
            for (int j = i + 1; j <= last; j++) {
                if (s.charAt(i) == '0' && j > i + 1) break;
                if (compare(s.substring(i, j), format) < 0 ) {
                    dp[j] += dp[i];
                }
            }
        }

//        System.out.println("format = " + format);
//        System.out.println("dp = " + Arrays.toString(dp));

        return dp[last];
    }

    static int compare(String s1, String s2) {
        int c = Integer.compare(s1.length(), s2.length());
        if (c == 0) return s1.compareTo(s2);
        return c;
    }
}