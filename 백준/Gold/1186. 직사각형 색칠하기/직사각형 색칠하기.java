import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var NK = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        var N = NK[0];
        var K = NK[1];

        var squares = new ArrayList<Square>();

        for (int i=0; i<N; i++) {
            squares.add(new Square(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }

        System.out.println(solution(N, K, squares).stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    static List<Integer> solution(int N, int K, List<Square> squares) {
        var gridX = new TreeSet<Integer>();
        var gridY = new TreeSet<Integer>();
        for (Square s: squares) {
            gridX.add(s.x1);
            gridX.add(s.x2);
            gridY.add(s.y1);
            gridY.add(s.y2);
        }

        var area = new int[gridX.size()-1][gridY.size()-1];
        var areaS = new int[gridX.size()-1][gridY.size()-1];

        for (int[] ss: areaS) Arrays.fill(ss, -1);

        var gx = gridX.toArray(new Integer[0]);
        var gy = gridY.toArray(new Integer[0]);

        for (int i=0; i<gridX.size()-1; i++) {
            for (int j=0; j<gridY.size()-1; j++) {
                area[i][j] = (gx[i+1]-gx[i]) * (gy[j+1]-gy[j]);
            }
        }

        var result = new ArrayList<Area>();

        for (int i=0; i<squares.size(); i++) {
            var now = squares.get(i);
            var x1 = find(now.x1, gx);
            var x2 = find(now.x2, gx);
            var y1 = find(now.y1, gy);
            var y2 = find(now.y2, gy);

            var n = new Area(i+1, 0);
            result.add(n);

            for (int x=x1; x<x2; x++) {
                for (int y=y1; y<y2; y++) {
                    if (areaS[x][y]>=0) result.get(areaS[x][y]).mArea(area[x][y]);
                    n.pArea(area[x][y]);
                    areaS[x][y] = i;
                }
            }
        }

        result.sort(Comparator.comparingInt(Area::getValue).reversed()
                .thenComparingInt(Area::getNum));

        var a = new ArrayList<Integer>();

        for (int i=0; i<K; i++) {
            a.add(result.get(i).num);
        }
        a.sort(Comparator.naturalOrder());
        return a;
    }

    static int find(int n, Integer[] arr) {
        if (n==arr[0]) return 0;
        if (n==arr[arr.length-1]) return arr.length-1;

        var s = 0;
        var e = arr.length-1;

        while (s+1<e) {
            var mid = (s+e)/2;

            if (arr[mid]>n) e = mid;
            else s = mid;
        }
        return s;
    }

    static class Area{
        int num;
        int value;

        Area(int num, int value) {
            this.num = num;
            this.value = value;
        }

        public int getNum() {
            return num;
        }

        public int getValue() {
            return value;
        }

        public void mArea(int x) {
            value-=x;
        }

        public void pArea(int x){
            value+=x;
        }
    }


    static class Square {
        int x1;
        int y1;
        int x2;
        int y2;

        Square(int[] input) {
            this.x1 = input[0];
            this.y1 = input[1];
            this.x2 = input[2];
            this.y2 = input[3];
        }
    }
}