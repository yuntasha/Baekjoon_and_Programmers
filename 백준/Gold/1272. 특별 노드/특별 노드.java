import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NR = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NR[0];
        var R = NR[1]-1;

        var V = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        DP = new int[N][N];

        var links = new int[N][N];

        for (int i=0; i<N-1; i++) {
            var now = Arrays.stream(bf.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)-1).toArray();
            links[now[0]][now[1]] = links[now[1]][now[0]] = 1;
        }

        System.out.println(solution(N, R, V, links));
    }

    static int solution(int N, int R, int[] V, int[][] links) {
        var result = V[R];

        for (int i=0; i<N; i++) {
            if (links[R][i]==0) continue;
            result += find(N, V, links, i, R);
        }

        return result;
    }

    static int find(int N, int[] V, int[][] links, int now, int sn) {
        if (DP[now][sn] > 0) return DP[now][sn];
        var result1 = V[now] - V[sn];
        var result2 = V[now];
        for (int i=0; i<N; i++) {
            if (i==now || V[now]>V[i] || links[now][i]==0) continue;
            result1 += find(N, V, links, i, sn);
            result2 += find(N, V, links, i, now);
        }

        DP[now][sn] = Math.min(result1, result2);

        if (DP[now][sn]==0) DP[now][sn] = V[now]-V[sn];

        return DP[now][sn];
    }
}