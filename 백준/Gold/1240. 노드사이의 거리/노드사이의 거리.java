import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        var nodes = new int[N][N];

        for (int i=0; i<N-1; i++) {
            var line = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes[line[0]-1][line[1]-1] = line[2];
            nodes[line[1]-1][line[0]-1] = line[2];
        }

        solution(N, nodes);

        for (int i=0; i<M; i++) {
            var now = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(nodes[now[0]-1][now[1]-1]);
        }
    }

    static void solution(int N, int[][] nodes) {
        for (int i=0; i<N; i++) { // 이 노드가 이어지는 경우
            for (int j=0; j<N; j++) { // 이 노드와
                if (nodes[i][j]==0) continue;
                for (int k=j+1; k<N; k++) { // 이 노드를 연결시켜준다.
                    if (nodes[i][k]==0) continue;
                    if (nodes[j][k]>0) continue;
                    nodes[k][j] = nodes[j][k] = nodes[i][j] + nodes[i][k];
                }
            }
        }
    }
}