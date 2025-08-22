/*
트리가 여러개 존재해서 포레스트
어차피 루트 아니면 부모를 하나씩 가지고 있음
이거 같은 트리냐만 따지면 되는거 아닌가
그럼 같은 그룹으로 묶는게 뭐더라 암튼 그걸로 묶고
같은 그룹인 애들만 모아서 홀짝, 역홀짝 노드를 찾아(부모가 없는 경우)
그래서 홀짝 1개만 있으면 역홀짝 트리
역홀짝 1개만 있으면 홀짝 트리

근데 두개 더해서 1개라면
1개가 홀짝이라면 홀짝 +1;
1개가 역홀짝이면 역홀짝 +1?
*/

import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int N = nodes.length;
        
        HashMap<Integer, Integer> nodeNum = new HashMap<>();
        
        for (int i = 0; i < N; i++) nodeNum.put(nodes[i], i);
        
        int[] g = new int[N];
        int[] count = new int[N];
        
        for (int i = 0; i < N; i++) g[i] = i;
        
        for (int[] edge : edges) {
            int a = find(nodeNum.get(edge[0]), g);
            int b = find(nodeNum.get(edge[1]), g);
            count[nodeNum.get(edge[0])]++;
            count[nodeNum.get(edge[1])]++;
            
            g[Math.max(a, b)] = Math.min(a, b);
        }
        
        HashMap<Integer, Tree> trees = new HashMap<>();
        
        for (int i = 0; i < N; i++) {
            int now = find(i, g);
            if (!trees.containsKey(now)) trees.put(now, new Tree());
            if ((count[i] & 1) == (nodes[i] & 1)) trees.get(now).ori++;
            else trees.get(now).rev++;
        }
        
        int[] result = {0, 0};
        
        for (int num : trees.keySet()) {
            Tree now = trees.get(num);
            
            if (now.ori + now.rev == 1) {
                result[0] += now.ori;
                result[1] += now.rev;
            } else {
                if (now.ori == 1) result[0]++;
                if (now.rev == 1) result[1]++;
            }
        }
        
        return result;
    }
    
    public int find(int n, int[] g) {
        if (g[n] != n) g[n] = find(g[n], g);
        return g[n];
    }
    
    static class Tree {
        int ori;
        int rev;
        
        Tree() {
            this.ori = 0;
            this.rev = 0;
        }
    }
}