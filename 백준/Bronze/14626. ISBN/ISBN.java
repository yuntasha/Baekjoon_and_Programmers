import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(solution(bf.readLine()));
    }

    public static int solution(String s) {
        int idx = s.indexOf('*');

        char[] cc = s.toCharArray();

        int result = 0;

        for (int i = 0; i < 10; i++) {
            if (isOK(i, idx, cc)) result = i;
        }
        return result;
    }

    public static boolean isOK(int n, int idx, char[] cc) {
        int result = 0;

        cc[idx] = (char) ('0' + n);

        for (int i = 0; i < 13; i++) {
            int a = cc[i] & 15;
            result += a;
            if ((i & 1) == 1) result += a << 1;
        }

        return result % 10 == 0;
    }
}
