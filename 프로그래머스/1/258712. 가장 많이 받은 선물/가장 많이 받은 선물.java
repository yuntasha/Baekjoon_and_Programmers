import java.util.*;

/*
선물을 두 사람 사이에 더 적게 받은 사람이 1개 받는 것
A -> B 5 개
B -> A 3개
---> 그러면 B -> A 1개

같다면
선물 지수 작은 사람 -> 큰 사람 1개
선물 지수 : (준 선물 - 받은 선물)

선물 지수 기억
누가 누구한테 받았는지 기억

해시맵 사용해서 이름 인덱스 연결
선물지수
이차원 배열로 받은 수 정리 +-

다음 달에 가장 많은 선물을 받는 친구의 선물의 개수
*/

class Solution {
    public int solution(String[] friends, String[] gifts) {
        
        HashMap<String, Integer> name = new HashMap<>();
        
        for (int i = 0; i < friends.length; i++) {
            name.put(friends[i], i);
        }
        
        int[] points = new int[friends.length];
        int[][] gift = new int[friends.length][friends.length];
        
        for (String n : gifts) {
            String[] names = n.split(" ");
            
            int a = name.get(names[0]);
            int b = name.get(names[1]);
            
            gift[a][b]++;
            gift[b][a]--;
            
            points[a]++;
            points[b]--;
        }
        
        int[] result = new int[friends.length];
        
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                if (gift[i][j] > 0) {
                    result[i]++;
                } else if (gift[i][j] < 0){
                    result[j]++;
                } else {
                    if (points[i] > points[j]) result[i]++;
                    else if (points[i] < points[j]) result[j]++;
                }
            }
        }
        
        int max = 0;
        
        for (int i = 0; i < friends.length; i++) {
            max = Math.max(max, result[i]);
        }
        
        return max;
    }
}