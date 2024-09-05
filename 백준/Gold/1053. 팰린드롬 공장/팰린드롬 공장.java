import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] DP;
    static char[] S;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        S = bufferedReader.readLine().strip().toCharArray();

        DP = new int[S.length][S.length];

        System.out.println(solution());
    }

    static int solution(){
        reset();
        var result = find(0, S.length-1);
        for (int i=0; i<S.length-1; i++){
            for (int j=i+1; j<S.length; j++){
                if (S[i]==S[j]) continue;
                reset();
                swap(i, j);
                result = Math.min(result, find(0, S.length-1)+1);
                swap(i, j);
            }
        }
        return result;
    }

    static int find(int left, int right){
        if (left>=right) return 0;
        if (DP[left][right]==-1) DP[left][right] = min(
                find(left, right-1)+1,
                find(left+1, right)+1,
                find(left+1, right-1)+(S[left]==S[right]?0:1));
        return DP[left][right];
    }

    static void swap(int a, int b){
        var temp = S[a];
        S[a] = S[b];
        S[b] = temp;
    }

    static void reset(){
        for (int i=0; i<S.length; i++) Arrays.fill(DP[i], -1);
    }

    static int min(int a, int b, int c){
        return Math.min(Math.min(a, b), c);
    }
}