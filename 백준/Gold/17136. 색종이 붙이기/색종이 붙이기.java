/*
각 지점에서 1, 2, 3, 4, 5로 각각 존재하는지 확인한 다음에 덮어버리기
10 * 10이니까
5 * 5로 최대 4개까지 덮는게 가능함
0이면 안되니까 처음부터 탐색해서 1, 2,~~로 해보자

성공이면 정상적인 값
실패하면 MAX값 넣어줄까?
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int MAX = 50;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[10][10];

        for (int i = 0; i < 10; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }


        System.out.println(solution(map));
    }

    public static int solution(int[][] map) {
        int result = find(0, 0, map, new int[]{0, 5, 5, 5, 5, 5});
        return result == MAX ? -1 : result;
    }

    public static int find(int start, int count, int[][] map, int[] papers) {
        int idx = start;

        while (idx < 100) {
            if (map[idx / 10][idx % 10] == 1) break;
            idx++;
        }

        if (idx >= 100) {
            return count;
        }

        int x = idx / 10;
        int y = idx % 10;

        int result = MAX;

        for (int p = 1; p <= 5; p++) {
            if (papers[p] == 0) continue;
            if (!isOK(p, x, y, map)) break;
            papers[p]--;
            onPaper(p, x, y, map);
            result = Math.min(result, find(idx + 1, count + 1, map, papers));
            offPaper(p, x, y, map);
            papers[p]++;
        }

        return result;
    }

    public static boolean isOK(int n, int x, int y, int[][] map) {
        if (x + n > 10 || y + n > 10) return false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[x + i][y + j] == 0) return false;
            }
        }


        return true;
    }

    public static void onPaper(int n, int x, int y, int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[x + i][y + j] = 0;
            }
        }
    }

    public static void offPaper(int n, int x, int y, int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[x + i][y + j] = 1;
            }
        }
    }
}