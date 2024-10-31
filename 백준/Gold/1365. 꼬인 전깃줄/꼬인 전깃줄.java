import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());
        var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {
        var list = new ArrayList<Integer>();

        for (int n : arr) {
            var now = find(list, n);
            if (now == list.size()) {
                list.add(n);
            } else {
                list.set(now, n);
            }
        }
        return N-list.size();
    }

    static int find(List<Integer> arr, int n) {
        var s = 0;
        var e = arr.size() - 1;

        if (arr.isEmpty()) {
            return 0;
        }

        if (arr.get(s) > n) {
            return 0;
        }
        if (arr.get(e) < n) {
            return arr.size();
        }

        while (s + 1 < e) {
            var mid = (s + e) / 2;
            if (arr.get(mid) < n) {
                s = mid;
            } else {
                e = mid;
            }
        }

        return e;
    }
}