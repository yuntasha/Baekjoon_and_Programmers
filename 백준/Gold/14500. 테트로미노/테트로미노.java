import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var s = bufferedReader.readLine().strip().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);

        int[][] graph = new int[N][M];

        for (int i=0; i<N; i++){
            graph[i] = Arrays.stream(bufferedReader.readLine().strip().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        System.out.println(solution(N, M, graph));
    }

    public static int solution(int N, int M, int[][] graph){
        var shapes = new int[][][]{
                {{0,0}, {0,1}, {0,2}, {0,3}}, // ㅡ
                {{0,0}, {1,0}, {0,1}, {1,1}}, // ㅁ
                {{0,0}, {1,0}, {2,0}, {0,1}}, // ㄴ
                {{0,0}, {1,0}, {1,1}, {2,1}}, // ㄱㄴ
                {{0,0}, {1,0}, {2,0}, {1,1}} // ㅜ
        };

        var rotates = new int[][]{
                {1,1}, {1,-1}, {-1,1}, {-1,-1}
        };

        int max = 0;

        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                for (var shape: shapes){
                    for (var rotate: rotates){
                        // xy 그대로
                        int now = 0;
                        for (var s: shape){
                            int x = i+s[0]*rotate[0];
                            int y = j+s[1]*rotate[1];
                            if (0<=x && x<N && 0<=y && y<M){
                                now+=graph[x][y];
                            }
                        }
                        max = max<now?now:max;

                        // xy 뒤집어서
                        now = 0;
                        for (var s: shape){
                            int x = i+s[1]*rotate[1];
                            int y = j+s[0]*rotate[0];
                            if (0<=x && x<N && 0<=y && y<M){
                                now+=graph[x][y];
                            }
                        }
                        max = max<now?now:max;
                    }
                }
            }
        }
        return max;
    }
}