import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var M = Integer.parseInt(bf.readLine());

        var n = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        var a = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        System.out.println(solution(n, a));
    }

    static String solution(long[] n, long[] a) {
        var result = new StringJoiner(" ");

        for (int i=0; i<n.length; i++) {
            result.add(String.valueOf(find(n[i], a[i])));
        }

        return result.toString();
    }

    static long find(long n, long a) {
        if (n + 1 >= a) {
            return Math.min(a, n);
        }
        var result = n;
        a -= n + 1;
        result += a/n*(n-1);
        result += a%n;

        return result;
    }
}