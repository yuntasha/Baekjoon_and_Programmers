import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        HashMap<Integer, HashMap<Integer, Integer>> lh = new HashMap<>();

        for (int i=0; i<N; i++){
            var line = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            var nowNode = new HashMap<Integer, Integer>();
            var now = 1;
            while (line[now] != -1){
                nowNode.put(line[now]-1, line[now+1]);
                now+=2;
            }
            lh.put(line[0]-1, nowNode);
        }

        solution(N, lh);
    }

    static void solution(int N, HashMap<Integer, HashMap<Integer, Integer>> lh){
        var flist = dfs(N, lh, 0);
        var result = 0;
        for (int i=0; i<N; i++){
            if (flist[result]<flist[i]){
                result = i;
            }
        }
        System.out.println(Arrays.stream(dfs(N, lh, result)).max().getAsInt());
    }

    static int[] dfs(int N, HashMap<Integer, HashMap<Integer, Integer>> lh, int nodeNum){
        var result = new int[N];
        var stack = new LinkedList<Integer>();
        stack.addFirst(nodeNum);
        while (!stack.isEmpty()){
            var nowNode = stack.removeFirst();
            for (int i: lh.get(nowNode).keySet()) {
                if (i == nodeNum || result[i]!=0) continue;
                result[i] = result[nowNode] + lh.get(nowNode).get(i);
                stack.addFirst(i);
            }
        }
        return result;
    }
}