import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        if (N == 0) {
            System.out.println(0);
            return;
        }

        ArrayDeque<Integer>[] arr = new ArrayDeque[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = new ArrayDeque<>();
        }

        StringTokenizer input = new StringTokenizer(bf.readLine());
        List<Integer> l = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(input.nextToken());
            arr[n].add(i);
            l.add(n);
        }

        System.out.println(solution(N, arr, l));
    }

    static int solution(int N, ArrayDeque<Integer>[] arr, List<Integer> l) {
        HashSet<Integer> set = new HashSet<>();
        int result = 0;

        for (int i = 0; i < N; i++) {
            int now = l.get(i);
            arr[now].remove();

            if (set.contains(now)) continue;

            if (set.size() == 4) {
                int maxV = -1;
                int min = -1;
                for (int n : set) {
                    if (arr[n].isEmpty()) {
                        min = n;
                        break;
                    }
                    if (arr[n].peek() > maxV) {
                        maxV = arr[n].peek();
                        min = n;
                    }
                }

                set.remove(min);
            }

            set.add(now);
            result++;
        }

        return result;
    }
}