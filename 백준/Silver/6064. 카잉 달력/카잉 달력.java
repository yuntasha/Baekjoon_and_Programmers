import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        for (int i=0; i<N; i++){
            System.out.println(solution(bufferedReader.readLine()));
        }
    }

    private static int solution(String cmd){
        var con = getInt(cmd);

        int M = con[0];
        int N = con[1];
        int x = con[2];
        int y = con[3];


        while (x!=y){
            if (x>y) y+=N;
            else x+=M;
            if (x-y==con[2]-con[3]) break;
        }
        return x==y?x:-1;
    }

    private static int[] getInt(String cmd){
        return Arrays.stream(cmd.strip().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}