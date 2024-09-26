import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        var N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }



    static long solution(int N) {
        var results = new ArrayList<Long>();
        for (int i=0; i<10; i++) increase(i, results);
        results.sort(Comparator.naturalOrder());
        return results.size()>N-1?results.get(N-1):-1;
    }

    static void increase(long now, List<Long> results) {
        results.add(now);
        for (int i=0; i<now%10; i++) {
            increase(now*10+i, results);
        }
    }
}