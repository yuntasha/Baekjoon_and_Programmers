import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.IntStream;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var A = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)-1).toArray();

        var M = Integer.parseInt(bufferedReader.readLine());

        var cmds = new int[M][3];

        for (int i=0; i<M; i++) {
            cmds[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)-1).toArray();
            cmds[i][2]++;
        }

        System.out.println(solution(N, A, M, cmds));
    }

    static int solution(int N, int[] A, int M, int[][] cmds){
        var hm = new HashMap<Integer, Integer>();
        var q = new LinkedList<Integer>();
        var key = getKey(A);
        hm.put(key, 0);
        q.add(key);

        while (!q.isEmpty()) {
            var k = q.remove();
            var now = getArr(N, k);
            for (int[] cmd: cmds) {
                var arr = getTemp(N, now, cmd[0], cmd[1]);
                key = getKey(arr);
                var cost = hm.get(k)+cmd[2];
                if (cost>=hm.getOrDefault(key, Integer.MAX_VALUE)) continue;
                hm.put(key, cost);
                q.add(key);
            }
        }

        Arrays.sort(A);
        return hm.getOrDefault(getKey(A), -1);
    }

    static int getKey(int[] arr){
        var result = 0;
        for (int i=0; i<arr.length; i++) {
            result*=10;
            result+=arr[i];
        }
        return result;
    }

    static int[] getArr(int N, int key){
        var result = new int[N];
        for (int i=N-1; i>=0; i--){
            result[i] = key%10;
            key/=10;
        }
        return result;
    }

    static int[] getTemp(int N, int[] arr, int x, int y){
        var result = Arrays.copyOf(arr, N);
        var temp = result[x];
        result[x] = result[y];
        result[y] = temp;
        return result;
    }
}