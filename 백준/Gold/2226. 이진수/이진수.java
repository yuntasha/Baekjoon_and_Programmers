import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        System.out.print(solution(N));
    }

    public static String solution(int N) {
        BigInteger result = BigInteger.ZERO;

        for (int i = 2; i <= N; i++) {
            result = result.add(result);
            if ((i & 1) == 1) result = result.subtract(BigInteger.ONE);
            else result = result.add(BigInteger.ONE);
        }

        return result.toString();
    }
}