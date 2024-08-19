import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        var map = new char[N][M];

        for (int i=0; i<N; i++) {
            map[i] = bufferedReader.readLine().strip().toCharArray();
        }


        System.out.println(solution(N, M, map));
    }

    static int solution(int N, int M, char[][] map){
        Point red = null, blue = null, hole = null;
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                switch (map[i][j]){
                    case 'R':
                        red = new Point(i, j);
                        map[i][j] = '.';
                        break;
                    case 'B':
                        blue = new Point(i, j);
                        map[i][j] = '.';
                        break;
                    case 'O':
                        hole = new Point(i, j);
                        map[i][j] = '.';
                        break;
                }
            }
        }
        var result = dfs(map, red, blue, hole, 1);
        return result==Integer.MAX_VALUE?-1:result;
    }

    static int dfs(char[][] map, Point red, Point blue, Point hole, int n){
        if (n>10) return Integer.MAX_VALUE;
        var result = Integer.MAX_VALUE;

        var dList = new Direct[]{Direct.UP, Direct.DOWN, Direct.LEFT, Direct.RIGHT};

        for (Direct d: dList) {
            var r = red.move(d, map, blue, hole);
            if (r==null) {
                if (blue.move(d, map, new Point(0,0), hole)==null) continue;
                else return n;
            }
            var b = blue.move(d, map, r, hole);
            if (b==null) continue;
            r = r.move(d, map, b, hole);
            result = Math.min(result, dfs(map, r, b, hole, n+1));
        }

        return result;
    }


    static class Point{
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point move(Direct d, char[][] map, Point p, Point hole){
            var now = new Point(x, y);
            while (map[now.x+d.x][now.y+d.y]=='.' && (now.x+d.x != p.x || now.y+d.y != p.y)) {
                now.x+=d.x;
                now.y+=d.y;
                if (hole.x == now.x && hole.y == now.y) return null;
            }
            return now;
        }
    }

    enum Direct{
        UP(-1 ,0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

        final int x;
        final int y;

        Direct(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}