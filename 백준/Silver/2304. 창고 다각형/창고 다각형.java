import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Tower> towers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            towers.add(new Tower(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }

        System.out.println(solution(N, towers));
    }

    public static int solution(int N, List<Tower> towers) {
        int[] h = new int[1001];

        towers.sort(Comparator.comparingInt(Tower::getN));

        int now = 0;
        int idx = 0;
        for (int i = 0; i < 1001; i++) {
            if (idx < N && towers.get(idx).n == i) {
                now = Math.max(now, towers.get(idx).h);
                idx++;
            }
            h[i] = now;
        }

        now = 0;
        idx = N - 1;
        for (int i = 1000; i >= 0; i--) {
            if (idx >= 0 && towers.get(idx).n == i) {
                now = Math.max(now, towers.get(idx).h);
                idx--;
            }
            h[i] = Math.min(h[i], now);
        }

        int result = 0;

        for (int i = 0; i < 1001; i++) result += h[i];

        return result;
    }

    static class Tower {
        int n;
        int h;

        public Tower(int[] input) {
            this.n = input[0];
            this.h = input[1];
        }

        public int getN() {
            return n;
        }
    }
}