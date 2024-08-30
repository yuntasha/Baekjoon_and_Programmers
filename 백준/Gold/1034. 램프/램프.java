import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var hm = new HashMap<String, Integer>();

        for (int i=0; i<N; i++) {
            var now = bufferedReader.readLine().strip();
            hm.put(now, hm.getOrDefault(now, 0)+1);
        }

        var K = Integer.parseInt(bufferedReader.readLine().strip());
        System.out.println(solution(N, M, hm, K));
    }

    static int solution(int N, int M, HashMap<String, Integer> hm, int K){
        var result = new int[M+1];
        for (String s: hm.keySet()){
            var cnt = 0;
            for (char c: s.toCharArray()){
                if (c=='0') cnt++;
            }
            result[cnt] = Math.max(result[cnt], hm.get(s));
        }

        for (int i=2; i<=M; i++) {
            result[i] = Math.max(result[i-2], result[i]);
        }


        while (K>M) {
            K-=2;
        }
        return result[K];
    }
}