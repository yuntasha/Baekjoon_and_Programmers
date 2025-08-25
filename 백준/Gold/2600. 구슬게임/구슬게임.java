import java.io.*;
import java.util.*;

public class Main {

    static final int DEFEAT = 1;
    static final int WIN = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] b = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int[] k = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            output.append(solution(k, b) ? "A" : "B").append("\n");
        }


        System.out.print(output.toString());
    }

    public static boolean solution(int[] k, int[] b) {
        return find(k[0], k[1], b, new int[k[0] + 1][k[1] + 1]);
    }

    public static boolean find(int A, int B, int[] ps, int[][] dp) {
        if (dp[A][B] > 0) return dp[A][B] == WIN;

        boolean result = false;

        for (int p : ps) {
            if (p <= A) result |= !find(A - p, B, ps, dp);
            if (p <= B) result |= !find(A, B - p, ps, dp);
        }

        dp[A][B] = DEFEAT;
        if (result) dp[A][B] = WIN;

        return dp[A][B] == WIN;
    }
}