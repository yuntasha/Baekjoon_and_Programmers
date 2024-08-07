import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var s1 = bufferedReader.readLine().strip();
        var s2 = bufferedReader.readLine().strip();

        System.out.println(solution(s1, s2));
    }

    static String solution(String s1, String s2){
        List<Integer>[] where = new ArrayList[26];
        for (int i=0; i<26; i++) {
            where[i] = new ArrayList<>();
        }
        for (int i=0; i<s1.length(); i++) {
            where[s1.charAt(i)-'A'].add(i);
        }
        var dp = new String[s1.length()];

        Arrays.fill(dp, "");

        for (int i=0; i<s2.length(); i++){
            var now = where[s2.charAt(i)-'A'];
            var idx=0;
            var maxString = "";
            for (int j=0; j<s1.length() && idx< now.size(); j++){
                if (now.get(idx)==j) {
                    if (maxString.length()+1 >dp[j].length()) dp[j] = maxString+s2.charAt(i);
                    else maxString=dp[j];
                    idx++;
                }
                else if (maxString.length() < dp[j].length()) maxString=dp[j];
            }
        }

        var result = "";
        for (String s: dp){
            if (result.length()<s.length()) result = s;
        }
        return result.length() + (result.isBlank()?"":"\n"+result);
    }
}
