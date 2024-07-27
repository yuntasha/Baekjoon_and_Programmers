import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        System.out.println(solution(N));
    }

    static int solution(int N){
        var s = Integer.toBinaryString(N).toCharArray();
        var result = 0;
        for (char c: s){
            if (c=='1') result++;
        }
        return result;
    }
}