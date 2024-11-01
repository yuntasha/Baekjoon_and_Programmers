import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static String[] TUNES = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
    static int N;
    static int M;
    static int[] tunes;
    static int[] lines;
    static int bits;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = NM[0];
        M = NM[1];

        lines = Arrays.stream(bf.readLine().split(" ")).mapToInt(Main::find).toArray();
        tunes = Arrays.stream(bf.readLine().split(" ")).mapToInt(Main::find).toArray();
        System.out.println(solution());
    }

    static int solution() {
        var result = new int[N];
        Arrays.fill(result, -1);
        return dfs(0, result);
    }

    static int dfs(int n, int[] now) {
        if (n == N) {
            if (bits != (1 << M) - 1) {
                return Integer.MAX_VALUE;
            }
            var max = Arrays.stream(now).filter(i -> i > 0).max().orElse(0);
            var min = Arrays.stream(now).filter(i -> i > 0).min().orElse(0);
            if (max == 0) {
                return 0;
            }

            return max - min + 1;
        }

        var result = Integer.MAX_VALUE;
        var b = bits;

        for (int i = 0; i < M; i++) {
            bits = b | (1 << i);
            now[n] = (tunes[i] - lines[n] + 12) % 12;
            result = Math.min(result, dfs(n + 1, now));
            now[n] += 12;
            result = Math.min(result, dfs(n + 1, now));

            bits = b;
            now[n] = -1;
        }

        return result;
    }

    static int find(String s) {
        for (int i = 0; i < TUNES.length; i++) {
            if (s.equals(TUNES[i])) {
                return i;
            }
        }
        return -1;
    }
}