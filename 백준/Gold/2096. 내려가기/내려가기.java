import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, bf));
    }

    static String solution(int N, BufferedReader bf) throws IOException {
        var min = new int[2][3];
        var max = new int[2][3];

        for (int i=0; i<N; i++) {

            var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            min[i&1][0] = Math.min(min[i&1^1][0],min[i&1^1][1]) + arr[0];
            max[i&1][0] = Math.max(max[i&1^1][0],max[i&1^1][1]) + arr[0];

            min[i&1][1] = Math.min(min[i&1^1][0], Math.min(min[i&1^1][1], min[i&1^1][2])) + arr[1];
            max[i&1][1] = Math.max(max[i&1^1][0], Math.max(max[i&1^1][1], max[i&1^1][2])) + arr[1];

            min[i&1][2] = Math.min(min[i&1^1][1],min[i&1^1][2]) + arr[2];
            max[i&1][2] = Math.max(max[i&1^1][1],max[i&1^1][2]) + arr[2];
        }

        var minV = Integer.MAX_VALUE;
        var maxV = -1;

        for (int i=0; i<3; i++) {
            minV = Math.min(minV, min[(N-1)&1][i]);
            maxV = Math.max(maxV, max[(N-1)&1][i]);
        }

        return maxV + " " + minV;
    }
}