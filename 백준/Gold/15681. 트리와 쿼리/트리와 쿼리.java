import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NRQ = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NRQ[0];
        var R = NRQ[1]-1;
        var Q = NRQ[2];

        var queries = new int[Q];

        List<Integer>[] tree = new ArrayList[N];

        for (int i=0; i<N; i++) tree[i]=new ArrayList<>();

        for (int i=0; i<N-1; i++) {
            var line = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(c -> Integer.parseInt(c)-1).toArray();
            tree[line[0]].add(line[1]);
            tree[line[1]].add(line[0]);
        }

        for (int i=0; i<Q; i++) queries[i] = Integer.parseInt(bufferedReader.readLine())-1;

        solution(N, R, Q, tree, queries);
    }

    static void solution(int N, int M, int Q, List<Integer>[] tree, int[] queries){
        var result = findResult(N, M, tree);
        for (int i=0; i<Q; i++){
            System.out.println(result[queries[i]]);
        }
    }

    static int[] findResult(int N, int M, List<Integer>[] tree) {
        var result = new int[N];
        findSub(N, tree, M, result);
        return result;
    }

    static int findSub(int N, List<Integer>[] tree, int now, int[] result){
        var r = 1;
        result[now]=-1;
        for (int i: tree[now]){
            if (result[i]==-1) continue;
            r += findSub(N, tree, i, result);
        }
        result[now] = r;
        return r;
    }
}