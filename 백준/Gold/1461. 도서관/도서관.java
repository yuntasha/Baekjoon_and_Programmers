import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var p = new ArrayList<Integer>();
        var m = new ArrayList<Integer>();

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i=0; i<N; i++) {
            var n = Integer.parseInt(st.nextToken());
            if (n>0) p.add(n);
            else m.add(-n);
        }

        System.out.println(solution(N, M, p, m));
    }

    static int solution(int N, int M, List<Integer> p, List<Integer> m) {

        p.sort(Comparator.reverseOrder());
        m.sort(Comparator.reverseOrder());

        var result = -Math.max(p.isEmpty()?0:p.get(0), m.isEmpty()?0:m.get(0));

        for (int i=0; i<p.size(); i+=M) {
            result += p.get(i)<<1;
        }

        for (int i=0; i<m.size(); i+=M) {
            result += m.get(i)<<1;
        }

        return result;
    }
}