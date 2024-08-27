import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var T = Integer.parseInt(bufferedReader.readLine());

        for (int i=0; i<T; i++){
            System.out.println(solution(bufferedReader.readLine().strip().toCharArray())?"YES":"NO");
        }
    }

    static boolean solution(char[] arr){
        var N = arr.length;
        var dp = new boolean[N];
        for (int i=0; i<N-1; i++) {
            if (i>0 && !dp[i-1]) continue;
            if (arr[i]=='0') {
                if (arr[i+1]=='1') dp[i+1] = true;
            } else {
                if (arr[i+1]=='1') continue;
                var now = i+2;
                while (now<N && arr[now++]=='0');
                now--;
                while (now<N && now>i+2 && arr[now]=='1') {
                    dp[now++] = true;
                }
            }
        }
        return dp[N-1];
    }
}