import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {
        List<Integer>[] result = new ArrayList[N];
        for (int i=0; i<N; i++) result[i] = new ArrayList<>();

        for (int i=N-1; i>0; i--) {
            result[i].sort(Comparator.reverseOrder());
            var now = 1;
            for (int n=0; n<result[i].size(); n++) {
                now = Math.max(now, result[i].get(n) + n + 1);
            }
            result[arr[i]].add(now);
        }
        var r = 0;
        result[0].sort(Comparator.reverseOrder());
        for (int i=0; i<result[0].size(); i++) {
            r = Math.max(r, result[0].get(i)+i);
        }
        return r;
    }
}