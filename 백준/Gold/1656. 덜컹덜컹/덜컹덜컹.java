import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/*
t의 나누기 2함 짝수라면 -1 홀수라면 그냥 ㄱㄱ
그렇게 나온 값이 땅에 닿아야하는 다리중 최대 거리임
이분 탐색으로 그냥 찾자
*/

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N;

        StringJoiner sj = new StringJoiner("\n\n");

        while ((N = readInt()) > 0) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            int[] legs = new int[N];

            for (int i=0; i<N; i++) {
                legs[i] = readInt();
                min = Math.min(min, legs[i]);
                max = Math.max(max, legs[i]);
            }
            sj.add(solution(N, legs, min, max));
        }

        System.out.println(sj);
    }

    static String solution(int N, int[] legs, int min, int max) {
        if (min == max) return "0";

        int result;

        if ((result = chk(N, legs, max)) >= 0) return String.valueOf(result);

        while (min + 1 < max) {
            int mid = (min + max) / 2;

            if (chk(N, legs, mid) == -1) {
                max = mid;
            } else {
                min = mid;
            }
        }

        return String.valueOf(chk(N, legs, min));
    }

    static int chk(int N, int[] legs, int n) {
        List<Integer> possible = new ArrayList<>();

        int result = 0;

        for (int i=0; i<N; i++) {
            if (legs[i] >= n) {
                result += legs[i] - n;
                possible.add(i);
            }
        }

        if (possible.size() < 3) return -1;

        possible.add(N + possible.get(0));

        int max = N/2;
        if ((N&1) == 0) {
            max--;
        }

        for (int i=1; i<possible.size(); i++) {
            if (possible.get(i) - possible.get(i-1) > max) return -1;
        }

        return result;
    }

    static int readInt() throws IOException {
        int n;
        int c;
        while ((n = System.in.read()) < '0');
        n = n&15;
        while ((c = System.in.read()) >= '0') {
            n = (n<<3) + (n<<1) + (c&15);
        }
        return n;
    }
}