import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;

/*
ㅋㅋㅋㅋ
1, 3, 6 이거 30만 전까지 쭉 구하자
그리고 DP로 밀고나가면 끝!
EASY
*/

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    static int solution(int N) {
        List<Integer> arr = findArr(N);

        int[] DP = IntStream.rangeClosed(0, N).toArray();

        for (int i=0; i<=N; i++) {
            for (int n : arr) {
                if (i+n > N) break;
                DP[i+n] = Math.min(DP[i+n], DP[i] + 1);
            }
        }

        return DP[N];
    }

    static List<Integer> findArr(int N) {
        List<Integer> result = new ArrayList<>();

        int now = 1;
        int add = 1;

        for (int i=2; now<=N; i++) {
            result.add(now);
            add+=i;
            now+=add;
        }

        return result;
    }
}