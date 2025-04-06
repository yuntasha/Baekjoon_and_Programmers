import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
이분탐색으로 찾으면 빠를 듯
근데 최대가 100만 1천임
그러니까 그냥 200만으로 퉁치자

3 3
3 3

7
4 / 3 = 1

2
 */

public class Main {

    static int MAX_VALUE = 2_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        List<Med> meds = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());

            meds.add(new Med(a, b));
        }

        int K = Integer.parseInt(bf.readLine());

        System.out.print(solution(N, meds, K));
    }

    static int solution(int N, List<Med> meds, int K) {
        int s = 0;
        int e = MAX_VALUE;

        while (s + 1 < e) {
            int m = (s + e) >> 1;

            if (K < find(m, meds)) {
                e = m;
            } else {
                s = m;
            }
        }

        if (isOk(s, meds) == K) return s;
        return 0;
    }

    static int isOk(int n, List<Med> meds) {
        int result = 0;

        for (Med med : meds) {
            result += med.getRX(n);
        }

        return result;
    }

    static int find(int n, List<Med> meds) {
        int result = 0;

        for (Med med : meds) {
            result += med.getX(n);
        }

        return result;
    }

    static class Med {
        int a;
        int b;

        public Med(int a, int b) {
            this.a = a;
            this.b = b;
        }

        int getX(int n) {
            return (n - b) / a + ((n - b) % a > 0 ? 1 : 0);
        }

        int getRX(int n) {
            return (n - b) / a;
        }
    }
}