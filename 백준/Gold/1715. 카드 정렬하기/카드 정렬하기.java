import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i=0; i<N; i++) pq.add(Long.parseLong(bf.readLine()));

        System.out.println(solution(N, pq));
    }

    public static long solution(int N, PriorityQueue<Long> pq) {
        long result = 0L;

        while (pq.size() > 1) {
            long a = pq.remove();
            long b = pq.remove();

            result += a + b;
            pq.add(a+b);
        }
        
        return result;
    }
}