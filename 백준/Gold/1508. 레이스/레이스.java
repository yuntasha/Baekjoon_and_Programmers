import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(bf.readLine());

        var N = Integer.parseInt(st.nextToken());
        var M = Integer.parseInt(st.nextToken()); // 심판 수
        var K = Integer.parseInt(st.nextToken()); // 심판이 서있을 수 있는 곳 수

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, M, K, arr));
    }

    static String solution(int N, int M, int K, int[] arr) {
        // 최대 거리를 이분탐색으로 찾음
        // 찾을 때 뒤에서부터 시작해서 거리를 최대로 빠바박 하면 사전순 가장 뒤에 있는 결과가 될  <= 이건 이분탐색 안하는게 더 빠름

        var s = 0;
        var e = N;

        if (M == 1) {
            var result = new StringBuilder();
            result.append(1);

            for (int i = 1; i < K; i++) result.append(0);

            return result.toString();
        }

        if (M == 2) {
            var result = new StringBuilder();
            result.append(1);

            for (int i = 2; i < K; i++) result.append(0);

            result.append(1);

            return result.toString();
        }

        while (s + 1 < e) {
            var mid = (s + e) / 2;

            var result = find(K, M, arr, mid);

            if (result.isSuccess) {
                s = mid;
            } else {
                e = mid;
            }
        }

        return find(K, M, arr, s).result;
    }

    static Result find(int K, int M, int[] arr, int max) {
        var now = new StringBuilder();

        var last = arr[0];
        now.append(1);
        M--;

        for (int i = 1; i < K; i++) {
            if (M == 0) {
                now.append(0);
            } else if (arr[i] - last >= max) {
                now.append(1);
                M--;
                last = arr[i];
            } else {
                now.append(0);
            }
        }

        if (M == 0) {
            return new Result(now.toString());
        } else {
            return new Result();
        }
    }

    static class Result {
        boolean isSuccess;
        String result;

        Result() {
            this.isSuccess = false;
            this.result = null;
        }

        Result(String result) {
            this.isSuccess = true;
            this.result = result;
        }
    }
}