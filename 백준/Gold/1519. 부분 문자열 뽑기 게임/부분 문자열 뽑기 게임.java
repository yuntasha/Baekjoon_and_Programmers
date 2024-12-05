import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String S = bf.readLine();

        System.out.println(solution(S));
    }

    static int solution(String S) {
        int N = Integer.parseInt(S);
        boolean[] fail = new boolean[N];

        if (N < 10) {
            return -1;
        }

        for (int i=0; i<10; i++) {
            fail[i] = true;
        }

        for (int n=10; n<N; n++) {

            boolean isWin = false;

            int iMax = (n<<3)+(n<<1);

            Loop : for (int i=10; i<=iMax; i=(i<<3) + (i<<1)) {
                for (int now = n%i; now>0; now/=10) {
                    if (now == n) continue;
                    if (fail[n - now]) {
                        isWin = true;
                        break Loop;
                    }
                }
            }

            fail[n] = !isWin;
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
}