import java.util.*;

/*
직원, 팀

직원
- 직원 번호
- 하루 평균 매출액
- 직위 : 팀원, 팀장
    팀원과 팀장의 관계는 화살표로

직원 번호 1은 CEO 고정 - 트리의 루트임
CEO를 제외하면 1개의 화살표를 받음 - 부모가 하나임
직원은 최대 2개의 팀에 소속 : 팀장, 팀원으로만 가능 (팀장팀장, 팀원팀원 불가능)
받는 화살표가 있다면 팀원, 주는 화살표가 있다면 팀장

워크숍을 가는데 모든 팀에서 1명이상
하루 평균 매출액의 합이 최소가 되도록

sales : 직원들의 하루 평균 매출액
links : 팀장 - 팀원의 관계

1번부터 시작

각 팀에서 만들어져야함
즉 팀장이 선택되거나 - 팀원중 한명 이상이 선택되는 경우
dp로 메모이제이션은 해야겠다
dp[직원 아이디][0 : 선택 안됨, 1 : 선택됨]
*/

class Solution {
    public int solution(int[] sales, int[][] links) {
        int[][] dp = new int[sales.length + 1][2];
        List<List<Integer>> custum = makeLink(sales.length, links);
        return Math.min(find(1, 0, sales, custum, dp), find(1, 1, sales, custum, dp));
    }
    
    public int find(int n, int isGo, int[] sales, List<List<Integer>> links, int[][] dp) {
        if (dp[n][isGo] != 0) return Math.max(dp[n][isGo], 0);
        
        if (links.get(n).isEmpty()) {
            dp[n][isGo] = (sales[n - 1] + 1) * isGo - 1;
            return Math.max(dp[n][isGo], 0);
        }
        
        // 지금꺼 안쓰는 경우 - 자식 중 하나라도 보내야함
        if (isGo == 0) {
            int result = 0;
            boolean isExist = false;
            int add = Integer.MAX_VALUE;
            for (int child : links.get(n)) {
                int no = find(child, 0, sales, links, dp);
                int yes = find(child, 1, sales, links, dp);
                
                isExist |= (yes <= no);
                add = Math.min(add, yes - no);
                
                result += Math.min(yes, no);
            }
            
            return dp[n][isGo] = (isExist ? result : result + add);
        }
        
        // 지금꺼 사용하는 경우
        int result = 0;
        for (int child : links.get(n)) {
            int no = find(child, 0, sales, links, dp);
            int yes = find(child, 1, sales, links, dp);

            result += Math.min(yes, no);
        }
        
        return dp[n][isGo] = result + sales[n - 1];
    }
    
    public List<List<Integer>> makeLink(int n, int[][] links) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            result.add(new ArrayList<>());
        }
        
        for (int[] link : links) {
            result.get(link[0]).add(link[1]);
        }
        
        return result;
    }
}