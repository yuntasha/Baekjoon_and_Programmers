import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        var arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().mapToObj(String::valueOf).toArray(String[]::new);

        solution(N, M, arr);
        System.out.println(sb.toString());
    }

    static void solution(int N, int M, String[] arr){
        var result = new String[M];
        for (int i=0; i<arr.length; i++){
            result[0] = arr[i];
            printResult(M, arr, 1, result, i);
        }
    }

    static void printResult(int M, String[] arr, int m, String[] result, int last) {
        if (M==m) {
            sb.append(String.join(" ", result));
            sb.append("\n");
            return;
        }
        for (int i=last; i<arr.length; i++) {
            result[m] = arr[i];
            printResult(M, arr, m+1, result, i);
        }
    }
}