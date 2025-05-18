import java.util.*;

/*
그냥 가장 멀리있는 애들을 카운트해주면 됨
아 그냥 BFS하면서 자연스럽게 처리하자
최대 길이 20000이라 int 범위 내

1번부터 탐색해서 가장 멀리있는 녀석의 개수
*/

class Solution {
    public int solution(int n, int[][] edge) {
        List<List<Integer>> connect = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) connect.add(new ArrayList<>());
        
        for (int[] ed : edge) {
            connect.get(ed[0]).add(ed[1]);
            connect.get(ed[1]).add(ed[0]);
        }
        
        int[] visited = new int[n + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        q.add(1);
        visited[1] = 1;
        int max = 0;
        int count = 0;
        
        while (!q.isEmpty()) {
            int now = q.remove();
            
            if (visited[now] != max) {
                max = visited[now];
                count = 0;
            }
            count++;
            
            for (int c : connect.get(now)) {
                if (visited[c] > 0) continue;
                visited[c] = visited[now] + 1;
                q.add(c);
            }
        }
        
        return count;
    }
}