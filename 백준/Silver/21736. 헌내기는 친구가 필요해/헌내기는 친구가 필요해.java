import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var s = bufferedReader.readLine().strip().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);


        char[][] graph = new char[N][M];

        for (int i=0; i<N; i++){
            graph[i] = bufferedReader.readLine().strip().toCharArray();
        }

        solution(N, M, graph);
    }

    private static void solution(int N, int M, char[][] graph){

        var q = new LinkedList<Point>();
        var moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                if (graph[i][j]=='I') {
                    graph[i][j] = 'X';
                    q.add(new Point(i, j));
                }
            }
        }

        int cnt = 0;

        while (!q.isEmpty()){
            var now = q.removeLast();
            for (int[] move: moves){
                if (N>now.x+move[0] && now.x+move[0]>=0 && M>now.y+move[1] && now.y+move[1]>=0){
                    if (graph[now.x+move[0]][now.y+move[1]] == 'P'){
                        cnt++;
                        graph[now.x+move[0]][now.y+move[1]] = 'X';
                        q.addFirst(new Point(now.x+move[0], now.y+move[1]));
                    } else if (graph[now.x+move[0]][now.y+move[1]] == 'O'){
                        graph[now.x+move[0]][now.y+move[1]] = 'X';
                        q.addFirst(new Point(now.x+move[0], now.y+move[1]));
                    }
                }
            }
        }

        System.out.println(cnt==0?"TT":cnt);
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