/*
2개로 만들어
2개로 나올 수 있는 경우의 수
1_000_000
목적지, 나머지 하나 => 100만 * 해시
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int MAX = 50;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Long> arr = new ArrayList<>();

        for (int i = 0; i < N; i++) arr.add(Long.parseLong(bf.readLine()));

        System.out.println(solution(N, arr));
    }

    public static long solution(int N, List<Long> arr) {
        arr.sort(Comparator.naturalOrder());

        HashSet<Long> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                set.add(arr.get(i) + arr.get(j));
            }
        }

        for (int dest = N - 1; dest >= 0; dest--) {
            for (long n : arr) {
                if (set.contains(arr.get(dest) - n)) return arr.get(dest);
            }
        }

        return -1;
    }
}