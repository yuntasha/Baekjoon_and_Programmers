import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
        var now = 0;
        var group = new int[N][M];
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                now = Math.max(findGroup(N, M, map, group, i, j, now+1), now);
            }
        }
        return now;
    }

    static int findGroup(int N, int M, char[][] map, int[][] group, int x, int y, int n) {
        if (!(0<=x && x<N && 0<=y && y<M)) return n;
        if (group[x][y]>0) return group[x][y];
        if (group[x][y]==-1) return n;

        group[x][y]=-1;
        int dx=-1;
        int dy=-1;
        switch (map[x][y]) {
            case 'U':
                dx = x-1;
                dy = y;
                break;
            case 'D':
                dx = x+1;
                dy = y;
                break;
            case 'L':
                dx = x;
                dy = y-1;
                break;
            case 'R':
                dx = x;
                dy = y+1;
                break;
        }

        group[x][y] = findGroup(N, M, map, group, dx, dy, n);
        return group[x][y];
    }
}