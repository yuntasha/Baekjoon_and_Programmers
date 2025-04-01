import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    public static int solution(int N) {
        int result = 0;

        for (int a = 1; a <= 500; a++) {
            for (int b = 1; b < a; b++) {
                if (a * a == b * b + N) result++;
            }
        }

        return result;
    }
}