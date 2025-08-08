/*
간단하게 완탐
근데 BFS로 석유 매장 크기를 구하고 거기에 번호를 부여
map에 석유 매장 크기를 넣는다
번호에 따라 배열에 크기를 기록해두자
*/

import java.util.*;

class Solution {
    
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    public int solution(int[][] land) {
        int N = land.length;
        int M = land[0].length;
        int answer = 0;
        
        List<Integer> oilSize = new ArrayList<>();
        
        int[][] map = getMap(N, M, land, oilSize);
        
        Set<Integer> set = new HashSet<>();
        
        for (int j = 0; j < M; j++) {
            set.clear();
            for (int i = 0; i < N; i++) {
                set.add(map[i][j]);
            }
            
            int now = 0;
            
            for (int oilIdx : set) {
                now += oilSize.get(oilIdx);
            }
            
            answer = Math.max(answer, now);
        }
        
        return answer;
    }
    
    public int[][] getMap(int N, int M, int[][] land, List<Integer> oilSize) {
        oilSize.add(0);
        
        int[][] map = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (land[i][j] == 1 && map[i][j] == 0) oilSize.add(getSize(i, j, oilSize.size(), N, M, land, map));
            }
        }
        
        return map;
    }
    
    public int getSize(int n, int m, int num, int N, int M, int[][] land, int[][] map) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        
        q.add(new Node(n, m));
        map[n][m] = num;
        int result = 0;
        
        while (!q.isEmpty()) {
            Node node = q.remove();
            result++;
            
            for (int d = 0; d < 4; d++) {
                int x = node.x + dx[d];
                int y = node.y + dy[d];
                
                if (!isIn(x, y, N, M)) continue;
                if (land[x][y] == 0) continue;
                if (map[x][y] > 0) continue;
                
                q.add(new Node(x, y));
                map[x][y] = num;
            }
        }
        
        return result;
    }
    
    public boolean isIn(int x, int y, int N, int M) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
    
    public static class Node {
        int x;
        int y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}