/*
음... 각 노드에서 사용했을때와 사용하지 않았을때로 만들어야할듯
그냥 클래스 노드로해서 dp로 최대값 구하도록 하는게 좋을 느낌인데
그냥 dfs하고 Dp섞어서 해보자
 */

import java.io.*;
import java.util.*;

public class Main {

    static Map<Integer, List<Integer>> map = new HashMap<>();
    static Map<Integer, Integer> use = new HashMap<>();
    static Map<Integer, Integer> notUse = new HashMap<>();
    static Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        for (int i = 0; i < N; i++) {
            map.put(Integer.parseInt(bf.readLine()), new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(bf.readLine());

            for (int k : map.keySet()) {
                if (map.containsKey(k + n)) {
                    map.get(k).add(k + n);
                    map.get(k + n).add(k);
                }
            }
        }

        System.out.println(solution(N, map));
    }

    static int solution(int N, Map<Integer, List<Integer>> map) {
        int result = 0;

        for (int k : map.keySet()) {
            if (visited.contains(k)) continue;
            result += find(k);
        }

        return result;
    }

    static int find(int n) {
        if (use.containsKey(n)) return Math.max(use.get(n), notUse.get(n));
        visited.add(n);
        int useResult = 0;
        int notUseResult = 0;

        for (int k : map.get(n)) {
            if (visited.contains(k)) continue;
            find(k);
            useResult += notUse.get(k);
            notUseResult += Math.max(notUse.get(k), use.get(k));
        }

        use.put(n, useResult + n);
        notUse.put(n, notUseResult);

        return Math.max(use.get(n), notUse.get(n));
    }
}