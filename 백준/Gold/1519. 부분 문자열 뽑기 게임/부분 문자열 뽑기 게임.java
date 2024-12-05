import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        var S = read();

        System.out.println(solution(S));
    }

    static int solution(int N) {
        var fail = new boolean[N];

        if (N < 10) {
            return -1;
        }

        for (int i = 0; i < 10; i++) {
            fail[i] = true;
        }

        for (int n = 10; n < N; n++) {

            var isWin = false;

            var iMax = (n << 3) + (n << 1);

            Loop:
            for (int i = 10; i <= iMax; i = (i << 3) + (i << 1)) {
                for (int now = n % i; now > 0; now /= 10) {
                    if (now == n) continue;
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

        for (int i = 10; i <= N * 10; i *= 10) {
            for (int now = N % i; now > 0; now /= 10) {
                if (now == N) continue;
                if (fail[N - now]) {
                    result = Math.min(result, now);
                }
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}