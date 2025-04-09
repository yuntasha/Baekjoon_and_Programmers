import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * V개의 마을, E개의 도로
 * 일방통행 도로
 * 싸이클 찾고 싶다
 * 합이 가장 작은 싸이클을 찾는 프로그램
 * 2개 왕복도 가능하다
 * 
 * 입력
 * 2 <= V <= 400
 * 0 <= E <= V(V-1)
 * 거리 최대 1만
 * 
 * 출력
 * 실패시 -1
 * 1만 * 400 = 400만
 * 최대 400만까지 나옴
 * 
 * DFS로 그냥 쭉 탐색
 * 각 위치를 기준으로 자기 자신으로 돌아오면 그거
 * DFS로 각 위치까지 간다면 그 번호는 이미 처리된 것
 * DFS로 하면 빠른게 나오나?
 * 어차피 다 돌아야하니까 다 나올것 같은데
 * 이전에 가본 위치여야하니까
 * a -> b -> c
 * a -> c
 * 라고 한다면 예외가 생겨버린다
 * 왜냐?
 * 해당 방법으로 넘기면 싸이클이 없는데 방문된 곳이라 처리됨
 * 그냥 DFS로 위치 찾아버리자
 * 그게 제일 깔끔할듯?
 * 
 * 그럼 스택이 400이 쌓이는데 괜찮나?
 * 괜찮을듯?
 * 
 * 음
 * 역시 안됨 ㅎ
 * 
 * 플로이드 워셜처럼 해볼까?
 * 각 점부터 다른 점까지의 거리를 넣어주자
 * 각 점을 관통하는 거리를 넣어주자
 * 일단 전부 MAX값 넣어주고
 * 간선을 추가하자
 * 각 점을 거치면서 추가할 때 자기 자신도 추가하자
 * 1번한다고 하면 다른 애들 -> 1번 -> 다른거
 */

public class Main {
	
	static int FAIL = -1;
	static int MAX_VALUE = 10_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer input = new StringTokenizer(bf.readLine());
		
		int V = Integer.parseInt(input.nextToken());
		int E = Integer.parseInt(input.nextToken());
		
		int[][] roads = new int[V + 1][V + 1];
		
		for (int i = 1; i <= V; i++) {
			Arrays.fill(roads[i], MAX_VALUE);
		}
		
		for (int i = 0; i < E; i++) {
			input = new StringTokenizer(bf.readLine());
			
			int a = Integer.parseInt(input.nextToken());
			int b = Integer.parseInt(input.nextToken());
			int v = Integer.parseInt(input.nextToken());
			
			roads[a][b] = v;
		}
		
		System.out.println(solution(V, E, roads));
	}
	
	public static int solution(int V, int E, int[][] roads) {
		for (int m = 1; m <= V; m++) {
			for (int s = 1; s <= V; s++) {
				for (int e = 1; e <= V; e++) {
					roads[s][e] = Math.min(roads[s][e], roads[s][m] + roads[m][e]);
				}
			}
		}
		
		int result = MAX_VALUE;
		
		for (int i = 1; i <= V; i++) {
			result = Math.min(result, roads[i][i]);
		}
		
		return result == MAX_VALUE ? -1 : result;
	}
	
	public static class Road {
		int dest;
		int cost;
		
		Road(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
}