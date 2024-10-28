import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    static String solution(int N) {
        var arr = new int[26];
        if (dfs(N, 0, 0, arr, 0)) {
            var sb = new StringBuilder();
            for (int i=0; i<26; i++) {
                for (int j=1; j<arr[i]; j++) {
                    sb.append((char) ('A'+i));
                }
            }
            for (int i=0; i<26 && arr[i]!=0; i++) {
                sb.insert(arr[i]-1, (char) ('A'+i));
            }
            return sb.toString();
        } else {
            return "-1";
        }
    }

    static boolean dfs(int N, int n, int sum, int[] arr, int idx) {
        if (N==sum) {
            return true;
        }
        if (idx>=26) {
            return false;
        }
        var end = Math.min(sum+1, N);
        for (int i=end; i>n; i--) {
            if (N<i+sum) {
                continue;
            }
            arr[idx] = i;
            if (dfs(N, i, i+sum, arr, idx+1)) {
                return true;
            }
        }
        return false;
    }

    static int idx(int n) {
        return n-1;
    }
}