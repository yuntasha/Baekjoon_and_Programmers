import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int A = Integer.parseInt(input.nextToken());
        int B = Integer.parseInt(input.nextToken());

        System.out.println(solution(A, B));
    }

    public static String solution(int A, int B) {
        List<Integer> result = new ArrayList<>();

        for (int i = -1000; i <= 1000; i++) {
            if (sik(A, B, i)) result.add(i);
        }

        StringBuilder output = new StringBuilder();

        output.append(result.get(0));

        if (result.size() == 2) {
            output.append(" ").append(result.get(1));
        }

        return output.toString();
    }

    public static boolean sik(int A, int B, int x) {
        return x * x + 2 * A * x + B == 0;
    }
}