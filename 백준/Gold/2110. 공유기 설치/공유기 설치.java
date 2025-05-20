import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
방법 1 : 그냥 이분 탐색으로 최댓값 만들고 하나 설치함
그냥 앞에서부터 설치

 */

public class Main {

    static int MAX = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int C = Integer.parseInt(input.nextToken());

        List<Integer> houses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            houses.add(Integer.parseInt(bf.readLine()));
        }

        System.out.println(solution(N, C, houses));
    }

    static int solution(int N, int C, List<Integer> houses) {
        int s = 1;
        int e = MAX;

        houses.sort(Comparator.naturalOrder());

        if (C <= getCount(e, houses)) return e;

        while (s + 1 < e) {
            int m = (s + e) >> 1;
            if (C <= getCount(m, houses)) {
                s = m;
            } else {
                e = m;
            }
        }

        return s;
    }

    static int getCount(int d, List<Integer> houses) {
        int prev = -d;
        int count = 0;

        for (int h : houses) {
            if (h >= prev + d) {
                count++;
                prev = h;
            }
        }

        return count;
    }
}