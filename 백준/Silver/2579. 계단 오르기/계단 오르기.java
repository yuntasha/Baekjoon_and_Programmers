import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] dp = new int[N];
        int[] score = new int[N];
        for (int i=0; i<N; i++){
            score[i] = Integer.parseInt(bufferedReader.readLine());
        }

        if (N==1){
            System.out.println(score[0]);
            return;
        }
        dp[0] = score[0];
        dp[1] = score[0]+score[1];
        if (N==2){
            System.out.println(dp[1]);
            return;
        }
        dp[2] = Math.max(score[0], score[1])+score[2];

        for (int i=3; i<N; i++){
            dp[i] = Math.max(dp[i-2], dp[i-3]+score[i-1])+score[i];
        }

        System.out.println(dp[N-1]);
    }
}