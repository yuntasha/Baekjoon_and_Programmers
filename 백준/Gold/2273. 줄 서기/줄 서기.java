/*
플로이드 워셜처럼 만들어버리자
어떤데?
나보다 작다? -1
나보다 크다? 1
모릉다? 0
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

        int[][] compares = new int[N][N];

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(input.nextToken()) - 1;
            int b = Integer.parseInt(input.nextToken()) - 1;

            if (compares[a][b] == -1) {
                System.out.println("-1");
                return;
            }

            compares[a][b] = 1;
            compares[b][a] = -1;
        }

        System.out.print(solution(N, M, compares));
    }

    public static String solution(int N, int M, int[][] compares) {
        for (int m = 0; m < N; m++) {
            for (int s = 0; s < N; s++) {
                if (compares[s][m] == 0) continue;
                for (int e = 0; e < N; e++) {
                    if (compares[m][e] == 0) continue;
                    if (compares[s][m] == compares[m][e]) {
                        if (compares[s][e] == 0) compares[s][e] = compares[s][m];
                        if (compares[s][e] != compares[s][m]) return "-1";
                    }
                }
            }
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int smaller = 0;
            int bigger = 0;

            for (int j = 0; j < N; j++) {
                if (compares[i][j] == -1) smaller++;
                if (compares[i][j] == 1) bigger++;
            }

            result.add((smaller + 1) + " " + (N - bigger));
        }

        return result.stream().collect(Collectors.joining("\n"));
    }
}