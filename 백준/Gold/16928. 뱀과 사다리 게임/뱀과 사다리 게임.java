import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var NM = bufferedReader.readLine().strip().split(" ");
        var N = Integer.parseInt(NM[0]);
        var M = Integer.parseInt(NM[1]);

        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i=0; i<N+M; i++){
            var bridge = bufferedReader.readLine().strip().split(" ");
            hm.put(Integer.parseInt(bridge[0]), Integer.parseInt(bridge[1]));
        }

        System.out.println(solution(N, hm));
    }

    public static int solution(int N, HashMap<Integer, Integer> hm){
        int[] mapCnt = new int[101];

        var q = new LinkedList<Integer>();
        q.addLast(1);

        while (!q.isEmpty()){
            var now = q.removeLast();
            if (now == 100) break;
            var sb = hm.get(now);
            if (sb != null){
                if (mapCnt[sb] == 0 || mapCnt[sb] > mapCnt[now]) {
                    mapCnt[sb] = mapCnt[now];
                    q.addFirst(sb);
                }
            } else {
                for (int i = 1; i < 7 && now + i < 101; i++) {
                    if (mapCnt[now + i] == 0 || mapCnt[now + i] > mapCnt[now] + 1) {
                        mapCnt[now + i] = mapCnt[now] + 1;
                        q.addFirst(now + i);
                    }
                }
            }
        }
        return mapCnt[100];
    }
}