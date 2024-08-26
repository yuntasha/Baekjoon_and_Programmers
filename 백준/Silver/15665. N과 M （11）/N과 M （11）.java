import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        var arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var ts = new TreeSet<Integer>();

        for (int i: arr) {
            ts.add(i);
        }

        solution(N, M, ts);
        System.out.println(sb.toString());
    }

    static void solution(int N, int M, TreeSet<Integer> ts){
        var result = new int[M];
        printResult(M, ts, 0, result);
    }

    static void printResult(int M, TreeSet<Integer> ts, int m, int[] result) {
        if (M==m) {
            sb.append(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
            sb.append("\n");
            return;
        }
        for (int i: ts){
            result[m] = i;
            printResult(M, ts, m+1, result);
        }
    }
}