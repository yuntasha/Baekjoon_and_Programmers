import java.io.*;
import java.util.*;

/*
오른쪽으로 갈때 누적합
왼쪽으로 갈 때 누적합
음음
오른쪽으로 갈 때 누적합
왼쪽으로 갈 때 누적합
1 2 3 4 5 1
 1 2 3 4 5

1 3
0 3
됨

3 1
3
0   1   2   3   4   5
0   1   3   6   10  15
3 1

왼쪽 오른쪽 값을 가지고 하면 안되나?
s인덱스 하나
e인덱스 하나
초깃값 설정
s가 0일때 오른쪽 왼쪽비교해서 가장 작은 위치를 찾기
이제 한칸 더 갔을때 만약 왼쪽 오른쪽이랑 서로 크로스가 되는 경우 그때 비교한다

 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr) {
        int result = 0;

        int s = 0;
        int e = 1;
        int l = arr[0];
        int r = -arr[0];

        for (int i : arr) {
            r += i;
        }

        while (s < N) {
            while (isNext(getNext(e, N), l, r, arr)) {
                l += arr[e];
                r -= arr[e];
                e = getNext(e, N);
            }
//            System.out.println("s = " + s);
//            System.out.println(l + " " + r);
            result = Math.max(result, Math.min(l, r));
            l -= arr[s];
            r += arr[s];
            s++;
        }

        return result;
    }

    static int getNext(int now, int N) {
        return (now + 1) % N;
    }

    // 뒤에 값이 최솟값이 커지면 다음것으로 넘어가도 괜찮다는 이야기
    static boolean isNext(int now, int l, int r, int[] arr) {
        return Math.min(l, r) < Math.min(l + arr[now], r - arr[now]);
    }
}