import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static String solution(int N, int[] arr){
        var s = 0;
        var e = N-1;
        int rs=0;
        int re=0;
        int minV = Integer.MAX_VALUE;
        while (s<e){
            var now = arr[s] + arr[e];
            if (Math.abs(now)<minV) {
                minV = Math.abs(now);
                rs = s;
                re = e;
            }
            if (now==0) break;
            else if(now>0) e--;
            else s++;
        }
        return arr[rs] + " " + arr[re];
    }
}