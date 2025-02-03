import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
억까라고 생각해서 그냥 일단 완탐으로 다시 풂
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Double> angles = new ArrayList<>();

        for (int n=0; n<N; n++) {
            angles.add(Double.parseDouble(bf.readLine()));
        }

        System.out.println(solution(N, K, angles));
    }


    static int solution(int N, int K, List<Double> angles) {
        int result = Integer.MAX_VALUE;
        double p = 360f/(double) K;

        for (int i=0; i<N; i++) {
            int[] count = new int[K];
            for (int j=0; j<N; j++) {
                double d = getD(angles.get(i), angles.get(j));
                count[(int) (d/p)]++;
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int k=0; k<K; k++) {
                min = Math.min(min, count[k]);
                max = Math.max(max, count[k]);
            }
            result = Math.min(result, max - min);
        }

        return result;
    }

    static double getD(double a, double b) {
        return a <= b ? b - a : b - a + 360;
    }
}