import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static final long V = 1_000_000_007L;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var mo = 1L;
        var ja = 0L;

        for (int i=0; i<N; i++){
            var now = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ja = (((ja * now[0])%V) + ((now[1] * mo) % V)) % V;
            mo = (now[0] * mo) % V;
        }

        System.out.println(solution(ja, mo));
    }

    static long solution(long ja, long mo){
        var n = gcd(ja, mo);
        ja/=n;
        mo/=n;

        return (jg(mo, V-2) * ja)%V;
    }

    static long jg(long mo, long n){
        if (n==1) return (mo)%V;
        else {
            var prev = jg(mo, n/2);
            var result = (prev * prev)%V;
            if (n%2==1) result=(result*mo)%V;
            return result;
        }
    }


    static long gcd (long a, long b){
        if (b == 0) return a;
        else return gcd(b, a%b);
    }
}