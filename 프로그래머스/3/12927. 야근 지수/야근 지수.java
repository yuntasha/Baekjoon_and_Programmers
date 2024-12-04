import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        Arrays.sort(works);
        
        var idx = works.length-1;
        long last = works[works.length-1];
        
        while (idx>0) {
            var now = works[idx] - works[idx-1];
            if (now * (works.length-idx) <= n) { // 뺄 수 있으면
                last = works[idx-1];
                n -= now * (works.length-idx);
            } else {
                break;
            }
            idx--;
        }
        
        
        
        for (int i=0; i<works.length; i++) {
            long work = works[i];
            if (work >= last) {
                work = last;
                work -= n/(works.length-i);
                n -= n/(works.length-i);
            }
            work = Math.max(0, work);
            answer += work * work;
        }
        
        return answer;
    }
}