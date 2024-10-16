import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NW = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NW[0];
        var W = NW[1];

        var M = Double.parseDouble(bf.readLine());

        var p = new int[N][];

        for (int i=0; i<N; i++) {
            p[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        var d = new double[N][N];

        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                var now = cal(i, j, p);
                if (now>M) now = -1.0;
                d[i][j] = d[j][i] = now;
            }
        }
        
        for (int i=0; i<W; i++) {
            var now = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            d[now[0]-1][now[1]-1] = d[now[1]-1][now[0]-1] = 0.0;
        }

        System.out.println(solution(N, W, M, d));
    }


    static long solution(int N, int W, double M, double[][] d) {
        var visited = new boolean[N];
        visited[0] = true;
        for (int i=0; i<N-1; i++) {
            var min = -1;
            var minV = -1.0;
            for (int j=1; j<N; j++) {
                if (visited[j] || d[0][j] == -1) continue;
                if (minV != -1.0 && minV<d[0][j]) continue;
                minV = d[0][j];
                min = j;
            }
            if (min==-1) break;
            visited[min] = true;
            for (int k=1; k<N; k++) {
                if (d[min][k] == -1.0) continue;
                d[0][k] = d[0][k]==-1.0?d[0][min] + d[min][k]:Math.min(d[0][k], d[0][min] + d[min][k]);
            }
        }

        if (d[0][N-1]==-1.0) return -1;
        return (long)(d[0][N-1]*1000L);
    }

    static double cal(int a, int b, int[][] p) {
        return Math.sqrt(Math.pow(p[a][0]-p[b][0], 2) + Math.pow(p[a][1]-p[b][1], 2));
    }
}