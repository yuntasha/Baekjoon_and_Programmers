import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[3][];

        map[0] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        map[1] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        map[2] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(map));
    }

    public static int solution(int[][] map) {
        return find(0, 0, new int[3][3], map);
    }

    public static int find(int n, int bits, int[][] now, int[][] map) {
        if (n == 9) {
            if (!isOK(now)) return Integer.MAX_VALUE;
            return getDiff(now, map);
        }

        int result = Integer.MAX_VALUE;

        for (int i = 1; i < 10; i++) {
            if ((bits & (1 << i)) > 0) continue;
            now[n / 3][n % 3] = i;
            result = Math.min(result, find(n + 1, bits | (1 << i), now, map));
        }

        return result;
    }

    public static boolean isOK(int[][] now) {
        int n = now[0][0] + now[0][1] + now[0][2];
        boolean result = true;

        result &= n == now[1][0] + now[1][1] + now[1][2];
        result &= n == now[2][0] + now[2][1] + now[2][2];

        result &= n == now[0][0] + now[1][0] + now[2][0];
        result &= n == now[0][1] + now[1][1] + now[2][1];
        result &= n == now[0][2] + now[1][2] + now[2][2];

        result &= n == now[0][0] + now[1][1] + now[2][2];
        result &= n == now[0][2] + now[1][1] + now[2][0];

        return result;
    }

    public static int getDiff(int[][] now, int[][] map) {
        int result = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result += Math.abs(now[i][j] - map[i][j]);
            }
        }

        return result;
    }
}