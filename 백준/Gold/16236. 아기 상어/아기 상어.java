import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var arr = new int[N][N];

        for (int i=0; i<N; i++){
            arr[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(solution(N, arr));
    }


    static int solution(int N, int[][] arr){
        var now = new int[]{-1, -1};
        Loop: for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                if (arr[i][j]==9) {
                    now[0] = i;
                    now[1] = j;
                    break Loop;
                }
            }
        }

        var t = 0;
        var size = 2;
        var eat = 0;
        while (now[0] != -1){
            arr[now[0]][now[1]] = 0;
            if (size == eat){
                size+=1;
                eat = 0;
            }
            t += findNext(N, now, arr, size);
            eat++;
        }

        return t;
    }

    static int findNext(int N, int[] now, int[][] arr, int size){
        var dx = new int[]{0, 1, 0, -1};
        var dy = new int[]{1, 0, -1, 0};

        var visited = new boolean[N][N];

        var nowTime = Integer.MAX_VALUE;
        var q = new LinkedList<Point>();

        var result = new ArrayList<Point>();

        q.add(new Point(now[0], now[1], 0));

        while (!q.isEmpty()){
            var p = q.remove();
            if (p.t>=nowTime) break;

            for (int i=0; i<4; i++){
                var x = p.x + dx[i];
                var y = p.y + dy[i];
                if (!isIn(N, x, y) || visited[x][y] || (arr[x][y] > size && arr[x][y] != 0)) continue;

                visited[x][y] = true;

                if (arr[x][y] < size && arr[x][y] != 0){
                    result.add(new Point(x, y, p.t+1));
                    nowTime = p.t+1;
                }

                q.add(new Point(x, y, p.t+1));
            }
        }

        result.sort(Comparator.comparing(Point::getX)
                .thenComparing(Point::getY));
        if (result.isEmpty()) {
            now[0] = -1;
            now[1] = -1;
            return 0;
        } else {
            var p = result.get(0);
            now[0] = p.x;
            now[1] = p.y;
            return p.t;
        }
    }


    static boolean isIn(int N, int x, int y){
        return 0<=x && x<N && 0<=y && y<N;
    }

    static class Point{
        int x;
        int y;
        int t;

        Point(int x, int y, int t){
            this.x = x;
            this.y = y;
            this.t = t;
        }

        int getX() {
            return this.x;
        }

        int getY() {
            return this.y;
        }
    }
}