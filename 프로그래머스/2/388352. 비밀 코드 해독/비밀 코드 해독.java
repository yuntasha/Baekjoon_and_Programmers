/*
오름차순 정렬
30 29 28 27 26
5 4 3 2 1
29 7 27 26
30 * 30 * 30 * 10
270_000
그냥 전체 다 돌자...

*/

class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        return find(0, 1, new int[5], n, q, ans);
    }
    
    public static int find(int idx, int cursor, int[] result, int n, int[][] q, int[] ans) {
        if (idx == 5) {
            for (int i = 0; i < q.length; i++) {
                int exist = 0;
                for (int a : result) {
                    for (int b : q[i]) {
                        if (a == b) exist++;
                    }
                }
                
                if (ans[i] != exist) return 0;
            }
            return 1;
        }
        
        int output = 0;
        
        for (int i = cursor; i <= n; i++) {
            result[idx] = i;
            output += find(idx + 1, i + 1, result, n, q, ans);
        }
        
        return output;
    }
}