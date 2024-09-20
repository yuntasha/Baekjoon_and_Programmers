import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {
        var group = IntStream.range(0, N).toArray();

        for (int i=0; i<N; i++) {
            var a = find(i, group);
            var b = find(arr[i], group);
            group[Math.max(a, b)] = Math.min(a, b);
        }

        var result = 0;
        for (int i=0; i<N; i++) {
            if (i==find(i, group)) result++;
        }
        if (result == 1) return 0;
        return result;
    }

    static int find(int n, int[] group) {
        if (n!=group[n]) group[n] = find(group[n], group);
        return group[n];
    }
}