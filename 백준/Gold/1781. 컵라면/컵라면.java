import java.io.*;
import java.util.*;

/*
그리디 느낌인데
데드라인 순서로 받아
그러고 넘어가면 기존에 있는 가장 작은 것과 비교 가장 작은 것보다 작으면 ~~
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        PriorityQueue<Cup> pq = new PriorityQueue<>(Comparator.comparingInt(Cup::getT));

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            pq.add(new Cup(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken())));
        }

        System.out.println(solution(N, pq));
    }

    static int solution(int N, PriorityQueue<Cup> pq) {
        PriorityQueue<Cup> solve = new PriorityQueue<>(Comparator.comparingInt(Cup::getC));

        while (!pq.isEmpty()) {
            Cup now = pq.remove();

            if (now.t > solve.size()) {
                solve.add(now);
                continue;
            }
            if (solve.peek().c < now.c) {
                solve.add(now);
                solve.remove();
            }
        }

        int result = 0;
        for (Cup c : solve) result += c.c;
        return result;
    }

    static class Cup {
        int t;
        int c;

        public Cup(int t, int c) {
            this.t = t;
            this.c = c;
        }

        public int getT() {
            return t;
        }

        public int getC() {
            return c;
        }
    }
}