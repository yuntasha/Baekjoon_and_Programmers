import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var last = NM[0];
        var now = NM[1];

        var cnt = 1;

        while (now != last && now!=0) {
            if (now % 2 == 0) {
                now /= 2;
                cnt++;
            } else if (now % 10 == 1) {
                now /= 10;
                cnt++;
            } else break;
        }

        System.out.println(now == last ? cnt : -1);
    }
}