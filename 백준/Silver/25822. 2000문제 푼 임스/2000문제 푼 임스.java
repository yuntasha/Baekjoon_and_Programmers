import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var c = Float.parseFloat(bf.readLine());
        var C = (int)(c*100);

        var N = Integer.parseInt(bf.readLine());

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(Arrays.stream(solution(C, N, arr)).mapToObj(String::valueOf).collect(Collectors.joining("\n")));
    }

    static int[] solution(int C, int N, int[] arr) {
        var s = 0;
        var freeze = Math.min(C/99, 2);
        var now = freeze;
        var result = new int[]{0, 0};
        
        for (int e=0; e<N; e++) {
            result[1] = Math.max(arr[e], result[1]);

            if (arr[e]==0) {
                now--;
                while(now<0) {
                    if (arr[s++]==0) now++;
                }
            }

            result[0] = Math.max(result[0], e-s+1);
        }

        return result;
    }
}