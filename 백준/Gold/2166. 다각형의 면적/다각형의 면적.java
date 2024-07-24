import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var x = new ArrayList<Long>();
        var y = new ArrayList<Long>();

        for (int i=0; i<N; i++){
            var now = Arrays.stream(bufferedReader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            x.add(now[0]);
            y.add(now[1]);
        }

        System.out.printf("%.1f", solution(N, x, y));
    }

    static double solution(int N, List<Long> x, List<Long> y){
        x.add(x.get(0));
        y.add(y.get(0));

        var sum = new long[2];
        for (int i=0; i<N; i++){
            sum[0] += (long) x.get(i) *y.get(i+1);
            sum[1] += (long) y.get(i) *x.get(i+1);
        }

        var result = ((double)Math.abs(sum[0]-sum[1]))/2;
        return result;
    }
}