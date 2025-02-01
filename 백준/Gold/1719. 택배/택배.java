import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/*
일단 그 벨만포드인가 뭐시기로 푸는거 다익스트라 전체 탐색같은 느낌
그럼 시간 저장하는 배열과 어디를 통하는지에 대한 배열을 정함
갹 라인을 탐색 후 걔네들을 경유해서 지나가는 것으로 만듦
초기에 연결된 애인지 먼저 확인하면 되지 않을까
이것도 예외 생길듯 -> 약간 2 -> 4도 가능한데 2 -> 3 -> 4가 더 빠른 경우
아 next활용하면 되는구나 ㅇㅎ
 */

public class Main {

    static int MAX_VALUE = 2_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][N + 1];
        int[][] next = new int[N + 1][N + 1];
        for (int n = 1; n <= N; n++) Arrays.fill(map[n], MAX_VALUE);

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            next[a][b] = b;
            next[b][a] = a;
            map[a][b] = map[b][a] = v;
        }

        System.out.println(solution(N, M, map, next));
    }


    static String solution(int N, int M, int[][] map, int[][] next) {
        for (int i = 1; i <= N; i++) {
            for (int a = 1; a <= N; a++) {
                if (a == i || map[i][a] == MAX_VALUE) continue;
                for (int b = a + 1; b <= N; b++) {
                    if (b == i || map[i][b] == MAX_VALUE) continue;
                    if (map[a][b] > map[a][i] + map[i][b]) {
                        map[a][b] = map[b][a] = map[a][i] + map[i][b];
                        next[a][b] = next[a][i];
                        next[b][a] = next[b][i];
                    }
                }
            }
        }

        StringJoiner result = new StringJoiner("\n");

        for (int i = 1; i <= N; i++) {
            StringJoiner sj = new StringJoiner(" ");
            for (int j = 1; j <= N; j++) {
                sj.add(next[i][j]==0?"-":String.valueOf(next[i][j]));
            }
            result.add(sj.toString());
        }

        return result.toString();
    }
}