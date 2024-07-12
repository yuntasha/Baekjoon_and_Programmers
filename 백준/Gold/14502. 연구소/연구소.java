import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        var arr = new int[N][M];
        var poision = new ArrayList<Point>();


        for (int i=0; i<N; i++){
            arr[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<M; j++){
                if (arr[i][j] == 2) poision.add(new Point(i, j));
            }
        }

        System.out.println(solution(N, M, arr, poision));
    }

    static int solution(int N, int M, int[][] arr, List<Point> poision){
        var result = 0;
        for (int i=0; i<N*M-2; i++){
            if (arr[i/M][i%M] != 0) continue;
            arr[i/M][i%M] = 1;
            for (int j=i+1; j<N*M-1; j++){
                if (arr[j/M][j%M] != 0) continue;
                arr[j/M][j%M] = 1;
                for (int k=j+1; k<N*M; k++){
                    if (arr[k/M][k%M] != 0) continue;
                    arr[k/M][k%M] = 1;
                    // 여기에 arr로 탐색한거 찾기
                    result = Math.max(result, spread(N, M, arr, poision));
                    arr[k/M][k%M] = 0;
                }
                arr[j/M][j%M] = 0;
            }
            arr[i/M][i%M] = 0;
        }
        return result;
    }

    static int spread(int N, int M, int[][] arr, List<Point> poision) {

        var result = 0;
        var visited = new boolean[N][M];
        LinkedList<Point> q = new LinkedList<>();
        var dx = new int[]{0, 1, 0, -1};
        var dy = new int[]{1, 0, -1, 0};

        for (Point p: poision){
            visited[p.x][p.y] = true;
            q.add(p);
        }
        while (!q.isEmpty()){
            var p = q.remove();
            for (int i=0; i<4; i++){
                var x = p.x + dx[i];
                var y = p.y + dy[i];
                if (!isIn(N, M, x, y) || arr[x][y] != 0 || visited[x][y]) continue;
                q.add(new Point(x, y));
                visited[x][y] = true;
            }
        }
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                if (arr[i][j]==0 && !visited[i][j]) result++;
            }
        }
        return result;
    }

    static boolean isIn(int N, int M, int x, int y){
        return 0<=x && x<N && 0<=y && y<M;
    }

    static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}