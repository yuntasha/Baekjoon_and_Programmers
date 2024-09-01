import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int K;
    static int[] N;
    static int[][] DP;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NK = bufferedReader.readLine().split(" ");
        N = Arrays.stream(NK[0].split("")).mapToInt(Integer::parseInt).toArray();
        K = Integer.parseInt(NK[1]);
        DP = new int[1_000_001][K];
        System.out.println(solution());
    }

    static int solution(){
        return dfs(0);
    }

    static int dfs(int depth) {
        if (depth == K) return makeNum();

        if (DP[makeNum()][depth]>0) {
            return DP[makeNum()][depth];
        }

        var result = -1;

        for (int i=0; i<N.length-1; i++) {
            for (int j=i+1; j<N.length; j++) {
                if (i==0 && N[j]==0) continue;
                swap(i, j);
                result = Math.max(result, dfs(depth+1));
                swap(i, j);
            }
        }
        DP[makeNum()][depth] = result;

        return result;
    }

    static int makeNum() {
        var result = 0;
        for (int i: N) {
            result*=10;
            result+=i;
        }
        return result;
    }

    static void swap(int i, int j){
        var temp = N[i];
        N[i] = N[j];
        N[j] = temp;
    }
}