import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        var arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var tm = new TreeMap<Integer, Integer>();

        for (int i: arr) {
            tm.put(i, tm.getOrDefault(i, 0)+1);
        }

        solution(N, M, tm);
        System.out.println(sb.toString());
    }

    static void solution(int N, int M, TreeMap<Integer, Integer> tm){
        var result = new int[M];
        printResult(M, tm, 0, result, 0);
    }

    static void printResult(int M, TreeMap<Integer, Integer> tm, int m, int[] result, int last) {
        if (M==m) {
            sb.append(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
            sb.append("\n");
            return;
        }
        for (int i: tm.keySet()){
            if (last>i) continue;
            if (tm.get(i)==0) continue;
            tm.put(i, tm.get(i)-1);
            result[m] = i;
            printResult(M, tm, m+1, result, i);
            tm.put(i, tm.get(i)+1);
        }
    }
}