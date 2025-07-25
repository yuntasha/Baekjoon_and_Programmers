/*
depth가 8까지만 간다
1 - n
2 - 1, n+n, n*n

그냥 BFS돌려도 괜찮지 않을까?

1,2,3~~순서대로 찾자
어차피 8밖에 안되니까
Set으로 있는지 확인해서 반복문에서 빠지도록 하자

 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(bf.readLine());
        
        Map<Integer, Integer> result = solution(K);

        int N = Integer.parseInt(bf.readLine());

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(bf.readLine());
            output.append("\n").append(result.containsKey(n) ? result.get(n) : "NO");
        }

        System.out.println(output.substring(1));
    }

    public static Map<Integer, Integer> solution(int N) {
        HashMap<Integer, Integer> result = new HashMap<>();

        result.put(N, 1);

        ArrayDeque<Integer> q = new ArrayDeque<>();

        List<Set<Integer>> sets = new ArrayList<>();

        sets.add(null);
        for (int i = 0; i < 8; i++) {
            sets.add(new HashSet<>());
        }

        int now = 0;

        for (int i = 1; i <= 8; i++) {
            now *= 10;
            now += N;
            sets.get(i).add(now);
            result.put(now, i);
        }

        for (int i = 2; i <= 8; i++) {
            for (int n = 1; n <= (i >> 1); n++) {
                for (int a : sets.get(n)) {
                    for (int b : sets.get(i - n)) {
                        if (!result.containsKey(a + b)) {
                            result.put(a + b, i);
                            sets.get(i).add(a + b);
                        }

                        if (!result.containsKey(a - b) && a - b > 0) {
                            result.put(a - b, i);
                            sets.get(i).add(a - b);
                        }

                        if (!result.containsKey(b - a) && b - a > 0) {
                            result.put(b - a, i);
                            sets.get(i).add(b - a);
                        }

                        if (!result.containsKey(a * b)) {
                            result.put(a * b, i);
                            sets.get(i).add(a * b);
                        }

                        if (!result.containsKey(a / b) && a / b > 0) {
                            result.put(a / b, i);
                            sets.get(i).add(a / b);
                        }

                        if (!result.containsKey(b / a) && b / a > 0) {
                            result.put(b / a, i);
                            sets.get(i).add(b / a);
                        }
                    }
                }
            }
        }

        return result;
    }
}

