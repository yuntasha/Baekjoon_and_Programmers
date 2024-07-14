import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var T = Integer.parseInt(bufferedReader.readLine());

        for (int i=0; i<T; i++){
            var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            var N = NM[0];
            var M = NM[1];
            var time = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            var from = new ArrayList<HashSet<Integer>>();
            var to = new ArrayList<HashSet<Integer>>();

            for (int j=0; j<N; j++){
                from.add(new HashSet<>());
                to.add(new HashSet<>());
            }
            for (int j=0; j<M; j++){
                var fromTo = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)-1).toArray();
                to.get(fromTo[0]).add(fromTo[1]);
                from.get(fromTo[1]).add(fromTo[0]);
            }

            var dest = Integer.parseInt(bufferedReader.readLine())-1;

            System.out.println(solution(N, dest, time, from, to));
        }
    }

    static int solution(int N, int dest, int[] time, ArrayList<HashSet<Integer>> from, ArrayList<HashSet<Integer>> to){
        var q = new LinkedList<Integer>();
        var result = new int[N];

        for (int i=0; i<N; i++){
            if (from.get(i).isEmpty()) {
                q.add(i);
                result[i] = time[i];
            }
        }

        while (!q.isEmpty()){
            var now = q.remove();
            for (int i: to.get(now)){
                result[i] = Math.max(result[i], result[now] + time[i]);
                from.get(i).remove(now);
                if (from.get(i).isEmpty()) q.add(i);
            }
        }

        return result[dest];
    }
}