import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int MAX_VALUE = 300_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] dist = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], MAX_VALUE);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            int v = Integer.parseInt(input.nextToken());

            if (a == b) continue;

            dist[a][b] = dist[b][a] = Math.min(v, dist[a][b]);
        }

        System.out.println(solution(N, M, dist));
    }

    public static int solution(int N, int M, int[][] dist) {
        for (int m = 1; m <= N; m++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    dist[s][e] = dist[e][s] = Math.min(dist[s][e], dist[s][m] + dist[m][e]);
                }
            }
        }

//        for (int[] a : dist) System.out.println(Arrays.toString(a));

        int min = Integer.MAX_VALUE;
        int result = 0;

        for (int i = 1; i <= N; i++) {
            int now = 0;
            for (int j = 1; j <= N; j++) now += dist[i][j];
            if (min > now) {
                min = now;
                result = i;
            }
        }

        return result;
    }
}