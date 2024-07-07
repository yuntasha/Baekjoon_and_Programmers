import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());
        var M = Integer.parseInt(bufferedReader.readLine());

        var bus = new ArrayList<HashMap<Integer, Integer>>();

        for (int i=0; i<N; i++){
            bus.add(new HashMap<>());
        }

        for (int i=0; i<M; i++){
            var now = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            bus.get(now[0]-1).put(now[1]-1, Math.min(now[2], bus.get(now[0]-1).getOrDefault(now[1]-1, Integer.MAX_VALUE)));
        }

        var sted = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, M, bus, sted[0]-1, sted[1]-1));
    }


    static String solution(int N, int M, ArrayList<HashMap<Integer, Integer>> bus, int start, int end){
        var visited = new boolean[N];
        List<List<Integer>> street = new ArrayList<>();
        var cost = new int[N];

        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;
        for (int i=0; i<N; i++){
            street.add(new ArrayList());
        }
        street.get(start).add(start);

        for (int i=0; i<N; i++){
            var nowNode = 0;
            var nowValue = Integer.MAX_VALUE;
            for (int j=0; j<N; j++){
                if (visited[j] || nowValue<=cost[j]) continue;
                nowNode = j;
                nowValue = cost[j];
            }

            visited[nowNode] = true;


            for (int dest: bus.get(nowNode).keySet()){
                var nowCost = bus.get(nowNode).get(dest) + cost[nowNode];
                if (nowCost >= cost[dest]) continue;
                cost[dest] = nowCost;
                var nowStreet = new ArrayList<Integer>(street.get(nowNode));
                nowStreet.add(dest);
                street.set(dest, nowStreet);
            }
            if (nowNode == end) break;
        }

        return cost[end] + "\n" +
                street.get(end).size() + "\n" +
                street.get(end).stream().map(i->String.valueOf(i+1)).collect(Collectors.joining(" "));
    }
}


