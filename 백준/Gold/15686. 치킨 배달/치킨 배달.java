import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NM[0];
        var M = NM[1];

        var chicken = new ArrayList<Point>();
        var house = new ArrayList<Point>();


        for (int i=0; i<N; i++){
            var now = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<N; j++){
                if (now[j] == 1) house.add(new Point(i, j));
                if (now[j] == 2) chicken.add(new Point(i, j));
            }
        }

        System.out.println(solution(N, M, chicken, house));
    }

    static int solution(int N, int M, List<Point> chicken, List<Point> house){
        var distance = new ArrayList<ArrayList<Integer>>();

        for (var h: house){
            var now = new ArrayList<Integer>();
            distance.add(now);
            for (var c: chicken){
                now.add(c.dis(h));
            }
        }

        var searchList = new ArrayList<Integer>();

        return dfs(N, M, distance, searchList);
    }

    static int dfs(int N, int M, ArrayList<ArrayList<Integer>> distance, ArrayList<Integer> searchList){
        var result = Integer.MAX_VALUE;
        if (searchList.size() == M) {
            result = 0;
            for (int i = 0; i < distance.size(); i++) {
                var min = Integer.MAX_VALUE;
                for (int s : searchList) {
                    min = Math.min(distance.get(i).get(s), min);
                }
                result += min;
            }
        } else {
            int init = searchList.isEmpty()?0:searchList.get(searchList.size()-1)+1;
            for (int i=init; i<distance.get(0).size()-(M-searchList.size())+1; i++) {
                searchList.add(i);
                result = Math.min(result, dfs(N, M, distance, searchList));
                searchList.remove(searchList.size()-1);
            }
        }
        return result;
    }

    static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        int dis(Point t){
            return Math.abs(this.x-t.x) + Math.abs(this.y-t.y);
        }
    }
}