import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static int L,K,C;
    static int[] arr, sum;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var LKC = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        L = LKC[0];
        K = LKC[1];
        C = LKC[2];

        arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution());
    }

    static String solution() {
        Arrays.sort(arr);
        sum = new int[K];
        sum[0] = arr[0];
        for (int i=1; i<K; i++) sum[i] = arr[i] - arr[i-1];

        var s = 1;
        var e = L;
        if (check(1)!=-1) return "1 " + check(1);
        while (s<e-1) {
            var mid = (s+e)/2;
            var now = check(mid);
            if (now==-1) s=mid;
            else e=mid;
        }
        return e + " " + check(e);
    }

    static int check(int maxLen) {
        var result = new ArrayList<Integer>();
        if (L-arr[arr.length-1]>maxLen) return -1;
        var now = L-arr[arr.length-1];
        for (int i=arr.length-1; i>=0; i--) {
            if (sum[i]>maxLen) return -1;
            if (now+sum[i]>maxLen) {
                result.add(i);
                now = sum[i];
            } else {
                now += sum[i];
            }
        }
        if (result.size()>C) return -1;
        else if (result.size()==C) return arr[result.get(result.size()-1)];
        else return arr[0];
    }
}
