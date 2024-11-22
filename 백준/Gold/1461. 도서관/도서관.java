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

        var arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, M, arr));
    }

    static int solution(int N, int M, int[] arr) {
        var result = 0;
        var p = new PriorityQueue<Integer>(Comparator.reverseOrder());
        var m = new PriorityQueue<Integer>(Comparator.reverseOrder());

        for (int i=0; i<N; i++) {
            if (arr[i]>0) p.add(arr[i]);
            else m.add(-arr[i]);
        }

        if (p.isEmpty()) {
            result += m.remove();
            for (int i=1; i<M && !m.isEmpty(); i++) m.remove();
        } else if (m.isEmpty()) {
            result += p.remove();
            for (int i=1; i<M && !p.isEmpty(); i++) p.remove();
        } else if (p.peek() > m.peek()) {
            result += p.remove();
            for (int i=1; i<M && !p.isEmpty(); i++) p.remove();
        } else {
            result += m.remove();
            for (int i=1; i<M && !m.isEmpty(); i++) m.remove();
        }

        while (!p.isEmpty()) {
            result += p.remove()*2;
            for (int i=1; i<M && !p.isEmpty(); i++) p.remove();
        }

        while (!m.isEmpty()) {
            result += m.remove() * 2;
            for (int i=1; i<M && !m.isEmpty(); i++) m.remove();
        }

        return result;
    }
}