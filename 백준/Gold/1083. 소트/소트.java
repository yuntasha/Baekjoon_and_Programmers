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
        var S = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, arr, S));
    }

    static String solution(int N, int[] arr, int S) {
        for (int idx=0; idx<N-1; idx++) {
            if (S==0) break;
            var maxIdx = idx;
            for (int i=idx+1; i<=idx+S && i<N; i++) {
                if (arr[maxIdx]>=arr[i]) continue;
                maxIdx = i;
            }
            S-=swap(arr, idx, maxIdx);
        }

        return Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    static int swap(int[] arr, int a, int b){
        var temp = arr[b];
        for (int i=b-1; i>=a; i--) {
            arr[i+1] = arr[i];
        }
        arr[a] = temp;
        return b-a;
    }
}