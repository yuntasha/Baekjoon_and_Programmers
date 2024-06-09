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
        var N = Arrays.stream(bufferedReader.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray();
        var graph = new int[N[2]][N[1]][];
        for (int i=0; i<N[2]; i++){
            for (int j=0; j<N[1]; j++){
                graph[i][j] = Arrays.stream(bufferedReader.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
        }
        System.out.println(solution(N, graph));
    }

    private static int solution(int[] N, int[][][] graph){
        var q = new LinkedList<Point>(findTom(N, graph));
        var moves = new int[][]{{0,0,1}, {0,1,0}, {1,0,0}, {-1,0,0}, {0,-1,0}, {0,0,-1}};
        var max = 0;
        var cnt = q.size();
        while (!q.isEmpty()){
            var p = q.removeLast();
            var now = graph[p.x][p.y][p.z];
            for (int[] move: moves){
                if (p.x+move[0]>=0 && p.x+move[0]<N[2] && p.y+move[1]>=0 && p.y+move[1]<N[1] && p.z+move[2]>=0 && p.z+move[2]<N[0]){
                    if (graph[p.x+move[0]][p.y+move[1]][p.z+move[2]]==0){
                        graph[p.x+move[0]][p.y+move[1]][p.z+move[2]] = graph[p.x][p.y][p.z]+1;
                        cnt++;
                        max = graph[p.x+move[0]][p.y+move[1]][p.z+move[2]];
                        q.addFirst(new Point(p.x+move[0],p.y+move[1],p.z+move[2]));
                    }
                }
            }
        }
        return Arrays.stream(graph).flatMapToInt(i -> Arrays.stream(i).flatMapToInt(Arrays::stream)).anyMatch(i-> i==0)?-1:max==0?0:max-1;
    }

    private static List<Point> findTom(int[] N, int[][][] graph){
        var list = new ArrayList<Point>();
        for (int i=0; i<N[2]; i++){
            for (int j=0; j<N[1]; j++){
                for (int k=0; k<N[0]; k++){
                    if (graph[i][j][k] == 1) {
                        list.add(new Point(i, j, k));
                    }
                }
            }
        }
        return list;
    }

    private static class Point{
        int x;
        int y;
        int z;

        Point(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            return x+" "+y+" "+z;
        }
    }
}