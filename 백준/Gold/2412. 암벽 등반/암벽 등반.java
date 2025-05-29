import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
그냥 전체 위치에서 갈 수 있는 최장거리 뽑아서 가져오자
 */

public class Main {

    static long TURN_VALUE = 1_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int T = Integer.parseInt(input.nextToken());

        HashSet<Long> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            set.add(hash(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken())));
        }

        System.out.println(solution(N, T, set));
    }

    public static int solution(int N, int T, Set<Long> set) {
        ArrayDeque<Long> q = new ArrayDeque<>();

        Map<Long, Integer> visited = new HashMap<>();
        visited.put(0L, 0);

        q.add(0L);

        while (!q.isEmpty()) {
            long now = q.remove();

            if (unHashY(now) == T) return visited.get(now);

            for (int i = -2; i < 3; i++) {
                for (int j = -2; j < 3; j++) {
                    if (i == 0 && j == 0) continue;
                    long next = hash(unHashX(now) + i, unHashY(now) + j);
                    if (visited.containsKey(next) || !set.contains(next)) continue;
                    visited.put(next, visited.get(now) + 1);
                    q.add(next);
                }
            }
        }

        return -1;
    }

    public static long hash(int x, int y) {
        return x * TURN_VALUE + y;
    }

    public static int unHashX(long h) {
        return (int) (h / TURN_VALUE);
    }

    public static int unHashY(long h) {
        return (int) (h % TURN_VALUE);
    }
}