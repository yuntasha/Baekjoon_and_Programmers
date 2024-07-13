import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static int MAX_VALUE = 1_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());
        var arr = new ArrayList<Integer>();
        Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).forEach(arr::add);

        System.out.println(solution(N, arr));
    }

    static String solution(int N, ArrayList<Integer> arr){
        var existed = new boolean[MAX_VALUE+1];
        var result = new int[MAX_VALUE+1];

        for (int i: arr) existed[i] = true;

        for (int i=0; i<N; i++){
            var now = arr.get(i);
            for (int n=now*2; n<=MAX_VALUE; n+=now){
                if (existed[n]){
                    result[now]++;
                    result[n]--;
                }
            }
        }

        return arr.stream().map(i -> String.valueOf(result[i])).collect(Collectors.joining(" "));
    }
}