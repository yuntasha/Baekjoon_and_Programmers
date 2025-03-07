/*
이거 그냥 깔끔하게
흠흠흠
스택인 느낌인데
마무리에서 그냥 다 터뜨리는
그리디 + 우큐?
s가 빠른거로 일단 가져오고
그 다음에 e가 빠른거로 가져오자
그러면 될 듯?
shoot이 비었으면 일단 ready에서 하나 가져오기
그 다음에 shoot의 끝을 확인하고 그보다 짧은거 싹다 ready에서 가져와
*/
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        PriorityQueue<Target> ready = new PriorityQueue<>(Comparator.comparingInt(Target::getS));
        PriorityQueue<Target> shoot = new PriorityQueue<>(Comparator.comparingInt(Target::getE));
        
        for (int[] t : targets) {
            ready.add(new Target(t[0], t[1]));
        }
        
        while (!ready.isEmpty()) {
            shoot.add(ready.remove());
            
            while (!ready.isEmpty() && ready.peek().s < shoot.peek().e) {
                shoot.add(ready.remove());
            }
            
            shoot.clear();
            answer++;
        }
        
        return answer;
    }
    
    static class Target {
        int s;
        int e;
        
        Target(int s, int e) {
            this.s = s;
            this.e = e;
        }
        
        int getS() {
            return s;
        }
        
        int getE() {
            return e;
        }
    }
}