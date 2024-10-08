import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {
        return findMax(N, arr, 0);
    }

    static int findMax(int N, int[] arr, int now) {
        if (now==N) {
            return findLine(N, arr);
        }
        var result = 0;
        for (int i=now; i<N; i++) {
            swap(arr, now, i);
            result = Math.max(result, findMax(N, arr, now+1));
            swap(arr, now, i);
        }
        return result;
    }

    static void swap(int[] arr, int a, int b) {
        var temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    static int findLine(int N, int[] arr) {
        var angle = new int[N];
        angle[0] = arr[0];
        for (int i=1; i<N; i++) {
            angle[i] = angle[i-1] + arr[i];
        }

        var result = 0;
        for (int i=0; i<N; i++) {
            if (angle[i]==50) result++;
            if (angle[i]>=50) break;
            for (int j=i+1; j<N; j++) {
                if (angle[i]+50 == angle[j]) {
                    result++;
                    break;
                }
            }
        }

        return result;
    }
}