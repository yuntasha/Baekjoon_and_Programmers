import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        StringBuilder output = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(bf.readLine());

            int[] parents = new int[N + 1];

            for (int i = 0; i < N - 1; i++) {
                StringTokenizer input = new StringTokenizer(bf.readLine());

                int p = Integer.parseInt(input.nextToken());
                int c = Integer.parseInt(input.nextToken());

                parents[c] = p;
            }

            StringTokenizer input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            output.append("\n").append(solution(N, parents, a, b));
        }

        System.out.println(output.substring(1));
    }

    static int solution(int N, int[] parents, int a, int b) {
        Set<Integer> set = new HashSet<>();

        int now = a;

        while (now > 0) {
            set.add(now);
            now = parents[now];
        }

        now = b;

        while (now > 0) {
            if (set.contains(now)) return now;
            now = parents[now];
        }

        return now;
    }
}