/*
오 그럼 mod한 값을 가지고 있으면 될듯?
 */

import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        BigInteger[] arr = new BigInteger[N];

        for (int i = 0; i < N; i++) arr[i] = new BigInteger(bf.readLine());

        int K = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, arr, K));
    }

    public static String solution(int N, BigInteger[] bArr, int K) {
        int[] arr = new int[N];
        int[] len = new int[N];

        int L = 0;

        BigInteger mod = new BigInteger(String.valueOf(K));

        for (int i = 0; i < N; i++) {
            arr[i] = bArr[i].mod(mod).intValue();
            L += bArr[i].toString().length();
            len[i] = bArr[i].toString().length();
        }

        int[] tenMod = new int[L];

        tenMod[0] = 1;

        for (int i = 1; i < L; i++) {
            tenMod[i] = (tenMod[i - 1] * 10) % K;
        }

        long count = find(0, L, 0, len, tenMod, N, K, arr, new long[K][1 << N]);

        if (count == 0) return "0/1";

        long all = 1;

        for (int i = 1; i <= N; i++) all *= i;

        long m = gcd(all, count);

        return (count / m) + "/" + (all / m);
    }

    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static long find(int n, int l, int visited, int[] len, int[] tenMod, int N, int K, int[] arr, long[][] dp) {
        if (dp[n][visited] != 0) return Math.max(dp[n][visited], 0);
        if (visited == (1 << N) - 1) return n == 0 ? 1 : 0;

        long result = 0;

        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) > 0) continue;
            int now = (n + tenMod[l - len[i]] * arr[i]) % K;
            result += find(now, l - len[i], visited | (1 << i), len, tenMod, N, K, arr, dp);
        }

        dp[n][visited] = result;
        if (result == 0) dp[n][visited] = -1;

        return result;
    }
}