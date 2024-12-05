import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        var bf = new BufferedReader(new InputStreamReader(System.in));

        var S = bf.readLine();

        System.out.println(solution(S));
    }

    static int solution(String S) {
        var N = Integer.parseInt(S);
        var fail = new boolean[N];

        if (N < 10) {
            return -1;
        }

        for (int i=0; i<10; i++) {
            fail[i] = true;
        }

        for (int n=10; n<N; n++) {
            var s = String.valueOf(n);

            var isWin = false;

            Loop : for (int i=0; i<s.length(); i++) {
                for (int j=i+1; j<=s.length(); j++) {
                    if (i==0 && j==s.length()) continue;
                    var now = Integer.parseInt(s.substring(i, j));

                    if (now == 0) continue;

                    if (fail[n - now]) {
                        isWin = true;
                        break Loop;
                    }
                }
            }

            if (!isWin) {
                fail[n] = true;
            }
        }

        var result = Integer.MAX_VALUE;

        for (int i=0; i<S.length(); i++) {
            for (int j=i+1; j<=S.length(); j++) {
                if (i==0 && j==S.length()) continue;
                var now = Integer.parseInt(S.substring(i, j));

                if (now == 0) continue;

                if (fail[N - now]) {
                    result = Math.min(result, now);
                }
            }
        }

        return result==Integer.MAX_VALUE?-1:result;
    }
}