import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(Arrays.stream(solution(N, arr)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    static int[] solution(int N, int[] arr) {
        Arrays.sort(arr);
        var result = new int[N * 2];
        Arrays.fill(result, -1);

        if (dfs(N, arr, result, 0, 0)) {
            return result;
        }
        return new int[]{-1};
    }

    static boolean dfs(int N, int[] arr, int[] result, int now, int bits) {
        while (now < 2 * N) {
            if (result[now] == -1) {
                break;
            }
            now++;
        }

        if (now == 2 * N) {
            if (bits == (1 << N) - 1) return true;
            return false;
        }

        for (int i = 0; i < N; i++) {
            if ((bits & (1 << i)) > 0) continue;
            if (now+arr[i]+1 >= 2*N) break;
            if (result[now+arr[i]+1] >= 0) continue;
            result[now] = result[now+arr[i]+1] = arr[i];
            if (dfs(N, arr, result, now+1, bits | (1<<i))) return true;
            result[now] = result[now+arr[i]+1] = -1;
        }

        return false;
    }
}