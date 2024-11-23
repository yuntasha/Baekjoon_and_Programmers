import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var s = bf.readLine().split("");

        System.out.println(solution(s));
    }

    static String solution(String[] s) {
        StringBuilder result = new StringBuilder(s[0]);

        for (int i=1; i<s.length; i++) {
            if (result.substring(i-1, i).compareTo(s[i]) < 0) {
                result.insert(0, s[i]);
            } else {
                result.append(s[i]);
            }
        }

        return result.reverse().toString();
    }
}