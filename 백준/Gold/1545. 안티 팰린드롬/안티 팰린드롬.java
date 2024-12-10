import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        char[] S = bf.readLine().toCharArray();

        System.out.println(solution(S));
    }

    static String solution(char[] S) {
        Arrays.sort(S);

        StringBuilder result = new StringBuilder();

        for (int i=0; i<S.length/2 + (S.length&1); i++) {
            result.append(S[i]);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i=S.length/2 + (S.length&1); i<S.length; i++) {
            pq.add((int) S[i]);
        }

        int half = S.length/2 - 1;

        for (int i=S.length/2-1; i>=0; i--) {
            if (i==S.length-i-1) continue;
            LinkedList<Integer> q = new LinkedList<Integer>();
            while (!pq.isEmpty() && pq.peek() == S[i]) {
                q.add(pq.remove());
            }

            if (pq.isEmpty()) return "-1";
            result.append((char) (int) pq.remove());
            while (!q.isEmpty()) pq.add(q.remove());
        }

        return result.toString();
    }
}