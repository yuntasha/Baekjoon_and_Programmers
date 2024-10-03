import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = bf.readLine().toCharArray();

        var M = bf.readLine().toCharArray();

        System.out.println(solution(N, M));
    }

    static int solution(char[] N, char[] M) {
        var result = N.length+M.length;
        for (int i=0; i<N.length; i++) {
            var isPass = true;
            for (int j=0; j<M.length && i+j<N.length && isPass; j++) {
                if (M[j] == '2' && N[i+j] == '2') isPass = false;
            }

            if (isPass) result = Math.min(result, Math.max(N.length, i+M.length));
        }

        for (int i=0; i<M.length; i++) {
            var isPass = true;
            for (int j=0; j<N.length && i+j<M.length && isPass; j++) {
                if (N[j] == '2' && M[i+j] == '2') isPass = false;
            }

            if (isPass) result = Math.min(result, Math.max(M.length, i+N.length));
        }

        return result;
    }
}