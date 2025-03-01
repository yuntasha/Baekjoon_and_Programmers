import java.io.*;
import java.util.*;

/*
이거 LCS하고 사이사이에 넣는다는 느낌으로 다가가서
그냥 해보면 어떻게 될 것 같은데
전체 길이 - 최대 증가 수열 길이
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];

        StringTokenizer input = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input.nextToken());
        }

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {

        List<Integer> dp = new ArrayList<>();

        for (int n : arr) {
            if (dp.isEmpty() || n > dp.get(dp.size() - 1)) {
                dp.add(n);
                continue;
            }

            int idx = find(n, dp);

            dp.set(idx, n);
        }

        return N - dp.size();
    }

    static int find(int n, List<Integer> dp) {
        if (dp.get(0) >= n) {
            return 0;
        }

        int s = 0;
        int e = dp.size() - 1;

        while (s + 1 < e) {
            int m = (s + e) >> 1;

            if (dp.get(m) > n) {
                e = m;
            } else {
                s = m;
            }
        }

        return e;
    }
}