/*
공배수에서 공약수 빼고 약수 구하기
같은 숫자의 약수는 전부 곱해준 상태로
아 완탐..
그냥 가능한 약수 전체 찾아
그냥 공배수 / 공약수 하고 그리고 약수로 sqrt부터 줄여나가보자
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int a = Integer.parseInt(input.nextToken());
        int b = Integer.parseInt(input.nextToken());

        System.out.println(solution(a, b));
    }

    static String solution(int a, int b) {
        int n = b / a;

        int sn = (int) Math.sqrt(n);

        for (int i = sn; i > 0; i--) {
            if (n % i == 0 && gcd(n/ i, i) == 1) {
                return i * a + " " + n / i * a;
            }
        }

        return "";
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}