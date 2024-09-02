import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Point> POINTS = new ArrayList<>();
    static int NOW;
    static int[] DP = new int[33554433];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        for (int i=0; i<5; i++) {
            var now = bufferedReader.readLine().strip().toCharArray();
            for (int j=0; j<5; j++){
                if (now[j] == '*') POINTS.add(new Point(i, j));
            }
        }
        System.out.println(solution());
    }

    static int solution(){
        var result = Integer.MAX_VALUE;
        for (int i=0; i<25; i++) {
            NOW = 1<<i;
            result = Math.min(result, dfs(i/5, i%5, POINTS.size()-1));
            if (result==0) return 0;
        }
        return result;
    }

    static int dfs(int x, int y, int depth) {
        var result = Integer.MAX_VALUE;
        if (depth==0) {
            // DP 확인후 없으면 탐색
            if (DP[NOW]==0) DP[NOW] = findResult(NOW);

            return DP[NOW];
        }

        for (int i=0; i<4; i++){
            var nx = x+dx[i];
            var ny = y+dy[i];

            if (!isIn(nx, ny) || (NOW & nowBit(nx, ny))>0) continue;
            NOW = NOW | nowBit(nx, ny);
            result = Math.min(result, dfs(nx, ny, depth-1));
            result = Math.min(result, dfs(x, y, depth-1));
            NOW = NOW ^ nowBit(nx, ny);
        }

        return result;
    }

    static int findResult(int n) {
        var result = Integer.MAX_VALUE;
        var now = new ArrayList<Point>();
        for (int i=0; i<25; i++) {
            if (((1<<i)&n)==0) continue;
            now.add(new Point(i/5, i%5));
        }
        
        return minNum(0, now, 0, 0);
    }

    static int minNum(int n, List<Point> p, int visited, int distance) {
        if (n==POINTS.size()) return distance;
        
        var result = Integer.MAX_VALUE;
        for (int i=0; i<p.size(); i++) {
            if ((visited & (1<<i)) > 0) continue; // 이미 조회했으면 지나감v
            var now = POINTS.get(n).getDistance(p.get(i));
            distance += now;
            result = Math.min(result, minNum(n+1, p, visited|(1<<i), distance));
            distance -= now;
        }

        return result;
    }

    static int nowBit(int x, int y){
        return 1<<(x*5+y);
    }

    static boolean isIn(int x, int y){
        return 0<=x && x<5 && 0<=y && y<5;
    }

    static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        int getDistance(Point p) {
            return Math.abs(p.x-x) + Math.abs(p.y-y);
        }
    }
}