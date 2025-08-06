/*
-180 < Math.atan <= 180이다
0, 1이 기준이다
180이라면 반시계


b - a < 180 -> 그대로

문제가 어디서 생기냐...

-178 - (178) 이런 느낌?
즉 180이 넘어가는 경우
-356 +360

사실상 4인데
그럼 360을 더해준다?

그럼 4가 나오긴함 ㅇㅇ

근데
160 - -(160)
이건 -40인가
320인데
-360해주면

그러니까
정리해보면
b - a < 180 => 그대로
 */

import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        StringBuilder output = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(bf.readLine());

            double[][] pos = new double[N][];

            for (int i = 0; i < N; i++) pos[i] = Arrays.stream(bf.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();

            output.append("\n").append(solution(N, pos));
        }

        System.out.println(output.substring(1));
    }

    public static int solution(int N, double[][] pos) {
        double result = 0;

        for (int i = 0; i < N - 1; i++) {
            result += getAngle(pos[i], pos[i + 1]);
        }

        return (int) Math.floor(Math.abs(result) / (Math.PI * 2));
    }

    public static double getAngle(double[] pos1, double[] pos2) {
        double result = Math.atan2(pos2[1], pos2[0]) - Math.atan2(pos1[1], pos1[0]);

        if (result == -Math.PI) return Math.PI;
        if (Math.abs(result) <= Math.PI) return result;
        if (result == 0) return 0;
        if (result > 0) return result - 2 * Math.PI;
        return result + 2 * Math.PI;
    }
}