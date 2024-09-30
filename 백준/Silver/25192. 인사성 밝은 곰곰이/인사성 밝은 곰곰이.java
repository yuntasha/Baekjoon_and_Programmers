import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var result = 0;
        var cnt = new HashSet<String>();

        for (int i=0; i<N; i++) {
            var now = bf.readLine();
            if (now.equals("ENTER")) {
                result += cnt.size();
                cnt.clear();
            } else {
                cnt.add(now);
            }
        }
        result += cnt.size();
        System.out.println(result);
    }
}