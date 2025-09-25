/*
으아
N L M
N = 문자의 개수 <= 26
L = 문자열의 길이
M = 문자열 부분 집합의 개수

음
char이 26까지만 쓰이니까
대충 32가 5비트
15비트 사용해서 나타내자

왜 3개라고 생각했지

M에 들어가는 부분 문자열의 최대 길이는 10
다음이 될 수 있는지 확인을 해봐야할 것 같은데용?

다음이 가능한지 서로 확인

오 그렇게 가자

그럼 이게 시간 복잡도가 어떻게 나옴?
600 * 600 * 10 간단하네


처음엔 지정
0으로 시작하면
5 길이에 1길이
4 나오면 스탑

N개만 사용되어야한다는 그게 사용 안됐음
예를들어
A B C D E
이렇게 있는데 3개만 사용해야하면 좀 다른 결과가 나와야한다
최대 26개니까 비트로 전환

2^26
64_000_000
이렇게 됨

N종류이하의 문자로 이루어짐
비트마스킹 넘기자 그냥
 */



import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int L = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        List<String> sl = new ArrayList<>();
        input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < M; i++) sl.add(input.nextToken());

        System.out.println(solution(N, L, M, sl));
    }

    public static int solution(int N, int L, int M, List<String> sl) {
        List<List<Integer>> g = getGraph(M, sl);

        int[] bits = new int[M];

        for (int i = 0; i < M; i++) bits[i] = getBits(sl.get(i));

        int l = L - sl.get(0).length();

        if (l < 0) return getException(N, L);

        int result = 0;

        int[][] dp = new int[l][M];

        for (int i = 0; i < M; i++) {
            result += find(0, i, bits[i], g, l, dp, bits, N);
        }

        return result;
    }

    public static int getException(int N, int L) {
        int result = 1;

        for (int i = 0; i < L; i++) result *= N;

        return result;
    }

    public static int find(int d, int n, int bit, List<List<Integer>> g, int l, int[][] dp, int[] bits, int N) {
        if (findCnt(bit) > N) return 0;
        if (d == l) return 1;
        if (dp[d][n] != 0) return Math.max(dp[d][n], 0);

        int result = 0;

        for (int next : g.get(n)) {
            result += find(d + 1, next, bit | bits[next], g, l, dp, bits, N);
        }

        dp[d][n] = result;
        if (dp[d][n] == 0) dp[d][n] = -1;

        return result;
    }

    public static List<List<Integer>> getGraph(int N, List<String> sl) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < N; i++) result.add(new ArrayList<>());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isNext(sl.get(i), sl.get(j))) result.get(i).add(j);
            }
        }

        return result;
    }

    public static boolean isNext(String s1, String s2) {
        for (int i = 1; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i - 1)) return false;
        }

        return true;
    }

    public static int getBits(String s) {
        int result = 0;

        for (char c : s.toCharArray()) result |= 1 << (c - 'A');

        return result;
    }

    public static int findCnt(int bit) {
        int result = 0;

        while (bit > 0) {
            result += bit & 1;
            bit = bit >> 1;
        }

        return result;
    }
}