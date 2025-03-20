import java.util.*;

/*
도넛 -> 그냥 원형 1개는 자기 자신을 가리킴
막대 -> 한 줄로 쭉
8자 -> 도넛 한 정점 기준 2개로

각각 존재하는데 한 정점을 만들고 그 정점이랑 쟤네랑 연결

생성한 정점 번호, 도넛, 막대, 8자 그래프

100만

일단 각 정점을 생성한 애라고 가정하고 각 가는 방향으로 만들어보기

그냥 탐색한 경우
막대 -> 중복없이 탐색됨 - 하나도 더 진행 못하는 애가 하나 있음
도넛 -> 중복 만나면 끝
8자 -> 탐색하다보면 2개로 뻗어나가는 친구 있음

방향이 있구나
그럼 자신을 한번도 보지 못하는 애가 범인
근데 이건 막대그래프 예외가 있음 그럼 출발하는게 최소인 애가 정답
막대인데 1개 출발 1개출발

1개 출발 1개 출발이면 중간 합류밖에 없음 -> 그 다음애가 몇개 받는지 체크하고 많은 애 있는 쪽이 정답
무조건 2개 이상
그니까 out이 2이상 in이 0

도넛 - 나머지
막대 - 앞으로 나갈 곳이 없는 친구 개수
8자 - 2개 이상 나가는 친구 개수
*/

class Solution {
    
    int[] in;
    int[] out;
    
    public int[] solution(int[][] edges) { 
        int N = init(edges);
        
        int[] ans = new int[4];
        
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                if (out[i] > 1) ans[0] = i;
            } else {
                if (out[i] == 0) ans[2]++;
                if (out[i] == 2) ans[3]++;
            }
        }
        
        ans[1] = out[ans[0]] - ans[2] - ans[3];
        
        return ans;
    }
    
    public int init(int[][] edges) {
        
        in = new int[1_000_001];
        out = new int[1_000_001];
        
        int max = 0;
        
        for (int[] edge : edges) {
            out[edge[0]]++;
            in[edge[1]]++;
            max = Math.max(max, Math.max(edge[0], edge[1]));
        }
        
        return max;
    }
}