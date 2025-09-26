/*
G키로그램이 주어짐
(현재 몸무게) ^ 2 - (기억 몸무게) ^ 2

현재 몸무게, 기억 몸무게 투포인터로 완탐
최대 100,000의 G가 주어진댔으니
하나의 차이가 저거보단 큰 경우
 */


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    static int a = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(bf.readLine());

        System.out.println(solution(G));
    }

    public static String solution(int G) {
        List<Integer> result = new ArrayList<>();

        int now = 2;
        int prev = 1;

        while (prev < now) {
            int n = now * now - prev * prev;

            if (n == G) {
                result.add(now);
                now++;
                prev++;
            } else if (n < G) {
                now++;
            } else {
                prev++;
            }
        }

        if (result.isEmpty()) return "-1";

        return result.stream().map(String::valueOf).collect(Collectors.joining("\n"));
    }
}