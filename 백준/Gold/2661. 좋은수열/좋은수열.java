import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static String[] S = {"1", "2", "3"};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    public static String solution(int N) {
        return find("", N);
    }

    public static String find(String now, int N) {
        if (now.length() == N) return now;

        Loop:
        for (String s : S) {
            String next = now + s;

            for (int i = next.length() - 2; i >= 0; i -= 2) {
                if (next.substring(i, (i + next.length()) >> 1).equals(next.substring((i + next.length()) >> 1))) continue Loop;
            }

            String result = find(next, N);
            if (result != null) return result;
        }

        return null;
    }
}