import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[9][9];

        for (int i = 0; i < 9; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 9; j++) map[i][j] = Integer.parseInt(input.nextToken());
        }

        System.out.println(solution(map));
    }

    public static String solution(int[][] map) throws IOException {
        if (!find(0, findPoints(map), map)) {
            throw new IOException();
        }

        return Arrays.stream(map).map(arr -> Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" "))).collect(Collectors.joining("\n"));
    }

    public static boolean find(int n, List<Point> points, int[][] map) {
        if (n == points.size()) return true;

        Point p = points.get(n);

        for (int i = 1; i < 10; i++) {
            map[p.x][p.y] = i;
            if (checkArea(p.x, p.y, map) && checkLine(p.x, p.y, map) && find(n + 1, points, map)) return true;
            map[p.x][p.y] = 0;
        }

        return false;
    }

    public static List<Point> findPoints(int[][] map) {
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j] == 0) points.add(new Point(i, j));
            }
        }

        return points;
    }

    public static boolean checkArea(int x, int y, int[][] map) {
        boolean[] visited = new boolean[10];

        int sx = x - (x % 3);
        int sy = y - (y % 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[sx + i][sy + j] == 0) continue;
                if (visited[map[sx + i][sy + j]]) return false;
                visited[map[sx + i][sy + j]] = true;
            }
        }

        return true;
    }

    public static boolean checkLine(int x, int y, int[][] map) {
        boolean[] visitedX = new boolean[10];
        boolean[] visitedY = new boolean[10];

        for (int i = 0; i < 9; i++) {
            if (map[i][y] != 0) {
                if (visitedX[map[i][y]]) return false;
                visitedX[map[i][y]] = true;
            }

            if (map[x][i] != 0) {
                if (visitedY[map[x][i]]) return false;
                visitedY[map[x][i]] = true;
            }
        }

        return true;
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}