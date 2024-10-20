import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

       var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

       var N = NM[0];
       var M = NM[1];

       var g = IntStream.range(0, N).toArray();

       for (int i=0; i<M; i++) {
           var now = Arrays.stream(bf.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)-1).toArray();
           if (now[0]<now[1]) continue;
           g[now[0]] = now[1];
       }

        System.out.println(solution(N, g));
    }


    static int solution(int N, int[] g) {
        var nowV = N-1; // 현재 넣어줄 값
        var minD = N-1; // 어디까지 넣어줄거냐
        var group = new int[N];
        for (int i=N-1; i>=0; i--) {
            minD = Math.min(minD, g[i]);
            group[i] = nowV;
            if (minD==i) {
                minD = nowV = i-1;
            }
        }

        var result = group[0];
        
        Loop: while (result!=N-1) {
            if (N%(result+1)!=0) {
                result = group[result+1];
            } else {
                for (int i=result; i<N-1; i+=result+1) {
                    if (group[i]==group[i+1]) {
                        result = group[result+1];
                        continue Loop;
                    }
                }
                break;
            }
        }

        return N/(result+1);
    }
}