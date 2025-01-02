import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
음음
모든 점 비교해서 한 직선위에 있는지 확인
음음 비교하려면
1,000,000,000 이 걸리니까 3*2*1로 나눠도 애매한데?
직선을 만드는 방법 좋은거 없나
12 y차이 * 23 x차이 = 12 x차이 * 23 y차이
GCD로 확 빼버려? 근데 빼서 뭐함
일단 한 직선위에 있는 모든 애들 붙잡아서 구해야함
x증가량, y증가량으로 만들면 1_000_000이 나와버림
그냥 기울기 만들고 싹다 정렬해버려?
12 13이렇게 같은애들 있으면 되잖아

 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] points = new int[N][];

        for (int i=0; i<N; i++) {
            points[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, points));
    }

    public static int solution(int N, int[][] points) {
        int result = -1;
        for (int i=0; i<N; i++) {
            List<Double> l = new ArrayList<>();
            for (int j=i+1; j<N; j++) {
                l.add(getG(points[i], points[j]));
            }

            l.sort(Comparator.naturalOrder());
            int now = 2;
            for (int idx=0; idx<l.size()-1; idx++) {
                if (Objects.equals(l.get(idx), l.get(idx + 1))) now++;
                else {
                    if (now >= 3) result = Math.max(result, now);
                    now = 2;
                }
            }
            if (now >= 3) result = Math.max(result, now);
        }

        return result;
    }

    public static double getG(int[] a, int[] b) {
        if (a[0] == b[0]) {
            return 0.0;
        } else if (a[1] == b[1]){
            return Double.POSITIVE_INFINITY;
        } else if (a[0] > b[0]){
            return getSG(a, b);
        } else {
            return getSG(b, a);
        }
    }

    public static double getSG(int[] a, int[] b) {
        return (double) (a[0]-b[0]) / (double) (a[1]-b[1]);
    }
}