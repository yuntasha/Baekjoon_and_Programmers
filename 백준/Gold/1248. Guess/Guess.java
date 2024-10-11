import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var S = bf.readLine().toCharArray();

        System.out.println(Arrays.stream(solution(N, S)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    static int[] solution(int N, char[] S) {
        var map = new char[N][N];
        var now = 0;
        var result = new int[N];
        for (int i=0; i<N; i++) {
            for (int j=i; j<N; j++) {
                map[i][j] = S[now++];
            }
        }
        dfs(N, map, 0, result);
        return result;
    }

    static boolean dfs(int N, char[][] map, int now, int[] result) {
        if (now==N) return true;

        for (int i=-10; i<11; i++) {
            result[now] = i;
            var sum = 0;
            var b = true;
            for (int n=now; n>=0 && b; n--) {
                sum += result[n];
                if (map[n][now]=='+') {
                    if (sum<=0) b=false;
                } else if (map[n][now]=='-') {
                    if (sum>=0) b=false;
                } else if (sum!=0) b=false;
            }

            if (b && dfs(N, map, now+1, result)) return true;
        }

        return false;
    }
}