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

        ArrayList<Integer>[] arr = new ArrayList[4];

        for (int i=0; i<4; i++) arr[i] = new ArrayList<Integer>();

        for (int i=0; i<N; i++) {
            var now = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<4; j++) arr[j].add(now[j]);
        }

        System.out.println(solution(N, arr));
    }

    static long solution(int N, ArrayList<Integer>[] arr){
        var arr1 = new int[N*N];
        var idx = 0;

        for (int i: arr[0]){
            for (int j: arr[1]){
                arr1[idx++] = i+j;
            }
        }
        Arrays.sort(arr1);
        var result = 0L;

        for (int i: arr[2]){
            for (int j: arr[3]){
                var now = i+j;
                result += findR(now, arr1) - findL(now , arr1);
            }
        }
        return result;
    }

    static long findL(int n, int[] arr2){
        if (arr2[0]==-n) return 0;
        var s = 0;
        var e = arr2.length-1;
        while (s+1!=e){
            var mid = (s+e)/2;
            if (arr2[mid]+n<0) {
                s = mid;
            }else e = mid;
        }
        return e;
    }

    static long findR(int n, int[] arr2){
        if (arr2[arr2.length-1]==-n) return arr2.length;
        var s = 0;
        var e = arr2.length-1;
        while (s+1!=e){
            var mid = (s+e)/2;
            if (arr2[mid]+n<=0) {
                s = mid;
            }else e = mid;
        }
        return e;
    }
}