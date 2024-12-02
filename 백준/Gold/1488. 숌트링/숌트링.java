import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(bf.readLine());

        var countA = Integer.parseInt(st.nextToken());
        var countB = Integer.parseInt(st.nextToken());
        var maxA = Integer.parseInt(st.nextToken());
        var maxB = Integer.parseInt(st.nextToken());

        System.out.println(solution(countA, countB, maxA, maxB));
    }

    static int solution(int countA, int countB, int maxA, int maxB) {

        if (maxA == 0 || countA == 0) return Math.min(countB, maxB);
        if (maxB == 0 || countB == 0) return Math.min(countA, maxA);

        if (countA < countB) {
            return countA + (countA+1 > countB/maxB ? countB : maxB * (countA + 1));
        } else {
            return countB + (countB+1 > countA/maxA ? countA : maxA * (countB + 1));
        }
    }
}