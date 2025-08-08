import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        char[] arr = new char[M];

        input = new StringTokenizer(bf.readLine());

        for (int i = 0; i < M; i++) {
            arr[i] = input.nextToken().charAt(0);
        }

        System.out.println(solution(N, M, arr).stream().collect(Collectors.joining("\n")));
    }

    public static List<String> solution(int N, int M, char[] arr) {
        List<String> result = new ArrayList<>();

        Arrays.sort(arr);

        int[] isA = new int[M];

        for (int i = 0; i < M; i++) {
            isA[i] = arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u' ? 1 : 0;
        }

        find(0, 0, 0, new char[N], result, N, M, arr, isA);

        return result;
    }

    public static void find(int n, int mCnt, int cursor, char[] s, List<String> result, int N, int M, char[] arr, int[] isA) {
        if (n == N) {
            if (mCnt == 0 || N - mCnt < 2) return;
            StringBuilder sb = new StringBuilder();
            for (char c : s) sb.append(c);
            result.add(sb.toString());
            return;
        }

        for (int i = cursor; i < M; i++) {
            s[n] = arr[i];
            find(n + 1, mCnt + isA[i], i + 1, s, result, N, M, arr, isA);
        }
    }
}