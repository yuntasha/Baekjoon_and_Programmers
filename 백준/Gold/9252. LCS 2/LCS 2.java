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
        var c1 = s1.toCharArray();
        var c2 = s2.toCharArray();

        var dp = new int[c1.length+1][c2.length+1];


        for (int i=1; i<c1.length+1; i++){
            for (int j=1; j<c2.length+1; j++){
                dp[i][j] = c1[i-1]==c2[j-1]?dp[i-1][j-1]+1:Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        if (dp[c1.length][c2.length]==0) return String.valueOf(dp[c1.length][c2.length]);

        var result = new StringBuilder();

        var x1 = c1.length;
        var x2 = c2.length;

        while (dp[x1][x2]>0){
            if (dp[x1][x2]==dp[x1-1][x2]) x1--;
            else if (dp[x1][x2]==dp[x1][x2-1]) x2--;
            else {
                result.append(c1[x1-1]);
                x1--;
                x2--;
            }
        }

        return dp[c1.length][c2.length] + "\n" + result.reverse();
    }
}