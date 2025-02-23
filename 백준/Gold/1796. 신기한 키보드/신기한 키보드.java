import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
각 자리에서 다른 애들까지의 거리를 구함
각 알파벳 시작, 끝, 움직이는 개수 구하기
그리고 a부터 차근차근 더해가자
시작
끝
개수
같으면 문제가 생겨버림...
dp를 사용해서 구해버리자
0에서 끝으로 가는 경우 - 앞 뒤로 가장 빠른 애들 찾아서 넣어버리기
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        char[] input = bf.readLine().toCharArray();

        System.out.println(solution(input));
    }

    static int solution(char[] input) {
        int[] start = new int[26];
        int[] end = new int[26];
        int[] count = new int[26];

        for (int i = 0; i < input.length; i++) {
            int now = input[i] - 'a';
            if (count[now] == 0) {
                start[now] = i;
                end[now] = i;
            }
            count[now] += i - end[now] + 1;
            end[now] = i;
        }

        int[] dp = new int[2];
        int[] idx = new int[2];
        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) continue;
            int[] nextDp = new int[2];
            nextDp[0] = Math.min(Math.abs(idx[0] - start[i]) + dp[0], Math.abs(idx[1] - start[i]) + dp[1]) + count[i];
            nextDp[1] = Math.min(Math.abs(idx[0] - end[i]) + dp[0], Math.abs(idx[1] - end[i]) + dp[1]) + count[i];
            idx[0] = end[i];
            idx[1] = start[i];
            dp = nextDp;
        }

        return Math.min(dp[0], dp[1]);
    }
}
