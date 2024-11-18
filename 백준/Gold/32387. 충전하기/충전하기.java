import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static TreeSet<Integer> set;
    static int[] log;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var T = NM[1];

        set = new TreeSet<Integer>();
        log = new int[N+1];

        for (int i=1; i<=N; i++) set.add(i);

        StringJoiner sb = new StringJoiner("\n");

        for (int t = 1; t <= T; t++) {
            var input = bf.readLine().split(" ");
            sb.add(String.valueOf(solution(t, Integer.parseInt(input[0]), Integer.parseInt(input[1]))));
        }

        System.out.println(sb.toString());
    }

    static int solution(int t, int type, int port) {
        if (type == 1) {
            Integer result = set.ceiling(port);

            if (Objects.isNull(result)) {
                return -1;
            }

            set.remove(result);
            log[result] = t;

            return result;
        } else {
            if (!set.contains(port)) {
                set.add(port);
                return log[port];
            }
            return -1;
        }
    }
}