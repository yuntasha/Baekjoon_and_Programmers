import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var N = Integer.parseInt(bufferedReader.readLine());
        var rgb = new Solution(N);
        var rb = new Solution(N);
        for (int i=0; i<N; i++){
            var M = bufferedReader.readLine().strip();
            rgb.makeRgbGraph(M, i);
            rb.makeRbGraph(M, i);
        }
        System.out.println(rgb.getResult()+" "+ rb.getResult());
    }

    private static class Solution{
        int N;
        char[][] graph;

        public Solution(int N){
            this.N = N;
            this.graph = new char[N][N];
        }


        public void makeRgbGraph(String line, int n){
            graph[n] = line.toCharArray();
        }

        public void makeRbGraph(String line, int n){
            var ca = line.toCharArray();
            for (int i=0; i<N; i++){
                graph[n][i] = ca[i]=='G'?'R':ca[i];
            }
        }

        public int getResult(){
            int n = 0;
            LinkedList<Pair> ll = new LinkedList<>();
            int[][] moves = {{1,0}, {0,1}, {-1,0}, {0,-1}};
            for (int i=0; i<N; i++){
                for (int j=0; j<N; j++){
                    if (graph[i][j]!='Z'){
                        ll.addFirst(new Pair(i, j, graph[i][j]));
                        graph[i][j] = 'Z';
                        n++;
                        while (!ll.isEmpty()){
                            var p = ll.removeLast();
                            for (var move:moves){
                                if (p.x+move[0]>=0 && p.x+move[0]<N && p.y+move[1]>=0 && p.y+move[1]<N) {
                                    if (p.now == graph[p.x + move[0]][p.y + move[1]]) {
                                        graph[p.x + move[0]][p.y + move[1]] = 'Z';
                                        ll.addFirst(new Pair(p.x + move[0], p.y + move[1], p.now));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return n;
        }
    }

    public static class Pair{
        int x;
        int y;
        char now;

        Pair(int x, int y, char now){
            this.x = x;
            this.y = y;
            this.now = now;
        }
    }
}