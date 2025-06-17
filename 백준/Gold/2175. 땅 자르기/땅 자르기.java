/*
그럼 최대가 5각형임
?
깊게 생각할 필요가 있나?
8 * 7 = 56
시간은 짧다
8개를 나열
일단 전체 넓이를 구해
그리고 처음 시작점부터 7개까지 하나씩 더해가면서 넘지않는 최소값찾기
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static final int MAX = 20_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        double[][] points = new double[4][2];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                points[i][j] = Double.parseDouble(input.nextToken());
            }
        }

        System.out.println(solution(points));
    }

    static String solution(double[][] points) {
        double result = 0;
        double total = getR(points);
        points = getPoints(points);

        for (int i = 0; i < 8; i++) {
            double now = 0;
            for (int j = 0; j < 7; j++) {
                now += getRound(points[i], points[(i + j) & 7], points[(i + j + 1) & 7]);
                if (now > (total / 2)) break;
                result = Math.max(result, now);
            }
        }

        return result + " " + (total - result);
    }

    static double[][] getPoints(double[][] points) {
        double[][] result = new double[8][2];

        for (int i = 0; i < 8; i++) {
            if ((i & 1) == 0) result[i] = points[i >> 1];
            else {
                int n = i >> 1;
                result[i][0] = (points[n][0] + points[(n + 1) & 3][0]) / 2;
                result[i][1] = (points[n][1] + points[(n + 1) & 3][1]) / 2;
            }
        }

        return result;
    }

    static double getR(double[]... p) {
        double result = 0;
        for (int i = 1; i < p.length - 1; i++) {
            result += getRound(p[0], p[i], p[i + 1]);
        }
        return result;
    }

    static double getRound(double[] p1, double[] p2, double[] p3) {
        return Math.abs((p2[0] - p1[0]) * (p3[1] - p1[1]) - (p3[0] - p1[0]) * (p2[1] - p1[1])) / 2;
    }
}