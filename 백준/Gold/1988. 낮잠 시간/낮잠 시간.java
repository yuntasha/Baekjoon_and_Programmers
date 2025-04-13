/*
 * 그냥 뭐 DP인듯하구여
 * 현재 수면중인지 아닌지 확인
 * 
 * dp[현재 시간][현재 최대 수면][수면중인지]
 * 
 * 수면중 -> 직전 수면중 + 현재값, 직전 비수면
 * 비수면 -> 직전 수면중, 직전 비수면
 */

import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer input = new StringTokenizer(bf.readLine());
    	
    	int N = Integer.parseInt(input.nextToken());
    	int B = Integer.parseInt(input.nextToken());
    	
    	int[] arr = new int[N];
    	
    	for (int i = 0; i < N; i++) {
    		arr[i] = Integer.parseInt(bf.readLine());
    	}
    	
    	System.out.println(solution(N, B, arr));
    }
    
    public static int solution(int N, int B, int[] arr) {
    	int[][][] dp = new int[N][B + 1][2];
    	
    	return Math.max(find(N - 1, B, 0, N, B, arr, dp), find(N - 1, B, 1, N, B, arr, dp)) - 1;
    }
    
    public static int find(int n, int b, int isSleep, int N, int B, int[] arr, int[][][] dp) {
    	if (dp[n][b][isSleep] > 0) {
    		return dp[n][b][isSleep];
    	}
    	
    	if (b == 0 && isSleep == 1) {
    		return Integer.MIN_VALUE;
    	}
    	
    	if (n + 1 < b) {
    		return Integer.MIN_VALUE;
    	}
    	
    	if (n == 0) return 1;
    	
    	if (isSleep == 1) {
    		dp[n][b][isSleep] = Math.max(find(n - 1, b - 1, 1, N, B, arr, dp) + arr[n], find(n - 1, b - 1, 0, N, B, arr, dp));
    	} else {
    		dp[n][b][isSleep] = Math.max(find(n - 1, b, 1, N, B, arr, dp), find(n - 1, b, 0, N, B, arr, dp));
    	}
    	
    	return dp[n][b][isSleep];
    }
}