import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int MAX_VALUE = 200_001;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        System.out.println(solution(N, M));
    }

    static String solution(int N, int M){
        var result = new int[MAX_VALUE];

        var cnt = 0;

        result[M] = MAX_VALUE;
        result[N] = 1;

        var q = new LinkedList<Find>();

        q.add(new Find(N, 1));

        while(!q.isEmpty()){
            var now = q.remove();

            if (now.cnt>result[M]) break;

            if (now.node == M){
                cnt++;
                continue;
            }

            int[] list = {now.node*2, now.node+1, now.node-1};

            for (int i:list) {
                if (0<=i && i < MAX_VALUE) {
                    if (result[i] == now.cnt + 1 || result[i] == 0 || result[i] == MAX_VALUE) {
                        result[i] = now.cnt + 1;
                        q.add(new Find(i, now.cnt+1));
                    }
                }
            }
        }
        return result[M]-1 + "\n" + cnt;
    }

    static class Find{
        int node;
        int cnt;

        Find(int node, int cnt){
            this.node = node;
            this.cnt = cnt;
        }
    }
}