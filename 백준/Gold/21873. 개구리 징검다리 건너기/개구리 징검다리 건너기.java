import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
1
12
123
123..n
123..n
123..n
23..n
3..n
 */

public class Main {

    static long cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    static String solution(int N) {
        int team = 1;

        StringBuilder result = new StringBuilder();

        for (int i = 1; i < N; i++) {
            result.append(move(team, 1, i));
            team ^= 3;
        }

        result.append(move(team, 1, N));
        team ^= 3;
        result.append(move(team, 1, N));
        team ^= 3;
        result.append(move(team, 1, N));
        team ^= 3;

        for (int i = 2; i <= N; i++) {
            result.append(move(team, i, N));
            team ^= 3;
        }

        return cnt + "\n" + result.toString();
    }

    static String move(int team, int start, int end) {
        StringBuilder result = new StringBuilder();

        for (int i = start; i <= end; i++) {
            result.append(team).append(" ").append(i).append("\n");
            cnt++;
        }

        return result.toString();
    }
}