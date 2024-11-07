import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] DP = new int[100];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var T = Integer.parseInt(bf.readLine());

        init();

        for (int t = 0; t < T; t++) {
            System.out.println(solution(bf.readLine()));
        }
    }

    static int solution(String s) {
        var result = 0;
        var now = 0;



        if (s.length() % 2 == 1) {
            var n = Integer.parseInt(s.substring(0, 1));
            result += DP[n];
            now++;
        }

        while (now < s.length()) {
            var n = Integer.parseInt(s.substring(now, now + 2));
            result += DP[n];
            now += 2;
        }

        return result;
    }

    static void init() {
        Arrays.fill(DP, 100);
        DP[0] = 0;

        for (int i=0; i<100; i++) {
            if (i+10<100) {
                DP[i + 10] = Math.min(DP[i] + 1, DP[i + 10]);
            }
            if (i+1<100) {
                DP[i + 1] = Math.min(DP[i] + 1, DP[i + 1]);
            }
            if (i+25<100) {
                DP[i + 25] = Math.min(DP[i] + 1, DP[i + 25]);
            }
        }
    }
}