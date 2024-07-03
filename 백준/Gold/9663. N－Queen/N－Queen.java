import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var p = new boolean[N+N-1];
        var m = new boolean[N+N-1];
        var l = new boolean[N];

        System.out.println(solution(N, p, m, l, 0));
    }


    static int solution(int N, boolean[] p, boolean[] m, boolean[] l, int now) {
        if (N == now) return 1;
        var result = 0;
        for (int i=0; i<N; i++){
            int mInt = now - i + N - 1;
            if (p[now+i] || m[mInt] || l[i]) continue;
            p[now+i] = m[mInt] = l[i] = true;
            result += solution(N, p, m, l, now+1);
            p[now+i] = m[mInt] = l[i] = false;
        }
        return result;
    }
}