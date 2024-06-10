import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var N = Integer.parseInt(bufferedReader.readLine());
        if (N==1) System.out.println(1);
        else if (N==2) System.out.println(3);
        else {
            var l = new int[N+1];
            l[0] = 0;
            l[1] = 1;
            l[2] = 3;
            for (int i = 3; i <= N; i++) {
                l[i] = (l[i-2]*2 + l[i-1])%10007;
            }
            System.out.println(l[N]);
        }
    }
}