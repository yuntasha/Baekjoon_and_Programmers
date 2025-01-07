import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
이 문제는 초깃값 설정을 잘해야한다는 소리임
따라서 가능한 범위를 계속해서 줄여나가다가 불가능해질때 그때 초깃값을 정하는 것
초깃값 설정 방법
최소 = max(이전 최소, 현재 최소)
최대 = min(이전 최대, 현재 최대)
그러다가 최소 > 최대인 경우
이전 최소 > 현재 최대라면 이전 최소가 해당 값
이전 최대 < 현재 최소라면 아전 최대가 해당 값
만약 끝까지 가면 그냥 최대 붙잡고 끝까지 하자
*/

public class Main {

    static int MAX = 100_000;
    static int MIN = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] ranges = new int[N][2];

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            ranges[i][0] = Integer.parseInt(st.nextToken());
            ranges[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, ranges));
    }

    static String solution(int N, int[][] ranges) {
        int now = findInit(N, ranges);

        int[] arr = new int[N];
        int result = 0;

        for (int i=0; i<N; i++) {
            if (now < ranges[i][0]) {
                result += Math.abs(now - ranges[i][0]);
                now = ranges[i][0];
            } else if (now > ranges[i][1]) {
                result += Math.abs(now - ranges[i][1]);
                now = ranges[i][1];
            }
            arr[i] = now;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result).append('\n');
        sb.append(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining("\n")));

        return sb.toString();
    }

    static int findInit(int N, int[][] ranges) {
        int max = MAX;
        int min = MIN;
        int prevMax = MAX;
        int prevMin = MIN;

        for (int i=0; i<N; i++) {
            min = Math.max(prevMin, ranges[i][0]);
            max = Math.min(prevMax, ranges[i][1]);

            if (min > max) {
                if (prevMin > max) return prevMin;
                if (prevMax < min) return prevMax;
                throw new IllegalArgumentException();
            }

            prevMin = min;
            prevMax = max;
        }

        return min;
    }
}