import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        HashMap<Integer, List<Integer>> hm = new HashMap<>();

        for (int i=0; i<N-1; i++){
            var line = Arrays.stream(bufferedReader.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray();

            var now1 = hm.getOrDefault(line[0], new ArrayList<>());
            if (now1.isEmpty()) hm.put(line[0], now1);
            now1.add(line[1]);


            var now2 = hm.getOrDefault(line[1], new ArrayList<>());
            if (now2.isEmpty()) hm.put(line[1], now2);
            now2.add(line[0]);
        }

        var result = solution(N, hm);
        Arrays.stream(result, 2, N + 1).forEach(System.out::println);
    }

    public static int[] solution(int N, HashMap<Integer, List<Integer>> hm){
        var result = new int[N+1];

        LinkedList<Integer> ll = new LinkedList<>();
        ll.addFirst(1);

        while (!ll.isEmpty()){
            int now = ll.removeLast();
            for (int i: hm.get(now)){
                if (result[i]==0 && i!=1){
                    result[i] = now;
                    ll.addFirst(i);
                }
            }
        }
        return result;
    }

    private static int maxThree(int a, int b, int c){
        return Math.max(a, Math.max(b, c));
    }
}