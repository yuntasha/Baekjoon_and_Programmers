import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var arr = new int[N][3];

        var dpMax = new int[N][3];
        var dpMin = new int[N][3];

        dpMin[0] = dpMax[0] = arr[0] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i=1; i<N; i++){
            arr[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i=1; i<N; i++){
            dpMax[i][0] = Math.max(dpMax[i-1][0], dpMax[i-1][1]) + arr[i][0];
            dpMax[i][2] = Math.max(dpMax[i-1][2], dpMax[i-1][1]) + arr[i][2];
            dpMax[i][1] = Math.max(dpMax[i][0] - arr[i][0], dpMax[i][2] - arr[i][2]) + arr[i][1];

            dpMin[i][0] = Math.min(dpMin[i-1][0], dpMin[i-1][1]) + arr[i][0];
            dpMin[i][2] = Math.min(dpMin[i-1][2], dpMin[i-1][1]) + arr[i][2];
            dpMin[i][1] = Math.min(dpMin[i][0] - arr[i][0], dpMin[i][2] - arr[i][2]) + arr[i][1];
        }

        System.out.println(Arrays.stream(dpMax[N-1]).max().getAsInt() + " " + Arrays.stream(dpMin[N-1]).min().getAsInt());
    }
}