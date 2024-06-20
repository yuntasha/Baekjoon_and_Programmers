import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        List<Integer> arr = new ArrayList<>();
        sol(N, M, 0, arr);
    }

    static void sol(int N, int M, int now, List<Integer> arr){
        if (M==now) {
            System.out.println(arr.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        } else {
            var start = now==0?0:arr.get(now-1);
            for (int i=start+1; i<=N; i++) {
                arr.add(i);
                sol(N, M, now+1, arr);
                arr.remove(now);
            }
        }
    }
}