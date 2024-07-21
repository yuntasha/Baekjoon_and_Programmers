import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        var map = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, M, map));
    }

    static int solution(int N, int M, int[] map){
        var result = Integer.MAX_VALUE;
        var start = 0;
        var cnt = 0;
        var now = 0;
        var dest = 0;
        while (dest<N || now>=M){
            if (now<M) {
                cnt++;
                now+=map[dest++];
            } else {
                result = Math.min(result, cnt--);
                now-=map[start++];
            }
        }
        return result==Integer.MAX_VALUE?0:result;
    }
}