import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
1, 5, 10, 25
가장 동전을 많이 사용하는 경우로 구해라

불가능한 경우 0 0 0 0

가장 적게 하는 방법?
뒤에서부터 가능한거 죄다 넣어보기
큰거 하나씩 계속 제거해보면

1이 가능한 개수 - 전체 % 5 + 5의 배수
5가 가능한 개수 - 전체 % 10 / 5 + 10의 배수
10이 가능한 개수 - 전체 % 25 / 10 + 25의 배수
25는 나머지로...

그냥 앞에서부터 가능한 최대치로

2 7 12 17 ...
5 10 15 20 ...
10 20 30 40 ...
25 50 75 ...

그냥 뒤에서부터 0넣고 그냥 싹 다 돌려볼까

1, 5까지는 그냥 구할법하다...
10, 25가 구해지면 나머지는 구할법함
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int X = Integer.parseInt(input.nextToken());
        int A = Integer.parseInt(input.nextToken());
        int B = Integer.parseInt(input.nextToken());
        int C = Integer.parseInt(input.nextToken());
        int D = Integer.parseInt(input.nextToken());

        System.out.println(Arrays.stream(solution(X, A, B, C, D)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    static int[] solution(int X, int A, int B, int C, int D) {
        if (X % 5 > A) return new int[4];

        int aMax = X % 5 + (A - X % 5) / 5 * 5; // a가 가질 수 있는 최대 수

        int count = 0;

        int[] result = new int[4];

        int now = 0;

        for (int d = 0; d <= D; d++) {
            now += d * 25;
            if (now > X) break;
            for (int c = 0; c <= C; c++) {
                if (now + c * 10 > X) break;
                now += c * 10;
                int a = Math.min(X - now, aMax);
                int b = (X - now - a) / 5;
                if (b <= B && count < a + b + c + d) {
                    result[0] = a;
                    result[1] = b;
                    result[2] = c;
                    result[3] = d;
                    count =  a + b + c + d;
                }
                now -= c * 10;
            }
            now -= d * 25;
        }

        return result;
    }

    static int[] isBigger(int[] arr1, int[] arr2, int sum1, int sum2) {
        int a = 0;
        int b = 0;

        for (int i = 0; i < 4; i++) {
            a += arr1[i];
            b += arr2[i];
        }

        if (a > b) return arr1;
        return arr2;
    }
}