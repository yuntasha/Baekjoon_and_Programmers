import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var T = Integer.parseInt(bufferedReader.readLine());

        var N = Integer.parseInt(bufferedReader.readLine());

        var listN = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var M = Integer.parseInt(bufferedReader.readLine());

        var listM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(T, N, listN, M, listM));
    }

    static long solution(int T, int N, int[] listN, int M, int[] listM){
        long result = 0L;

        var hm1 = findSum(N, listN);
        var hm2 = findSum(M, listM);

        for (int i1: hm1.keySet()){
            var i2 = T-i1;
            if (!hm2.containsKey(i2)) continue;
            result += hm1.get(i1) * hm2.get(i2);
        }

        return result;
    }

    static HashMap<Integer, Long> findSum(int N, int[] list) {
        var result = new HashMap<Integer, Long>();
        List<Integer> l = new ArrayList<>();
        for (int i=0; i<N; i++){
            for (int n=0; n<i; n++){
                var now = l.get(n)+list[i];
                result.put(now, result.getOrDefault(now, 0L)+1);
                l.set(n, now);
            }
            l.add(list[i]);
            result.put(list[i], result.getOrDefault(list[i], 0L)+1);
        }

        return result;
    }
}