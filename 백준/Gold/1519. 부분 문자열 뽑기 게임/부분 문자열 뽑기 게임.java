import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solution(Integer.parseInt(br.readLine())));
    }
    public static int solution(int N) {
        boolean[] fail = new boolean[N];
        if (N < 10) {
            return -1;
        }

        for (int i=0; i<10; i++) {
            fail[i] = true;
        }
        for (int n=10; n<N; n++) {
            fail[n] = !solve(n, (n << 3) + (n << 1), fail);
        }
        int result = Integer.MAX_VALUE;
        for (int i=10; i<=((N<<3) + (N<<1)); i=(i<<3) + (i<<1)) {
            for (int now = N%i; now>0; now/=10) {
                if (now == N) continue;
                if (fail[N - now]) {
                    result = Math.min(result, now);
                }
            }
        }
        return result==Integer.MAX_VALUE?-1:result;
    }
    
    public static boolean solve(int n, int iMax, boolean[] fail) {
    	for (int i=10; i<=iMax; i=(i<<3) + (i<<1)) {
            for (int now = n%i; now>0; now/=10) {
                if (now == n) continue;
                if (fail[n - now]) {
                    return true;
                }
            }
        }
    	return false;
    }
}