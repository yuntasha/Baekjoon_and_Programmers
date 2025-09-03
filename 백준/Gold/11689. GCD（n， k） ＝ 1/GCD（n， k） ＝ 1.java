/*
1_000_000_000_000
n, k인데 나오는 모든
n의 약수를 구하면 될듯?
약수를 구하고
간단하게 6으로 생각해보자
1 2 3 4 5 6

1 2 3 6
x 3 2 x


크아아악
오일러의 피라니

 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(bf.readLine());

        System.out.println(solution(N));
    }

    public static long solution(long N) {
        if (N == 1) return 1;

        List<Long> mods = getMod(N);

        long result = 1;
        long now = mods.get(0);
        long count = 0;

        if (now == N) return N - 1;


        for (long i : mods) {
            if (now == i) {
                count++;
                continue;
            }

            result *= Math.pow(now, count) - Math.pow(now, count - 1);
            now = i;
            count = 1;
        }
        result *= Math.pow(now, count) - Math.pow(now, count - 1);

        return result;
    }

    public static List<Long> getMod(long N) {
        List<Long> result = new ArrayList<>();

        for (long i = 2; i <= Math.sqrt(N); i++) {
            while (N % i == 0) {
                result.add(i);
                N /= i;
            }
        }

        if (N > 1)  result.add(N);

        return result;
    }
}
