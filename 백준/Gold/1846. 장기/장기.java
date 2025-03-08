import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*

 */

public class Main {

    static final String FAIL = "-1";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }

    static String solution(int N) {
        if (N == 3) {
            return "-1";
        }

        StringJoiner sj = new StringJoiner("\n");

        if ((N & 1) == 1) {
            sj.add("2").add("1").add(String.valueOf(N));
            for (int i = 3; i < N; i++) {
                sj.add(String.valueOf(i));
            }
        } else {
            for (int i = 2; i <= (N >> 1); i++) {
                sj.add(String.valueOf(i));
            }
            sj.add("1");
            for (int i = (N >> 1) + 2; i <= N; i++) {
                sj.add(String.valueOf(i));
            }
            sj.add(String.valueOf((N >> 1) + 1));
        }

        return sj.toString();
    }
}