import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        int[][] arr = new int[4][N];

        for (int i=0; i<N; i++) {
            var now = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<4; j++) arr[j][i] = now[j];
        }

        System.out.println(solution(N, arr));
    }

    static long solution(int N, int[][] arr){
        var arr1 = new int[N*N];
        var arr2 = new int[N*N];

        var idx = 0;
        for (int i: arr[0]){
            for (int j: arr[1]){
                arr1[idx++] = i+j;
            }
        }

        idx = 0;
        for (int i: arr[2]){
            for (int j: arr[3]){
                arr2[idx++] = i+j;
            }
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        var s = 0;
        var e = arr2.length-1;
        var result = 0L;
        while (s<arr1.length && e>=0) {
            if (arr1[s] + arr2[e] == 0) {
                var cnt1 = 1L;
                var cnt2 = 1L;
                while (++s<arr1.length && arr1[s]+arr2[e]==0) cnt1++;
                while (--e>=0 && arr1[s-1]+arr2[e]==0) cnt2++;
                result += cnt1*cnt2;
            } else if (arr1[s] + arr2[e] < 0) {
                s++;
            } else {
                e--;
            }
        }
        return result;
    }
}