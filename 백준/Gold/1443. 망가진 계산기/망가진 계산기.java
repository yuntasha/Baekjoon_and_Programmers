import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var DP = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var D= DP[0];
        var P = DP[1];

        System.out.println(solution(D, P));
    }

    static int solution(int D, int P) {
        MAX = 1;
        for (int i=0; i<D; i++) {
            MAX*=10;
        }

        return dfs(P, 0, 1, 9);
    }


    static int dfs(int P, int p, int num, int last) {
        if (p==P) {
            return num;
        }

        var result = -1;

        for (int i=last; i>1; i--) {
            var now = num*i;

            if (now<MAX) {
                var n = dfs(P, p+1, now, i);

                if (n==-1) continue;
                if (result < n) {
                    result = n;
                }
            }
        }

        return result;
    }
}