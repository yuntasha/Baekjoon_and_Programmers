import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        StringJoiner result = new StringJoiner("\n");

        for (int t=1; t<=T; t++) {
            result.add(String.format("Case #%d: %d", t, solution(Integer.parseInt(bf.readLine()), new StringTokenizer(bf.readLine()))));
        }

        System.out.println(result);
    }

    static int solution(int N, StringTokenizer st) {
        if (N == 1) return 0;
        if (N == 2) return Math.max(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        LinkedList<Integer> stk = new LinkedList<>();

        int result = 0;

        for (int i=0; i<N; i++) {
            int now = Integer.parseInt(st.nextToken());
            if (stk.isEmpty()) {
                stk.add(now);
                continue;
            }
            int prev = stk.removeLast();

            if (prev <= now) {
                while (!stk.isEmpty() && stk.peekLast() <= now) {
                    result += stk.removeLast();
                }
                result += now;
                stk.add(now);
                continue;
            }

            stk.add(prev);
            stk.add(now);
        }

        if (stk.size() > 1) {
            stk.removeLast();
            while (!stk.isEmpty()) {
                result += stk.removeLast();
            }
        }

        return result;
    }
}