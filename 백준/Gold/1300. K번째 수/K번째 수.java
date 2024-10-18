import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

       var N = Integer.parseInt(bf.readLine());

       var K = Integer.parseInt(bf.readLine());

        System.out.println(solution(N, K));
    }


    static int solution(int N, int K) {
        var s = 1;
        var e = K;
        if (K==1) return 1;
        
        while (s+1<e) {
            var mid = (s+e)/2;
            var now = find(N, mid);
            if (now>=K) e = mid;
            else s = mid;
        }

        return e;
    }

    static int find(int N, int num) {
        var result = 0;
        for (int i=1; i<=Math.min(N, num); i++) {
            var now = Math.min(num/i, N);
            if (now==0) break;
            result += now;
        }
        return result;
    }
}