import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NV = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        var N = NV[0];
        var V = NV[1];

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();



        System.out.println(solution(N, V, arr));
    }

    static int solution(int N, int V, int[] arr) {
        var result = N;

        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                if (Math.abs(arr[i]-arr[j])>=V) {
                    result = Math.min(result, 1+solveP(i)+solveP(j-i));
                    break;
                }
            }
        }
        return result;
    }

    static int solveP(int n) {
        return n/2 + n%2;
    }
}