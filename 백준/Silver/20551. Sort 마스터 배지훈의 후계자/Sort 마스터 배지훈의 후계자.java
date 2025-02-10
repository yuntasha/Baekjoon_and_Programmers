import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
벨만 포드
돈을 다시 주을 수 있음
그러니까 싸이클을 찾아야함
근데 싸이클은 양의 싸이클만 찾고 음의 싸이클은 찾을 필요가 없음
근데 싸이클이 돌아졌을때
역추적을 해서 찾음
근데 만약에 최적 경로가 아닌 곳에서 갑자기 양의 싸이클이 도는 것이 있다면?

 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(A);

        StringJoiner result = new StringJoiner("\n");

        for (int i = 0; i < M; i++) {
            result.add(String.valueOf(bi(Integer.parseInt(bf.readLine()), A)));
        }

        System.out.println(result.toString());
    }

    static int bi(int n, int[] M) {
        if (M[0] > n) return -1;
        if (M[M.length - 1] < n) return -1;
        if (M[0] == n) return 0;

        int s = 0;
        int e = M.length - 1;

        while (s + 1 < e) {
            int m = (s + e) >> 1;

            if (M[m] < n) {
                s = m;
            } else {
                e = m;
            }
        }

        return M[e] == n ? e : -1;
    }
}