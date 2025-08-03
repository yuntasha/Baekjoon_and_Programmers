import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int MAX = 12;
    static int min = MAX;
    static int sCnt = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        int[] quests = new int[M];

        for (int i = 0; i < M; i++) {
            quests[i] = convert(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }

        System.out.println(solution(N, M, K, quests));
    }

    public static int solution(int N, int M, int K, int[] quests) {
        return find(1, 0, N, M, K, quests);
    }

    public static int find(int n, int keySet, int N, int M, int K, int[] quests) {
        if (n > 2 * N) {
            if (getCount(keySet) != N) return 0;
            int result = 0;

            for (int quest : quests) {
                if ((quest & keySet) == quest) result++;
            }

            return result;
        }

        int result = find(n + 1, keySet, N, M, K, quests);

        if (getCount(keySet) < n) result = Math.max(result, find(n + 1, keySet | (1 << n), N, M, K, quests));

        return result;
    }

    public static int getCount(int bits) {
        int result = 0;

        while (bits > 0) {
            result += (bits & 1);
            bits = bits >> 1;
        }

        return result;
    }

    public static int convert(int[] arr) {
        int result = 0;

        for (int i : arr) {
            result |= (1 << i);
        }

        return result;
    }
}