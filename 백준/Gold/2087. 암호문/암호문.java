import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
가능한 모든 숫자를 쳐내야할 것 같음
총 40개의 숫자가 있으니까
20개 20개로 나눠버리면
100만 2개가 나와서 이분탐색으로 찾아내면 될듯
각각 20비트니까 int로 연결시켜버리면 어떻게 될 것 같은데
int 8비트 -> 200만개
1600만
16_000_000
그럼 16mb 충분!

다 하고 이진법 변환할 때 N개의 비트가 나오도록 0으로 시작하면 생략되니까 이점 유의해서 작성하면 됨


 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        int K = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, arr, K));
    }

    static String solution(int N, int[] arr, int K) {
        Map<Integer, Integer> aMap = find(0, N / 2, K, arr);
        Map<Integer, Integer> bMap = find(N / 2, N, K, arr);

        for (int key : aMap.keySet()) {
            if (bMap.containsKey(K - key)) {
                return makeBits(aMap.get(key), N / 2) + makeBits(bMap.get(K - key), N - N / 2);
            }
        }

        return "";
    }

    static String makeBits(int n, int len) {
        String bn = Integer.toBinaryString(n);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < len - bn.length(); i++) {
            result.append(0);
        }

        return result.append(bn).toString();
    }

    static Map<Integer, Integer> find(int s, int e, int max, int[] arr) {
        Map<Integer, Integer> result = new HashMap<>();
        dfs(s, 0, 0, e, max, result, arr);
        return result;
    }

    static void dfs(int n, int sum, int bits, int last, int max, Map<Integer, Integer> result, int[] arr) {
        if (sum > max) return;
        if (n == last) {
            result.put(sum, bits);
            return;
        }

        dfs(n + 1, sum + arr[n], (bits << 1) + 1, last, max, result, arr);
        dfs(n + 1, sum, (bits << 1), last, max, result, arr);
    }

    static int compare(String s1, String s2) {
        int c = Integer.compare(s1.length(), s2.length());
        if (c == 0) return s1.compareTo(s2);
        return c;
    }
}