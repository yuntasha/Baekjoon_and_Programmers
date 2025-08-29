import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[] g = new int[N + 1];

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = find(Integer.parseInt(input.nextToken()), g);
            int b = find(Integer.parseInt(input.nextToken()), g);

            if (a == b) continue;
            g[Math.max(a, b)] = Math.min(a, b);
        }

        HashSet<Integer> set = new HashSet<>();

        for (int i = 1; i <= N; i++) set.add(find(i, g));

        System.out.println(set.size());
    }

    public static int find(int n, int[] g) {
        if (g[n] == 0 || g[n] == n) return n;
        return g[n] = find(g[n], g);
    }
}
