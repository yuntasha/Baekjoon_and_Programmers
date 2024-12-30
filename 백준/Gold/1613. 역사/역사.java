import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
시간 순서를 주면 테케를 주고 그만큼 물어봄
2차원 배열을 만들어서 앞에것이 먼저 일어나면 -1
뒤에것이 먼저 일어나면 1
모르면 0
1에서부터 N까지 모두 찾음
1보다 작은거 찾는 과정으로 쭉~
그러면서 이미 방문한 애들은 배열에 true로 두어서 재탐색은 하지 않게 만듦
그다음에 큰거 찾는 과정으로 쭉~

 */

public class Main {

    static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        result = new int[N + 1][N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            result[x][y] = -1;
            result[y][x] = 1;
        }

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                for (int k=j+1; k<=N; k++) {
                    if (result[i][j] + result[i][k] == 0 && result[i][j] != 0) {
                        result[j][k] = result[i][k];
                        result[k][j] = result[i][j];
                    }
                }
            }
        }

        int T = Integer.parseInt(bf.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(bf.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            System.out.println(result[x][y]);
        }
    }
}