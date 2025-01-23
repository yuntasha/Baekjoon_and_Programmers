import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
DP인 느낌인데
DP[앞에 위치][뒤에 위치]로 구하면 될 것 같다
같으면 DP[S+1][E-1]에 최솟값 업데이트
다르면 DP[S+1][E], DP[S][E-1]에 업데이트
업데이트 순서는?
S가 0인곳부터 쭉?
이라고 하기에는 뭔가 이상하다
그냥 BFS처럼 다가가는게 맞는듯
그러면 점화식 다시 작성
DP[S][E]의 업데이트
다를 경우 DP[S-1][E], DP[S][E+1]로 업데이트
같을 경우 DP[S-1][E-1]로 업데이트
시작은 양 끝이 같으면 1, N-2로 만들기
양끝이 다르면 0, N-2와 1, N-1로 만들기
0, 1 BFS느낌으로?
근데 1, 2인데? 이건 아님
어떻게 조회하냐....
1 - N까지 조회했을때
1-N 반복문 안에 s가 0부터 N까지, 그 다음에 e가 0부터 N까지 그러다가 둘이 같아지면 컷할까 그럼 중복이 없나
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    public static int solution(int N, int[] arr) {
        if (N == 1) {
            return 0;
        }

        if (N == 2) {
            return arr[0] == arr[1] ? 0 : 1;
        }

        int[][] DP = new int[N][N];

        for (int i=0; i<N; i++) {
            Arrays.fill(DP[i], -1);
        }

        return dfs(0, N-1, arr, DP);
    }

    static int dfs(int s, int e, int[] arr, int[][] DP) {
        if (DP[s][e] != -1) {
            return DP[s][e];
        }

        if (s >= e) {
            DP[s][e] = 0;
            return 0;
        }

        DP[s][e] = Math.min(dfs(s, e-1, arr, DP) + 1, dfs(s+1, e, arr, DP) + 1);

        if (arr[s] == arr[e]) {
            DP[s][e] = Math.min(DP[s][e], dfs(s+1, e-1, arr, DP));
        }

        return DP[s][e];
    }
}