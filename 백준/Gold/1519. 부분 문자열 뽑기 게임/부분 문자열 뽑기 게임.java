import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    static int[] MY_DP;
    static int[] OTHER_DP;

    public static void main(String[] args) throws IOException {
        var bf = new BufferedReader(new InputStreamReader(System.in));

        var S = bf.readLine();

        System.out.println(solution(S));
    }

    static int solution(String S) {
        var N = Integer.parseInt(S);

        if (N < 10) {
            return -1;
        }

        var fail = new HashSet<Integer>();

        for (int i=0; i<10; i++) {
            fail.add(i);
        }

        for (int n=10; n<N; n++) {
            var s = String.valueOf(n);

            var isWin = false;

            Loop : for (int i=0; i<s.length(); i++) {
                for (int j=i+1; j<=s.length(); j++) {
                    if (i==0 && j==s.length()) continue;
                    var now = Integer.parseInt(s.substring(i, j));

                    if (now == 0) continue;

                    if (fail.contains(n - now)) {
                        isWin = true;
                        break Loop;
                    }
                }
            }

            if (!isWin) {
                fail.add(n);
            }
        }

        var result = Integer.MAX_VALUE;

        for (int i=0; i<S.length(); i++) {
            for (int j=i+1; j<=S.length(); j++) {
                if (i==0 && j==S.length()) continue;
                var now = Integer.parseInt(S.substring(i, j));

                if (now == 0) continue;

                if (fail.contains(N - now)) {
                    result = Math.min(result, now);
                }
            }
        }

        return result==Integer.MAX_VALUE?-1:result;
    }

    /**
     * 승리할 수밖에 없는 숫자, 패배만 있는 숫자
     * 만약 바꿨을때 그 수가 지는 숫자라면
     */
}