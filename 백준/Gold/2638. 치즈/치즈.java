import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var graph = new ArrayList<ArrayList<Integer>>();

        graph.add(new ArrayList<>());
        for (int i=0; i<M+2; i++){
            graph.get(0).add(0);
        }

        for (int i=0; i<N; i++){
            var now = new ArrayList<Integer>();

            graph.add(now);
            now.add(0);

            for (int n:Arrays.stream(bufferedReader.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray()) {
                now.add(n==0?0:2);
            }

            now.add(0);
        }

        graph.add(new ArrayList<>());
        for (int i=0; i<M+2; i++){
            graph.get(N+1).add(0);
        }

        System.out.println(solution(N, M, graph));
    }

    static int solution(int N, int M, ArrayList<ArrayList<Integer>> graph){

        var arr = new LinkedList<Point>();

        arr.add(new Point(0, 0));

        var result = 0;

        var temp = findErase(N, M, graph, arr);

        while (!temp.isEmpty()){
            result++;
//            System.out.println("===========" + result + "===========");
//            System.out.println(graph.stream().map(g -> g.stream().map(i->i==-1?"O":"X").collect(Collectors.joining(" ")).toString()).collect(Collectors.joining("\n")).toString());
            for (Point p: temp){
                graph.get(p.x).set(p.y, -1);
                arr.add(p);
            }
            temp = findErase(N, M, graph, arr);
        }

        return result;
    }

    static List<Point> findErase(int N, int M,  ArrayList<ArrayList<Integer>> graph, LinkedList<Point> ll) {
        List<Point> result = new ArrayList<>();
        while (!ll.isEmpty()) {
            var p = ll.remove();
            for (int i=0; i<4; i++){
                var x = p.x + dx[i];
                var y = p.y + dy[i];

                if (isOut(N+2, M+2, x, y)) continue;
//                System.out.println(graph.stream().map(g -> g.stream().map(String::valueOf).collect(Collectors.joining(" ")).toString()).collect(Collectors.joining("\n", "================\n","\n================")).toString());
                switch (graph.get(x).get(y)){
                    case 0:
                        graph.get(x).set(y, -1);
                        ll.add(new Point(x, y));
                        break;
                    case 1:
                        result.add(new Point(x, y));
                        break;
                    case 2:
                        graph.get(x).set(y, 1);
                        break;
                    default: break;
                }
            }
        }

        return result;
    }

    static boolean isOut(int N, int M, int x, int y){
        return !(0<=x && x<N && 0<=y && y<M);
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