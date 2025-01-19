import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
누적합인듯?
그냥 첫 줄부터 같은 값의 최댓값으로 넘겨주고
그리고 그 최댓값에서 위 아래로 몇 개까지 같은지 확인
그렇게 나온 결과 최댓값 털기
가로 세로 최대 333임
일단 전체 누적합 구하면 333 * 333
그리고 각 줄에 333개가 있으면 333 * 333 * 333 약 3690만
된다
*/

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][];

        for (int i=0; i<N; i++) {
            map[i] = bf.readLine().chars().map(n -> (n&1)).toArray();
        }

        System.out.println(solution(N, M, map));
    }

    static int solution(int N, int M, int[][] map) {
        int[][] sum = new int[N][M];
        for (int i=0; i<N; i++) {
            sum[i][0] = map[i][0];
            for (int j=1; j<M; j++) {
                sum[i][j] += map[i][j] + sum[i][j-1];
            }
        }

        int result = 0;

        for (int start = 0; start < M; start++) {
            for (int end = start; end < M; end++) {
                result = Math.max(result, find(start, end, N, M, map, sum));
            }
        }

        return result;
    }

    static int find(int start, int end, int N, int M, int[][] map, int[][] sum) {
        int result = 0;
        int now = 0;

        for (int i=0; i<N; i++) {
            if (sum[i][start] == sum[i][end] && map[i][start] == 0) {
                now++;
            } else {
                result = Math.max(result, now);
                now = 0;
            }
        }

        result = Math.max(result, now);

        return result * (end - start + 1);
    }
}