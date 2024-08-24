import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NMK = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NMK[0];
        var M = NMK[1];
        var K = NMK[2];

        var candies = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var group = IntStream.range(0, N).toArray();

        for (int i=0; i<M; i++) {
            var now = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)-1).toArray();
            var n1 = findGroup(now[0], group);
            var n2 = findGroup(now[1], group);
            group[Math.max(n1, n2)] = Math.min(n1, n2);
        }

        System.out.println(solution(N, M, K, candies, group));
    }

    static int solution(int N, int M, int K, int[] candies, int[] group){
        var headCnt = new int[N];

        for (int i=0; i<N; i++) findGroup(i, group);
        for (int i=0; i<N; i++) {
            var now = findGroup(i, group);
            if (now!=i) candies[now] += candies[i];
            headCnt[now]++;
        }
        var list = new ArrayList<Group>();
        for (int i=0; i<N; i++) {
            if (headCnt[i]>0) {
                list.add(new Group(headCnt[i], candies[i]));
            }
        }

        var dp = new int[K];

        for (Group g: list){
            for (int i=K-1; i>=0; i--) {
                if (dp[i]==0 && i>0) continue;
                if (g.headCount+i >= K) continue;
                dp[g.headCount+i] = Math.max(dp[g.headCount+i], dp[i]+g.candyCount);
            }
        }

        var result = 0;
        for (int n: dp) {
            result = Math.max(n, result);
        }
        return result;
    }

    static int findGroup(int n, int[] group){
        if (group[n]!=n) group[n] = findGroup(group[n], group);
        return group[n];
    }

    static class Group {
        int headCount;
        int candyCount;

        Group(int headCount, int candyCount) {
            this.headCount = headCount;
            this.candyCount = candyCount;
        }
    }
}