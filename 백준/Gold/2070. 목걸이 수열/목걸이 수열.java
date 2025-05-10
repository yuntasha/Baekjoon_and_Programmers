/*
길이가 100이면 백트래킹으로 어떻게 해볼법도 한디?
감소하는 순서로 나온다
두 개의 목걸이 수열을 합치면 목걸이 수열이 아니다

A < B
A에 몇글자 더 붙이면 B가 되는 경우
A와 B가 숫자가 같은데 그 이후 B가 숫자가 더 큰 경우

가능한 최대한 붙여야한다는 소리
1만 나오면 일단 1은 최대한 붙임
01이면 001이 안나오도록 만들어야함
01101이면 010111이 더 작기 때문에 아님
011011이면 두 개의 목걸이 수열을 합치면 목걸이 수열이 나옴

뒤에 0이 들어오면 목걸이 그거 아 그냥 돌려야하나?

앞이 최대한 커야한다. 즉
0101110111101111
01 / 0111 / 01111 / 01111
0100001이면 우짬

0000101이 더 크니까 전체도 안되고
111111
01101

무조건 된다고 가정하면
1만 계속 나오면 그거 하면 될거고
01001이면
01 / 001로 나눠줘야한다

0110111이면
그냥 0110111이다

그럼 0이 나올떄마다 판단하면 될듯
0이 나오면 앞에보다 크면 붙여
001 0001 00001

0001001
0001 / 001

일단 1이 끝나면 나누자
00111 / 001
근데 아무래도 왼쪽이 더 큼 그러면 그냥 나눠버림

음 이것들 규칙을 흠...

백트로 안되나

그냥 뒤에서부터 전부 박아보자
목걸이 수열이 맞는지 체크
맞으면 다음꺼 체크 그렇게 전부 된다면 해결처리

0011100011111일때
00111 / 00011111으로 나눔
두개를 비교할때 <가 된다면 앞이 더 작다면 합침
같다면? 합쳐줘야함
왜냐하면 뒤에 더 합쳐지면 더 커질 수 있음

감소하는 순서라고함
000111 > 0001
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();
        System.out.println(solution(s));
        // (111111)(000111001101110111111011)(0000111001000111101)(000010011100011, 0000101011010001000101001)
    }

    static String solution(String s) {
        List<Integer> splitIdx1 = new ArrayList<>();
        List<Integer> splitIdx2 = new ArrayList<>();

        splitIdx1.add(0);
        char prev = s.charAt(0);

        for (int i = 1; i < s.length(); i++) {
            if (prev == '1' && s.charAt(i) == '0') {
                splitIdx1.add(i);
            }
            prev = s.charAt(i);
        }

        splitIdx1.add(s.length());

        while (true) {
            int ps = splitIdx1.get(0);
            int pe = splitIdx1.get(1);

            splitIdx2.add(0);

            for (int i = 1; i < splitIdx1.size() - 1; i++) {
                if (isNeck(ps, splitIdx1.get(i + 1), s)) {
                    pe = splitIdx1.get(i + 1);
                    continue;
                }
                splitIdx2.add(pe);
                ps = splitIdx1.get(i);
                pe = splitIdx1.get(i + 1);
            }

            splitIdx2.add(pe);

            if (splitIdx1.size() == splitIdx2.size()) break;
            List<Integer> temp = splitIdx2;
            splitIdx2 = splitIdx1;
            splitIdx1 = temp;
            splitIdx2.clear();
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < splitIdx1.size() - 1; i++) {
            result.add(s.substring(splitIdx1.get(i), splitIdx2.get(i + 1)));
        }

        return result.stream().map(ss -> "(" + ss + ")").collect(Collectors.joining(""));
    }

    static boolean isNeck(int s, int e, String ss) {
        for (int n = 1; n < e - s; n++) {
            for (int i = 0; i < e - s; i++) {
                int c = Integer.compare(ss.charAt(s + i), ss.charAt(s + ((i + n) % (e - s))));
                if (c == 1) return false;
                if (c == -1) break;
            }
        }

        return true;
    }
}