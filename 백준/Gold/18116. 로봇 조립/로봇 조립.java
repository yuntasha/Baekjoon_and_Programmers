/*
음 유파로 하자
유파로 진행하 합을 구해주자
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    static int MAX = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] g = new int[MAX + 1];
        int[] count = new int[MAX + 1];

        for (int i = 0; i <= MAX; i++) {
            g[i] = i;
            count[i] = 1;
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            if (input.nextToken().equals("I")) isSame(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()), g, count);
            else output.append(count[find(Integer.parseInt(input.nextToken()), g)]).append("\n");
        }

        System.out.print(output);
    }

    public static void isSame(int a, int b, int[] g, int[] count) {
        int na = find(a, g);
        int nb = find(b, g);

        if (na == nb) return;

        g[Math.max(na, nb)] = Math.min(na, nb);
        count[Math.min(na, nb)] += count[Math.max(na, nb)];
    }

    public static int find(int a, int[] g) {
        if (g[a] == a) return a;
        return g[a] = find(g[a], g);
    }
}