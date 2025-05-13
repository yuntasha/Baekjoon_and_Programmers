import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
그냥 큰 애들부터 차례대로 하나씩 사용해서 서로 엮어보자
24bytes * 2000
48000 bytes
이거 얼마 안하는데
 */

public class Main {

    static String FAIL = "-1";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, M, arr));
    }

    static int solution(int N, int M, int[] arr) {
        int result = 0;

        int s = 0;
        int e = 1;

        int now = arr[0];

        while (e < N) {
            if (now == M) {
                result++;
                now += arr[e++];
                now -= arr[s++];
            } else if (now < M) {
                now += arr[e++];
            } else {
                now -= arr[s++];
            }
        }

        while (s < N && now >= M) {
            if (now == M) {
                result++;
            }
            now -= arr[s++];
        }

        return result;
    }
}