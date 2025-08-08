/*
그냥 시뮬레이션 아닌가?
필요하면 그냥 늘려 그리고 줄여
다음 시간 넘기고 k만큼 전 시간에 신설했는지 확인
*/

class Solution {
    public int solution(int[] players, int m, int k) {
        
        int[] addServerLog = new int[24];
        int now = 0;
        int result = 0;
        
        for (int h = 0; h < 24; h++) {
            if (h - k >= 0) now -= addServerLog[h - k];
            int need = players[h] / m;
            if (need > now) {
                addServerLog[h] = need - now;
                now = need;
                result += addServerLog[h];
            }
        }
        
        return result;
    }
}