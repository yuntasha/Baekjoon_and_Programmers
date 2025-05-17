import java.util.*;

/*
무지와 어피치가 귀가 방향이 비슷해서 택시 합승을 적절히 이용하면 얼마나 아낄수 있을지
A, B각각 4번에서 시작
양방향임

이거 전부다 서로 거리 구하는 그 알고리즘을 사용하고 벨만포드였나
각 지점에서 시작해서 최종 위치로 구함
지점 개수는 200이하
그럼 200 * 200 * 200 안전
그리고 

s : 시작 지점
a : a의 도착 지점
b : b의 도착 지점

fare : 연결 정보 c d e라고 한다면 c와 d를 연결하는 요금 f

가는 길 무조건 존재함
각 연결하는 것 하나씩
f의 크기는 1
최대 비용은 200_000_000 2억 괜찮음

*/

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 1부터 시작을 0부터 시작으로 변경
        a--;
        b--;
        s--;
        int answer = Integer.MAX_VALUE;
        int[][] costs = getBelman(n, fares);
        
        // for (int[] cost : costs) System.out.println(Arrays.toString(cost));
        
        for (int m = 0; m < n; m++) {
            if ((costs[s][m] == 0 && s != m) || (costs[m][a] == 0 && m != a) || costs[m][b] == 0 && m != b) continue;
            answer = Math.min(answer, costs[s][m] + costs[m][a] + costs[m][b]);
        }
        
        return answer;
    }
    
    public int[][] getBelman(int n, int[][] fares) {
        int[][] result = new int[n][n];
        
        for (int[] fare : fares) {
            result[fare[0] - 1][fare[1] - 1] = result[fare[1] - 1][fare[0] - 1] = fare[2];
        }
        
        for (int m = 0; m < n; m++) {
            for (int s = 0; s < n; s++) {
                if (m == s) continue;
                if (result[m][s] == 0) continue;
                for (int e = 0; e < n; e++) {
                    if (result[m][e] == 0 || s == e || e == m) continue;
                    if (result[s][e] == 0 || result[s][e] > result[s][m] + result[m][e]) {
                        result[e][s] = result[s][e] = result[s][m] + result[m][e];
                    }
                }
            }
        }
        
        return result;
    }
}