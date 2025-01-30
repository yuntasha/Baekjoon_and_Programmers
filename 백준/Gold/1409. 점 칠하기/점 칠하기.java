import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        boolean[] isPoint = new boolean[360];

        for (int i = 0; i < N; i++) {
            isPoint[Integer.parseInt(bf.readLine())] = true;
        }

        System.out.println(solution(N, isPoint));
    }

    public static long solution(int N, boolean[] isPoint) {
        int result = 0;
        for (int i=1; i<360; i++) {
            result = Math.max(result, getCount(isPoint, i));
        }
        return result;
    }

    public static int getCount(boolean[] isPoint, int r) {
        int[] group = IntStream.range(0, 360).toArray();
        int[] count = IntStream.range(0, 360).map(i -> 1).toArray();

        for (int i = 0; i < 360; i++) {
            if (isPoint[i] && isPoint[(i + r) % 360]) {
                int a = find(i, group);
                int b = find((i + r) % 360, group);

                if (a > b) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                if (a == b) continue;

                group[b] = a;
                count[a] += count[b];
            }
        }
        
        int result = 0;

        for (int i=0; i<360; i++) {
            if (i == find(i, group)) result += count[i] >> 1;
        }

        return result << 1;
    }

    public static int find(int n, int[] group) {
        if (n != group[n]) group[n] = find(group[n], group);
        return group[n];
    }
}