import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
그림을 걸 수 있는 방법
벽벽 빈벽 빈빈 벽빈
빈빈 빈벽 벽벽 벽빈
이런 느낌이여야함
아래로 봤을 때 현재 벽이면
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            char[] now = bf.readLine().toCharArray();

            for (int j = 0; j < M; j++) {
                map[i][j] = now[j] == 'X' ? 1 : 0;
            }
        }

        System.out.println(solution(N, M, map));
    }

    static int solution(int N, int M, int[][] map) {
        int result = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                if (map[i][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j] == 0 && map[i + 1][j + 1] == 0) {
                    result++;
                    j++;
                }
            }
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0 && map[i + 1][j] == 1 && map[i + 1][j + 1] == 1) {
                    result++;
                    j++;
                }
            }
        }

        for (int j = 0; j < M - 1; j++) {
            for (int i = 0; i < N - 1; i++) {
                if (map[i][j] == 0 && map[i][j + 1] == 1 && map[i + 1][j] == 0 && map[i + 1][j + 1] == 1) {
                    result++;
                    i++;
                }
            }
        }

        for (int j = 0; j < M - 1; j++) {
            for (int i = 0; i < N - 1; i++) {
                if (map[i][j] == 1 && map[i][j + 1] == 0 && map[i + 1][j] == 1 && map[i + 1][j + 1] == 0) {
                    result++;
                    i++;
                }
            }
        }

        return result;
    }
}