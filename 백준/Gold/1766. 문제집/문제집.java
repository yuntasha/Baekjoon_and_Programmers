import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var from = new HashMap<Integer, Integer>();
        var to = new HashMap<Integer, ArrayList<Integer>>();

        for (int i=0; i<M; i++){
            var now = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (!to.containsKey(now[0])) to.put(now[0], new ArrayList<>());
            from.put(now[1], from.getOrDefault(now[1], 0)+1);
            to.get(now[0]).add(now[1]);
        }

        System.out.println(Arrays.stream(solution(N, M, from, to)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    static int[] solution(int N, int M, HashMap<Integer, Integer> from, HashMap<Integer, ArrayList<Integer>> to){
        var pq = new PriorityQueue<Integer>();
        var result = new int[N];
        var now = 0;
        for (int i=1; i<=N; i++){
            if (!from.containsKey(i)) pq.add(i);
        }

        while (!pq.isEmpty()) {
            var n = pq.remove();
            result[now++] = n;
            if (!to.containsKey(n)) continue;
            for (int i: to.get(n)){
                var cnt = from.get(i);
                if (cnt==1) pq.add(i);
                else from.put(i, --cnt);
            }
        }

        return result;
    }
}