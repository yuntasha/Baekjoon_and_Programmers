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

        long[] gits = new long[N];

        for (int i = 0; i < N; i++) {
            gits[i] = convert(bf.readLine().split(" ")[1].toCharArray());
        }

        System.out.println(solution(N, M, gits));
    }

    public static int solution(int N, int M, long[] gits) {
        find(0, 0, 0, N, M, gits);
        return min == MAX ? -1 : min;
    }

    public static void find(long now, int gCnt, int n, int N, int M, long[] gits) {
        if (n == N) {
            if (now == 0) return;
            int cnt = getCount(now);

            if (sCnt < cnt) {
                min = gCnt;
                sCnt = cnt;
            }
            if (sCnt == cnt) min = Math.min(min, gCnt);
            return;
        }

        find(now, gCnt, n + 1, N, M, gits);
        find(now | gits[n], gCnt + 1, n + 1, N, M, gits);
    }

    public static int getCount(long bits) {
        int result = 0;

        while (bits > 0) {
            result += (bits & 1);
            bits = bits >> 1;
        }

        return result;
    }

    public static long convert(char[] YN) {
        long result = 0;

        for (char c : YN) {
            result = result << 1;
            if (c == 'Y') {
                result |= 1;
            }
        }

        return result;
    }
}