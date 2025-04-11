/*
 * 가로로 N칸을 가지는 2개의 행
 * -10 ~ 10사이에 0이 아닌 숫자판을 가짐
 * 위 아래 숫자판의 개수는 다를 수 있다
 * 
 * 각 열의 위와 아래에 있는 두 숫자들의 곱을 모두 더한 값
 * 
 * 순서는 유지하면서 위치를 바꿀 수 있다
 * 
 * 어떤 것을 이어 붙이냐인데
 * DP로 풀면 될 듯하이...
 * 
 * 현재 길이, 윗 인덱스, 아랫 인덱스
 * 
 * 최소 -100 * 400 = -40000
 */

import java.io.*;
import java.util.*;

public class Main {
	
	static int MIN = -40_001;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	
    	int N = Integer.parseInt(bf.readLine());
    	
    	List<Integer> arr1 = new ArrayList<>();
    	List<Integer> arr2 = new ArrayList<>();

		StringTokenizer input = new StringTokenizer(bf.readLine());
    	for (int i = 0; i < N; i++) {        	
        	int n = Integer.parseInt(input.nextToken());
        	if (n != 0) arr1.add(n);
    	}
    	
    	input = new StringTokenizer(bf.readLine());
    	for (int i = 0; i < N; i++) {	
        	int n = Integer.parseInt(input.nextToken());
        	if (n != 0) arr2.add(n);
    	}
    	
    	System.out.println(solution(N, arr1, arr2));
    }
    
    static int solution(int N, List<Integer> arr1, List<Integer> arr2) {
    	int[][][] dp = new int[N + 1][arr1.size() + 1][arr2.size() + 1];
    	boolean[][][] visited = new boolean[N + 1][arr1.size() + 1][arr2.size() + 1];
    	visited[0][0][0] = true;
    	
    	return find(N, arr1.size(), arr2.size(), arr1, arr2, dp, visited);
    }
    
    static int find(int n, int i1, int i2, List<Integer> arr1, List<Integer> arr2, int[][][] dp, boolean[][][] visited) {
    	if (visited[n][i1][i2]) {
    		return dp[n][i1][i2];
    	}
    	
    	dp[n][i1][i2] = MIN;
    	visited[n][i1][i2] = true;
    	
    	if (i1 < n && i2 < n) {
    		dp[n][i1][i2] = Math.max(dp[n][i1][i2], find(n - 1, i1, i2, arr1, arr2, dp, visited));
    	}
    	
    	if (i1 < n && i2 > 0) {
    		dp[n][i1][i2] = Math.max(dp[n][i1][i2], find(n - 1, i1, i2 - 1, arr1, arr2, dp, visited));
    	}
    	
    	if (i2 < n && i1 > 0) {
    		dp[n][i1][i2] = Math.max(dp[n][i1][i2], find(n - 1, i1 - 1, i2, arr1, arr2, dp, visited));
    	}
    	
    	if (i2 > 0 && i1 > 0) {
    		dp[n][i1][i2] = Math.max(dp[n][i1][i2], find(n - 1, i1 - 1, i2 - 1, arr1, arr2, dp, visited) + arr1.get(i1 - 1) * arr2.get(i2 - 1));
    	}
    	
    	return dp[n][i1][i2];
    }
}