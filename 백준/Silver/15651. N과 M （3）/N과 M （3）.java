import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;


public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        solution(N, M);
        System.out.println(sb.toString());
    }

    static void solution(int N, int M){
        var S = IntStream.rangeClosed(1, N).mapToObj(String::valueOf).toArray(String[]::new);
        printResult(M, S, 0, new String[M]);
    }

    static void printResult(int M, String[] S, int m, String[] result) {
        if (M==m) {
            sb.append(String.join(" ", result));
            sb.append("\n");
            return;
        }
        for (String s: S) {
            result[m] = s;
            printResult(M, S, m+1, result);
        }
    }
}