import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var XYDT = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var X = XYDT[0];
        var Y = XYDT[1];
        var D = XYDT[2];
        var T = XYDT[3];

        System.out.println(solution(X, Y, D, T));
    }

    static double solution(int X, int Y, int D, int T){
        var Z = Math.sqrt(X*X+Y*Y);
        double result = Z;

        var cnt = (int)Z/D;
//        System.out.println("Z = " + Z);
//        System.out.println("Math.max(2, cnt)*T = " + Math.max(2, cnt*D==Z?cnt:cnt+1)*T);
//        System.out.println("(cnt*T + (Z-(cnt*D))) = " + (cnt*T + (Z-(cnt*D))));
//        System.out.println("(cnt*T + (Z-(cnt*D))) = " + ((cnt+1)*T + (((cnt+1)*D)-Z)));
        result = Math.min(result, Math.max(2, cnt*D==Z?cnt:cnt+1)*T);
        result = Math.min(result, cnt*T + (Z-(cnt*D)));
        result = Math.min(result, (cnt+1)*T + (((cnt+1)*D)-Z));
        return result;
    }

}