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
        int result = 0;
        int prev = 0;
        int now = Integer.parseInt(st.nextToken());
        for (int i=1; i<N; i++) {
            prev = now;
            now = Integer.parseInt(st.nextToken());
            result += Math.max(prev, now);
        }
        return result;
    }
}