/*
양수면 큰 애들이면 그냥 곱하는게 이득임
근데 음수면 작은 애들끼리 곱하는게 이득임
홀수 개수라면 그냥 더해주는게 이득임
0은 우짬 그냥 음수에 넣어 양수에 있는거 더해주면 큰일남
1인 경우가 있구나...
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Long> plus = new ArrayList<>();
        List<Long> minus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            long n = Long.parseLong(bf.readLine());

            if (n > 0) plus.add(n);
            else minus.add(n);
        }

        System.out.println(solution(N, plus, minus));
    }

    static long solution(int N, List<Long> plus, List<Long> minus) {
        long result = 0;

        plus.sort(Comparator.reverseOrder());
        minus.sort(Comparator.naturalOrder());

        for (int i = 0; i < plus.size() - 1; i += 2) {
            result += plus.get(i) * plus.get(i + 1);
            if (plus.get(i) == 1 || plus.get(i + 1) == 1) result += 1;
        }

        if ((plus.size() & 1) == 1) {
            result += plus.get(plus.size() - 1);
        }

        for (int i = 0; i < minus.size() - 1; i += 2) {
            result += minus.get(i) * minus.get(i + 1);
        }

        if ((minus.size() & 1) == 1) {
            result += minus.get(minus.size() - 1);
        }

        return result;
    }
}