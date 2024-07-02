import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var s1 = bufferedReader.readLine().strip();
        var s2 = bufferedReader.readLine().strip();

        System.out.println(solution(s1, s2));
    }

    static int solution(String s1, String s2){
        var crr1 = s1.toCharArray();
        var crr2 = s2.toCharArray();

        var dp = new int[crr1.length][crr2.length];

        for (int i=0; i<crr1.length; i++){
            for (int j=0; j<crr2.length; j++){
                if (i==0) {
                    if (crr1[i] == crr2[j] || (j!=0 && dp[i][j-1]==1)) dp[i][j] = 1;
                    else dp[i][j] = 0;
                } else {
                    if (crr1[i] == crr2[j]) {
                        if (j==0) dp[i][j] = 1;
                        else dp[i][j] = Math.max(dp[i-1][j-1] + 1, dp[i-1][j]);
                    } else {
                        if (j==0) dp[i][j] = dp[i-1][j];
                        else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
        }

        return dp[crr1.length-1][crr2.length-1];
    }
}