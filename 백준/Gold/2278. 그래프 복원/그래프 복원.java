/*
일단 플로이드 워셜 반대로 가보자
그리고 N(N-1)/2개만큼 현재 존재하고
M - 1로해서 그 전까지 다 지워버려
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] dist = new int[N][N];

        for (int i = 0; i < N - 1; i++) {
            input = new StringTokenizer(bf.readLine());
            for (int j = i + 1; j < N; j++) {
                dist[i][j] = dist[j][i] = Integer.parseInt(input.nextToken());
            }
        }

        System.out.print(solution(N, M, dist));
    }

    public static String solution(int N, int M, int[][] dist) {
        List<Line> result = new ArrayList<>();

        int del = N * (N - 1) / 2 - M;

        Loop : for (int m = 0; m < N; m++) {
            for (int s = 0; s < N; s++) {
                if (dist[s][m] == 0) continue;
                for (int e = 0; e < N; e++) {
                    if (dist[s][e] == 0 || dist[m][e] == 0) continue;
                    if (dist[s][e] == dist[s][m] + dist[m][e]) {
                        del--;
                        dist[s][e] = dist[e][s] = 0;
                        if (del == 0) break Loop;
                    } else if (dist[s][e] > dist[s][m] + dist[m][e]) return "0";
                }
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (dist[i][j] != 0) result.add(new Line(i + 1, j + 1, dist[i][j]));
            }
        }

        if (result.size() != M) return "0";

        return "1\n" + result.stream().map(Line::toString).collect(Collectors.joining("\n"));
    }

    public static class Line {
        int a;
        int b;
        int d;

        public Line(int a, int b, int d) {
            this.a = a;
            this.b = b;
            this.d = d;
        }

        @Override
        public String toString() {
            return a + " " + b + " " + d;
        }
    }
}