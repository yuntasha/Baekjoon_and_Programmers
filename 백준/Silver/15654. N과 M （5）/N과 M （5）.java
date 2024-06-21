import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var nodes = Arrays.asList(Arrays.stream(bufferedReader.readLine().split(" ")).map(Integer::parseInt).sorted().toArray(Integer[]::new));
        var N = NM[0];
        var M = NM[1];

        List<Integer> arr = new ArrayList<>();
        sol(N, M, 0, 0, arr, nodes);
    }

    static void sol(int N, int M, int now, int start, List<Integer> arr, List<Integer> nodes){
        if (M==now) {
            System.out.println(arr.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        } else {
            for (int i=0; i<N; i++) {
                var node = nodes.get(i);
                if (arr.contains(node)) continue;
                arr.add(node);
                sol(N, M, now+1, i+1, arr, nodes);
                arr.remove(now);
            }
        }
    }
}