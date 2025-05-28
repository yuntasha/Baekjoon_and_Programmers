import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
그냥 전체 위치에서 갈 수 있는 최장거리 뽑아서 가져오자
 */

public class Main {

    static int MAX_VALUE = 200_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, M));
    }

    public static String solution(int N, int M) {
        HashMap<Integer, Integer> mem = new HashMap<>();

        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.add(N);
        mem.put(N, 0);

        while (!q.isEmpty() && !mem.containsKey(M)) {
            int now = q.remove();

            if (!mem.containsKey(now + 1)) {
                mem.put(now + 1, mem.get(now) + 1);
                q.add(now + 1);
            }

            if (!mem.containsKey(now - 1) && now > 0) {
                mem.put(now - 1, mem.get(now) + 1);
                q.add(now - 1);
            }

            if (!mem.containsKey(now * 2) && now * 2 < MAX_VALUE) {
                mem.put(now * 2, mem.get(now) + 1);
                q.add(now * 2);
            }
        }

        int[] result = new int[mem.get(M) + 1];

        result[mem.get(M)] = M;

        for (int i = mem.get(M) - 1; i > 0; i--) {
            if (mem.containsKey(result[i + 1] - 1) && mem.get(result[i + 1] - 1) == i) {
                result[i] = result[i + 1] - 1;
            }

            if (mem.containsKey(result[i + 1] + 1) && mem.get(result[i + 1] + 1) == i) {
                result[i] = result[i + 1] + 1;
            }

            if ((result[i + 1] & 1) == 0 && mem.containsKey(result[i + 1] / 2) && mem.get(result[i + 1] / 2) == i) {
                result[i] = result[i + 1] / 2;
            }
        }

        result[0] = N;

        return mem.get(M) + "\n" + Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }
}