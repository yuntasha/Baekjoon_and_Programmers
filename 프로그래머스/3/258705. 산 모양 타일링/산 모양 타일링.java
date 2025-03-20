/*
대놓고 DP 문제다
아 이거 한칸씩 가자
2n + 1배열 만들자
짝수의 경우 / 2한 값에서 위에 저거 있는지 확인
있다면 -1에서 2배로
없다면 -1 그냥

-2에서 + 1 이렇게
*/

import java.util.*;

class Solution {
    public int solution(int n, int[] tops) {
        
        int MOD = 10_007;
        
        int[] dp = new int[(n << 1) + 2];
        
        dp[0] = dp[1] = 1;
        
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
            if ((i & 1) == 0 && tops[(i >> 1) - 1] == 1) {
                dp[i] += dp[i - 1];
            }
            dp[i] %= MOD;
        }
        
        return dp[(n << 1) + 1];
    }
}