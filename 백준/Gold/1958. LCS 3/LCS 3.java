import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
LCS인데
3개의 문자열에 전부 부분 순열로 가장 긴거

완탐적 사고
- 전부 탐색 부분 문자열 싹다 구하기
- 같은 문자열 중 가장 긴것 고르기

- 전부 탐색 부분
	- (백트래킹) 일단 오름차순 아닌애들 전부 걸러
	- 이전 애들로 완성이 됐으면 그 다음으로 찾아가면 됨

3개 전부 각각의 인덱스를 두고 탐색해볼까
예를 들어 a찾으니 없어 -> 넘어가 -> b찾으니 없어 넘어가 -> c찾으니 없어 넘어가
3개다 있어 그러면 DP에 넣어줘
이거 반복하면 

2개로 생각을 해보자
최대 26개인데
각각 문자열에 대해 각 알파벳 위치를 적어둬
어차피 하나씩밖에 못들어감
2^26? => 1000 * 1000 * 64 = 64_000_000 가능성 있다
이거 그럼 비트로 넘겨버리면 안되냐
boolean으로 6400만? => 1.2억비트
1500만 바이트
1.5만 k
15m * 8하면 120으로 가능한 가장 작은 비트로 넘겨버리자
이거 3개 다 저장해야하는데 그러면 멤초임
해시맵 <Integer, Integer> 3개는 되나
싹다 만들어지면 결국 터짐

1 2 3을 만들 수 있다면 1 3은 찾을 필요가 없지

하나는 어떻게 찾는데
- 완탐
	- 시초임
- 1차원 배열 DP
	- 가능은 한데...
	- 역추적 가능
	- 
- 이분 탐색
	- 종류 찾기가 빡셈
	
아 그 LIS라고 하는구나...
그럼 DP로 넘길 수 있겠는데...
일단 그럼...
각 문자열의 인덱스 싹다 조회하고
같으면 이전 것에서 하나 추가
이런 방식으로 3중 반복문 돌리면...

킹받으니 재귀로 해보자
기본
dp[a][b][c] = max (dp[a - 1][b][c], dp[a][b - 1][c], dp[a][b][c - 1])
현재 값이 전부 같은 경우
dp[a][b][c] = dp[a - 1][b - 1][c - 1] + 1
이렇게 진행
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String s1 = bf.readLine();
		String s2 = bf.readLine();
		String s3 = bf.readLine();
		
		System.out.println(solution(s1, s2, s3));
	}
	
	public static int solution(String s1, String s2, String s3) {
		int[][][] dp = new int[s1.length() + 1][s2.length() + 1][s3.length() + 1];
		boolean[][][] visited = new boolean[s1.length() + 1][s2.length() + 1][s3.length() + 1];
		
		return dfs(s1.length(), s2.length(), s3.length(), s1, s2, s3, dp, visited);
	}
	
	public static int dfs(int i1, int i2, int i3, String s1, String s2, String s3, int[][][] dp, boolean[][][] visited) {
		if (visited[i1][i2][i3]) {
			return dp[i1][i2][i3];
		}
		
		visited[i1][i2][i3] = true;
		
		if (i1 > 0) {
			dp[i1][i2][i3] = Math.max(dp[i1][i2][i3], dfs(i1 - 1, i2, i3, s1, s2, s3, dp, visited));
		}
		
		if (i2 > 0) {
			dp[i1][i2][i3] = Math.max(dp[i1][i2][i3], dfs(i1, i2 - 1, i3, s1, s2, s3, dp, visited));
		}
		
		if (i3 > 0) {
			dp[i1][i2][i3] = Math.max(dp[i1][i2][i3], dfs(i1, i2, i3 - 1, s1, s2, s3, dp, visited));
		}
		
		if (i1 > 0 && i2 > 0 && i3 > 0 && s1.charAt(i1 - 1) == s2.charAt(i2 - 1) && s2.charAt(i2 - 1) == s3.charAt(i3 - 1)) {
			dp[i1][i2][i3] = Math.max(dp[i1][i2][i3], dfs(i1 - 1, i2 - 1, i3 - 1, s1, s2, s3, dp, visited) + 1);
		}
		
		return dp[i1][i2][i3];
	}
}